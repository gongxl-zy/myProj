package com.gxl.biz.impl;

import gxl.mult.code.Uuid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.MenuBiz;
import com.gxl.biz.RefreshCacheBiz;
import com.gxl.common.Cache;
import com.gxl.common.CheckUtils;
import com.gxl.common.Constants;
import com.gxl.common.ReflectUtils;
import com.gxl.common.StringUtils;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PFunction;
import com.gxl.entity.PMenu;
import com.gxl.entity.PRoleMenu;
import com.gxl.entity.PUser;
import com.gxl.pm.po.PmMenu;

@Service(value="menuBiz")
public class MenuBizImpl implements MenuBiz{
	@Autowired private PublicDao publicDao;
	@Autowired private RefreshCacheBiz refreshCacheBiz;
	
	@Override
	public List<PmMenu> getUserMenu(PUser user) throws Exception{
		List<Object> list = publicDao.selectListByHql("from PMenu order by menuType, menuSort");
		List<PmMenu> rtnList = new ArrayList<PmMenu>();
		for(Object obj : list){
			PMenu menu = (PMenu)obj;
			rtnList.add(new PmMenu(menu));
		}
		return rtnList;
	}
	
	@Override
	public Long countMenus(PmMenu menu) throws Exception{
		String hql = "select count(m) from PMenu m";
		return publicDao.countByHql(hql);
	}
	
	@Override
	public void txOpMenu(PmMenu menu,String operSign,String opUserId) throws Exception{
		//根据子类获取全新的父类对象
		PMenu pMenu = (PMenu) ReflectUtils.getFatherPo(menu);
		if(operSign.equals(Constants.OP_A)){
			pMenu.setMenuId(Uuid.build());
			pMenu.setCreaterId(opUserId);
			pMenu.setCreateTime(StringUtils.getNowTimeMinStr());
			publicDao.add(pMenu);
		}else if(operSign.equals(Constants.OP_D)){
			publicDao.del(pMenu);
		}else if(operSign.equals(Constants.OP_U)){
			pMenu.setUpdaterId(opUserId);
			pMenu.setUpdateTime(StringUtils.getNowTimeMinStr());
			publicDao.upd(pMenu);
		}else if(operSign.equals(Constants.OP_F_M)){
			String upMenuId = menu.getMenuId();
			String hql = "from PMenu where upMenuId='"+upMenuId+"'";
			List<Object> list = publicDao.selectListByHql(hql);
			Cache.tempMap.clear();
			for(Object obj : list){
				PMenu m = (PMenu)obj;
				Cache.tempMap.put(m.getMenuLink(), m.getMenuId());
			}
			String [] funcs = menu.getMenuFuncs().split("\\|");
			System.out.println(menu.getMenuFuncs());
			for(String func : funcs){
				if(!Cache.tempMap.containsKey(func) && !"".equals(func)){
					PFunction pFunc = Cache.funcMap.get(func); 
					PMenu obj = new PMenu();
					obj.setMenuId(Uuid.build());
					obj.setMenuName(pFunc.getFuncName());
					obj.setMenuType(Constants.FUNC_MENU);
					obj.setMenuProp(menu.getMenuProp());
					obj.setMenuSort(pFunc.getFuncSort());
					obj.setMenuState(Constants.START);
					obj.setUpMenuId(upMenuId);
					obj.setMenuIcon(pFunc.getFuncIcon());
					obj.setMenuLink(func);
					obj.setMenuDesc(pFunc.getFuncDesc());
					obj.setCreaterId(opUserId);
					obj.setCreateTime(StringUtils.getNowTimeMinStr());
					publicDao.add(obj);
				}
			}
			for(String key : Cache.tempMap.keySet()){
				boolean bGet = false;
				for(String func : funcs){
					if(key.equals(func)){
						bGet = true;
						break;
					}
				}
				if(!bGet){
					String id = Cache.tempMap.get(key);
					publicDao.executeUpdate("delete PMenu where menuId=?", new String[]{id});
				}
			}
		}
		refreshCacheBiz.refreshSysCache();
	}
	
	@Override
	public void txRoleMenuOp(PmMenu menu,String opUserId) throws Exception{
		if(CheckUtils.isNull(menu.getMenuId()))return;
		List<String> roleMenus = Cache.roleMenuMap.get(menu.getRoleId());
		String [] menuIds = menu.getMenuId().split("\\|");
		if(Constants.START.equals(menu.getState())){
			for(String menuId : menuIds){
				if(roleMenus == null || !roleMenus.contains(menuId)){
					PRoleMenu roleMenu = new PRoleMenu();
					roleMenu.setRmId(Uuid.build());
					roleMenu.setMenuId(menuId);
					roleMenu.setRoleId(menu.getRoleId());
					publicDao.add(roleMenu);
				}
			}
		}else if(Constants.STOP.equals(menu.getState())){
			for(String menuId : menuIds){
				publicDao.executeUpdate("delete PRoleMenu where menuId=? and roleId=?", new String[]{menuId,menu.getRoleId()});
			}
		}
		refreshCacheBiz.refreshRoleMenuCache();
	}
	
	@Override
	public List<PmMenu> getMenus() throws Exception{
		List<Object> list = publicDao.selectListByHql("from PMenu order by menuType, menuSort");
		List<PmMenu> rtnList = new ArrayList<PmMenu>();
		Cache.tempMap.clear();
		for(Object obj : list){
			PMenu menu = (PMenu)obj;
			if(Constants.FUNC_MENU.equals(menu.getMenuType().trim())){
				String funcs = menu.getMenuLink();
				if(Cache.tempMap.containsKey(menu.getUpMenuId())){
					funcs = Cache.tempMap.get(menu.getUpMenuId()) + "|" + funcs;
				}
				Cache.tempMap.put(menu.getUpMenuId(), funcs);
			}else{
				rtnList.add(new PmMenu(menu));
			}
		}
		for(PmMenu menu:rtnList){
			if(Cache.tempMap.containsKey(menu.getMenuId())){
				menu.setMenuFuncs(Cache.tempMap.get(menu.getMenuId()));
			}
		}
		Cache.tempMap.clear();
		return rtnList;
	}
	
	@Override
	public List<PmMenu> getRunMenus() throws Exception{
		List<Object> list = publicDao.selectListByHql("from PMenu where menuState='1' order by menuType, menuSort");
		List<PmMenu> rtnList = new ArrayList<PmMenu>();
		for(Object obj : list){
			PMenu menu = (PMenu)obj;
			rtnList.add(new PmMenu(menu));
		}
		return rtnList;
	}
}
