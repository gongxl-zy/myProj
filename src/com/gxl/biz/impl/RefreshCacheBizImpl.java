package com.gxl.biz.impl;

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
		/*	刷新菜单名	*/
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
		/*	刷新菜单名	*/
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
}
