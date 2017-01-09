package com.gxl.common;

import com.gxl.biz.ThreadBiz;
import com.gxl.common.thread.VtlMbrTask;

public class SearchThread extends Thread{
	
	private ThreadBiz threadBiz;
	
	public SearchThread(ThreadBiz threadBiz){
		this.threadBiz = threadBiz;
	}
	@Override
	public void run() {
		System.out.println("=========================启动新进程自动抓取网络资源，10秒后开始工作========================");
		try {
			Thread.currentThread();
			Thread.sleep(10000);//10秒后开始工作
			//new VtlMbrTask(threadBiz);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
