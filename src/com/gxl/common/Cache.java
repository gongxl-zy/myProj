package com.gxl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxl.frd.po.Combo;
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
	public static Map<String,List<String>> roleMenuMap = new HashMap<String,List<String>>();//角色权限map
	public static Map<String,List<Combo>> comboMap = new HashMap<String,List<Combo>>();//下拉列表map
	public static Map<String,String> areaMap = new HashMap<String,String>();//地区代码map
	public static Map<String,Integer> urlMap = new HashMap<String,Integer>();//链接map
	public static List<String> vtlMbrIds = new ArrayList<String>();//已录入的虚拟会员IDs
	public static List<String> waitIds = new ArrayList<String>();//待遍历的虚拟会员IDs
	
	/**
	 * 根据文本获取实际值
	 * @param opSet  类型
	 * @param opVal  文本值
	 * @return 实际值
	 */
	public static String getKeyByVal(String opSet,String opVal){
		String rtn = "2";
		if(comboMap.containsKey(opSet)){
			List<Combo> list = comboMap.get(opSet);
			for (Combo combo : list) {
				if(combo.getText().equals(opVal)){
					rtn = combo.getValue();
					break;
				}
			}
		}
		return rtn;
	}

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
