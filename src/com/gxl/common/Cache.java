package com.gxl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxl.pm.po.PmDept;
import com.gxl.pm.po.PmFunction;
import com.gxl.pm.po.PmMenu;
import com.gxl.pm.po.PmRole;
import com.gxl.pm.po.PmUser;

public class Cache {
	public static Map<String,PmFunction> funcMap = new HashMap<String,PmFunction>();//功能清单
	public static List<PmFunction> funcList = new ArrayList<PmFunction>();//功能清单
	public static Map<String,String> sysMap = new HashMap<String,String>();//用户、角色、部门、菜单ID<-->名
	public static Map<String,String> tempMap = new HashMap<String,String>();//共享map
	public static List<PmUser> userList = new ArrayList<PmUser>();
	public static List<PmRole> roleList = new ArrayList<PmRole>();
	public static List<PmDept> deptList = new ArrayList<PmDept>();
	public static List<PmMenu> menuList = new ArrayList<PmMenu>();
	public static Map<String,List<String>> roleMenuMap = new HashMap<String,List<String>>();
	
	/**
	 * 获取角色的菜单
	 * @param roleId
	 * @return
	 */
	public static List<PmMenu> gerMenusByRoleId(String roleId){
		List<PmMenu> rtnList = new ArrayList<PmMenu>();
		List<String> roles = roleMenuMap.get(roleId);
		for(PmMenu menu : menuList){
			if(roles.contains(menu.getMenuId())){
				rtnList.add(menu);
			}
		}
		return rtnList;
	}
	
	/**
	 * 获取角色的菜单（包含全部菜单，只是state不同）
	 * @param roleId
	 * @return
	 */
	public static List<PmMenu> getRoleMenus(String roleId){
		List<String> roles = null;
		if(roleMenuMap.containsKey(roleId)){
			roles = roleMenuMap.get(roleId);
		}
		for(PmMenu menu : menuList){
			menu.setState(Constants.STOP);
			if(roles != null && roles.contains(menu.getMenuId())){
				menu.setState(Constants.START);
			}
		}
		return menuList;
	}
	public static List<PmUser> getUserList(String cond){
		if(cond == null)return userList;
		List<PmUser> rtnList = new ArrayList<PmUser>();
		for(PmUser user : userList){
			if(user.getUserNo().indexOf(cond) >= 0){
				rtnList.add(user);
			}else if(user.getUserName().indexOf(cond) >= 0){
				rtnList.add(user);
			}
		}
		return rtnList;
	}
	/**
	 * 设置功能相关的list和map
	 */
	public static void setFuncList(){
		int size = funcMap.size();
		List<PmFunction> list = new ArrayList<PmFunction>();
		for(String key:funcMap.keySet()){
			list.add(funcMap.get(key));
		}
		int k = 1;
		funcList.clear();
		for(int i = 0; i < size; i ++){
			int min = 100000;
			for(int j = 0; j < list.size(); j ++){
				String strSort = list.get(j).getFuncSort().trim();
				Integer sort = Integer.parseInt(strSort);
				if(min > sort){
					min = sort;
					k = j;
				}
			}
			funcList.add(list.get(k));
			list.remove(k);
		}
	}
	
	/**
	 * 根据ID获取name（用户、角色、部门、菜单）
	 * @param id
	 * @return
	 */
	public static String getName(String id){
		if(id == null)return "";
		if(sysMap.containsKey(id)){
			return sysMap.get(id);
		}else{
			return "";
		}
	}
}
