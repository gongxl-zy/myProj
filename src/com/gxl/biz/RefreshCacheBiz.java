package com.gxl.biz;

public interface RefreshCacheBiz {
	/**
	 * 用户、角色、部门、菜单ID<-->名缓存刷新
	 * @throws Exception
	 */
	public void refreshSysCache() throws Exception;
	
	/**
	 * 功能list刷新
	 * @throws Exception
	 */
	public void refreshFuncCache() throws Exception;
	
	/**
	 * 角色权限map刷新
	 * @throws Exception
	 */
	public void refreshRoleMenuCache() throws Exception;
	
	/**
	 * 拉列表map刷新
	 * @throws Exception
	 */
	public void refreshComboCache() throws Exception;
}
