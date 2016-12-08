package com.gxl.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gxl.pm.po.PmMenu;

public class MenuUtils {
	/**
	 * 返回菜单资源
	 * @param listAll
	 * @return
	 */
	public static List<PmMenu> getPmMenu(List<PmMenu> listAll){
		List<PmMenu> resource = new ArrayList<PmMenu>();
		for(PmMenu menu : listAll){
			if(!menu.getMenuType().equals(Constants.FUNC_MENU) && CheckUtils.isNull(menu.getUpMenuId())){
				resource.add(getMenuTree(menu,listAll));
			}
		}
		return resource;
	}
	
	/**
	 * 返回菜单树
	 * @param menu
	 * @param listAll
	 * @return
	 */
	private static PmMenu getMenuTree(PmMenu menu,List<PmMenu> listAll){
		PmMenu rtnMenu = menu.clone();
		for(PmMenu m : listAll){
			if(!m.getMenuType().equals(Constants.FUNC_MENU) && CheckUtils.isNotNull(m.getUpMenuId())
					&& m.getUpMenuId().equals(menu.getMenuId())){
				rtnMenu.getSubMenus().add(getMenuTree(m,listAll));
			}
		}
		return rtnMenu;
	}
	
	/**
	 * 返回功能权限
	 * @param listAll
	 * @return
	 */
	public static Map<String, List<PmMenu>> getLimit(List<PmMenu> listAll){
		Map<String, List<PmMenu>> limitMap = new HashMap<String, List<PmMenu>>();
		for(PmMenu menu : listAll){
			if(menu.getMenuType().equals(Constants.PROG_MENU)){
				limitMap.put(menu.getMenuId(), getFuncList(menu.getMenuId(),listAll));
			}
		}
		return limitMap;
	}
	
	/**
	 * 返回程序的功能列表
	 * @param parentId
	 * @param listAll
	 * @return
	 */
	private static List<PmMenu> getFuncList(String parentId,List<PmMenu> listAll){
		List<PmMenu> list = new ArrayList<PmMenu>();
		for(PmMenu menu : listAll){
			if(menu.getMenuType().equals(Constants.FUNC_MENU) && CheckUtils.isNotNull(menu.getUpMenuId())
					&& menu.getUpMenuId().equals(parentId)){
				list.add(menu);
			}
		}
		return list;
	}
}
