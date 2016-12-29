package com.gxl.biz.impl;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gxl.biz.RefreshCacheBiz;
import com.gxl.common.Cache;
import com.gxl.dao.PublicDao;
import com.gxl.entity.PDept;
import com.gxl.entity.PFunction;
import com.gxl.entity.PMenu;
import com.gxl.entity.PRole;
import com.gxl.entity.PRoleMenu;
import com.gxl.entity.PUser;
import com.gxl.entity.PWaitSearch;
import com.gxl.frd.po.Combo;
import com.gxl.pm.po.PmDept;
import com.gxl.pm.po.PmFunction;
import com.gxl.pm.po.PmMenu;
import com.gxl.pm.po.PmRole;
import com.gxl.pm.po.PmUser;

@Service(value="refreshCacheBiz")
public class RefreshCacheBizImpl implements RefreshCacheBiz{
	@Autowired private PublicDao publicDao;
	
	@Override
	public void refreshSysCache() throws Exception{
		Cache.sysMap.clear();
		Cache.userList.clear();
		Cache.roleList.clear();
		Cache.deptList.clear();
		Cache.menuList.clear();
		/*	刷新菜单名	*/
		List<Object> list = publicDao.selectListByHql("from PMenu order by menuType,menuSort");
		for(Object obj : list){
			PMenu temp = (PMenu)obj;
			Cache.sysMap.put(temp.getMenuId(), temp.getMenuName());
			Cache.menuList.add(new PmMenu(temp));
		}
		/*	刷新用户名	*/
		list = publicDao.selectListByHql("from PUser");
		for(Object obj : list){
			PUser temp = (PUser)obj;
			Cache.sysMap.put(temp.getUserId(), temp.getUserName());
			Cache.userList.add(new PmUser(temp));
		}
		/*	刷新角色名	*/
		list = publicDao.selectListByHql("from PRole");
		for(Object obj : list){
			PRole temp = (PRole)obj;
			Cache.sysMap.put(temp.getRoleId(), temp.getRoleName());
			Cache.roleList.add(new PmRole(temp));
		}
		/*	刷新部门名	*/
		list = publicDao.selectListByHql("from PDept order by deptLevel");
		for(Object obj : list){
			PDept temp = (PDept)obj;
			Cache.sysMap.put(temp.getDeptId(), temp.getDeptName());
			Cache.deptList.add(new PmDept(temp));
		}
		/*for(String key : Cache.sysMap.keySet()){
			System.out.println("ID:"+key+";中文名："+Cache.sysMap.get(key));
		}*/
	}
	
	@Override
	public void refreshFuncCache() throws Exception{
		Cache.funcMap.clear();
		List<Object> list = publicDao.selectListByHql("from PFunction order by funcSort");
		for(Object obj : list){
			PFunction temp = (PFunction)obj;
			Cache.funcMap.put(temp.getFuncCode(), new PmFunction(temp));
		}
		Cache.setFuncList();
	}
	
	@Override
	public void refreshRoleMenuCache() throws Exception{
		Cache.roleMenuMap.clear();
		List<Object> list = publicDao.selectListByHql("from PRoleMenu order by roleId");
		for(Object obj : list){
			PRoleMenu temp = (PRoleMenu)obj;
			if(Cache.roleMenuMap.containsKey(temp.getRoleId())){
				Cache.roleMenuMap.get(temp.getRoleId()).add(temp.getMenuId());
			}else{
				List<String> rmList = new ArrayList<String>();
				rmList.add(temp.getMenuId());
				Cache.roleMenuMap.put(temp.getRoleId(), rmList);
			}
		}
	}

	@Override
	public void refreshComboCache() throws Exception {
		Cache.comboMap.clear();
		String sql = "select option_set,option_key,option_name,option_par from p_option order by option_set,option_key";
		ResultSet rst = publicDao.query(sql);
		String opSet = "";
		while(rst.next()){
			Combo combo = new Combo(rst.getString(2),rst.getString(3),rst.getString(4));
			opSet = rst.getString(1);
			if(Cache.comboMap.containsKey(opSet)){
				Cache.comboMap.get(opSet).add(combo);
			}else{
				List<Combo> list = new ArrayList<Combo>();
				list.add(combo);
				Cache.comboMap.put(opSet, list);
			}
		}
	}

	@Override
	public void refreshAreaCache() throws Exception {
		Cache.vtlMbrIds.clear();
		Cache.waitIds.clear();
		ResultSet rs = publicDao.query("select mbr_id from p_mbr_info where type='2'");
		if(rs.next()){
			Cache.vtlMbrIds.add(rs.getString(0));
		}
		List<Object> list = publicDao.selectListByHql("from PWaitSearch order by wsDepth");
		for(Object obj : list){
			PWaitSearch temp = (PWaitSearch)obj;
			Cache.waitIds.add(temp.getWsUrl());
		}
	}
}
