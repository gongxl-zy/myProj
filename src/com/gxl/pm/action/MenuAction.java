package com.gxl.pm.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;

import com.gxl.biz.MenuBiz;
import com.gxl.common.BasePmAction;
import com.gxl.common.Cache;
import com.gxl.common.Constants;
import com.gxl.pm.po.PmMenu;

@Namespace("/pm/menu")
public class MenuAction extends BasePmAction{
	private static final long serialVersionUID = -4835283531740476947L;

	private PmMenu menu = new PmMenu();
	@Autowired private MenuBiz menuBiz;
	
	public PmMenu getMenu() {
		return menu;
	}
	public void setMenu(PmMenu menu) {
		this.menu = menu;
	}
	@Override
	public Object getModel() {
		return menu;
	}
	
	@Action(value = "init", results = {@Result(name = "success", location = "/pm/system/menu.jsp")})
	public String init(){
		// 存入到Session中
		getSession().setAttribute(Constants.FUNC_LIST, Cache.funcList);
		return SUCCESS;
	}
	
	@Action(value = "query")
	public void query(){
		try {
			List<PmMenu> menus = menuBiz.getMenus();
			this.returnResultJsonTree(menus);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "oper")
	public void oper(){
		try {
			menuBiz.txOpMenu(menu,getOperSign(),getCurUser().getUserId());
			returnResultJson("操作成功！",true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
