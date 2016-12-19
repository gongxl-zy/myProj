package com.gxl.common;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.gxl.biz.RefreshCacheBiz;
import com.gxl.biz.ThreadBiz;

public class ServerStartListener implements ServletContextListener{
	private RefreshCacheBiz refreshCacheBiz;
	private ThreadBiz threadBiz;
	
	/**
	 * 服务退出
	 */
	@Override
	public void contextDestroyed(ServletContextEvent event) {
		//TODO 服务退出时运行
		System.out.println("服务器关闭时成功运行该文件!");
	}
	
	/**
	 * 服务初始化
	 */
	@Override
	public void contextInitialized(ServletContextEvent event) {
		WebApplicationContext springContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		if (springContext != null) {
			refreshCacheBiz = (RefreshCacheBiz)springContext.getBean("refreshCacheBiz");
			threadBiz = (ThreadBiz)springContext.getBean("threadBiz");
		} else {
			System.out.println("获取应用程序上下文失败!");
			return;
		}
		if(!initData()){
			System.out.println("初始化基础数据发送严重错误!请检查配置");
			System.exit(-1);
		}
	}
	
	/**
	 * 初始化数据
	 */
	private boolean initData() {
		if(refreshCacheBiz == null){
			return false;
		}
		try {
			refreshCacheBiz.refreshSysCache();
			refreshCacheBiz.refreshFuncCache();
			refreshCacheBiz.refreshRoleMenuCache();
			refreshCacheBiz.refreshComboCache();
			refreshCacheBiz.refreshAreaCache();
			Thread searchThread = new SearchThread(threadBiz);
			searchThread.start();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
