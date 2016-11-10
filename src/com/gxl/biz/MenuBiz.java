package com.gxl.biz;

import java.util.List;

import com.gxl.entity.PUser;
import com.gxl.pm.po.PmMenu;

public interface MenuBiz {
	public List<PmMenu> getUserMenu(PUser user) throws Exception;

	public Long countMenus(PmMenu menu) throws Exception;

	public List<PmMenu> getMenus() throws Exception;
	
	public void txOpMenu(PmMenu menu,String operSign,String opUserId) throws Exception;
	
	public void txRoleMenuOp(PmMenu menu,String opUserId) throws Exception;
	
	/**
	 * 获取启用中的菜单
	 * @return
	 * @throws Exception
	 */
	public List<PmMenu> getRunMenus() throws Exception;
}
