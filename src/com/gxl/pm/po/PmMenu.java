package com.gxl.pm.po;

import java.util.ArrayList;
import java.util.List;

import com.gxl.common.Cache;
import com.gxl.entity.PMenu;

public class PmMenu extends PMenu{
	private static final long serialVersionUID = 6482497776873318761L;
	private String roleId;//角色ID
	private String state;//角色的权限状态0-未开启；1-已开启
	private String menuFuncs;//功能清单
	private List<PmMenu> subMenus = new ArrayList<PmMenu>();//子菜单列表
	
	public String getUpMenuName() {//父菜单名称
		return Cache.getName(getUpMenuId());
	}
	public String getCreaterName() {//创建人姓名
		return Cache.getName(getCreaterId());
	}
	public String getUpdaterName() {//更新人姓名
		return Cache.getName(getUpdaterId());
	}
	
	public PmMenu(){
		super();
	}
	public PmMenu(PMenu menu) {
		setMenuId(menu.getMenuId());
		setMenuName(menu.getMenuName());
		setMenuType(menu.getMenuType());
		setMenuProp(menu.getMenuProp());
		setMenuSort(menu.getMenuSort());
		setMenuState(menu.getMenuState());
		setUpMenuId(menu.getUpMenuId());
		setMenuIcon(menu.getMenuIcon());
		setMenuLink(menu.getMenuLink());
		setMenuDesc(menu.getMenuDesc());
		setCreaterId(menu.getCreaterId());
		setCreateTime(menu.getCreateTime());
		setUpdaterId(menu.getUpdaterId());
		setUpdateTime(menu.getUpdateTime());
	}
	public List<PmMenu> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<PmMenu> subMenus) {
		this.subMenus = subMenus;
	}
	public String getMenuFuncs() {
		return menuFuncs;
	}
	public void setMenuFuncs(String menuFuncs) {
		this.menuFuncs = menuFuncs;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	
}
