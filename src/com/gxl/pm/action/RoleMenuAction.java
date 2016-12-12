package com.gxl.pm.action;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.MenuBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.pm.po.PmMenu;

@Namespace("/pm/roleMenu")
public class RoleMenuAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmMenu menu = new PmMenu();
	@Autowired private MenuBiz menuBiz;
	
	public PmMenu getMenu(){
		return menu;
	}
	public void setMenu(PmMenu menu) {
		this.menu = menu;
	}
	@Override
	public Object getModel() {
		return menu;
	}
	
	@Action(value = "", results = {@Result(name = "success", location = "/pm/system/roleMenu.jsp")})
	public String init(){
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			this.returnResultJsonTree(Cache.getRoleMenus(menu.getRoleId()));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "config")
	public void config(){
		try {
			menuBiz.txRoleMenuOp(menu,getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
