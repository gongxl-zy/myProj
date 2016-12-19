package com.gxl.common;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.gxl.dao.PublicDao;
import com.gxl.entity.PArea;

public class SearchThread extends Thread{
	
	private static Map<String,String> map = new HashMap<String,String>();
	private static String baseUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/";
	private static Integer times;
	
	private PublicDao publicDao;
	
	public SearchThread(PublicDao publicDao){
		this.publicDao = publicDao;
	}
	@Override
	public void run() {
		Thread.currentThread();
		try {
			System.out.println("=========================启动搜索地区代码，30秒后开始工作========================");
			Thread.sleep(10000);//30秒后开始工作
		} catch (InterruptedException e) {
			e.printStackTrace();
		}//防止进程因长时间工作导致不稳
		times = 0;
		startGet(baseUrl+"index.html",0);
		
	}
	private void startGet(String url,int depth){
		int nextDepth = depth + 1;
		String nextUrl = "";
		String code = "";
		String value = "";
		String type = "";
		Node textnode = null;
		try {
			times ++;
			Thread.currentThread();
			Thread.sleep(50);//防止进程因长时间工作导致不稳
			System.out.println("=========================第（"+times+"）次通讯，url("+url+")========================");
			Parser parser = new Parser( (HttpURLConnection) (new URL(url)).openConnection());
			
			// 这里是控制测试的部分，后面的例子修改的就是这个地方。  
			if(depth < 4){
	            NodeFilter filter = new TagNameFilter("a");
	            NodeList nodes = parser.extractAllNodesThatMatch(filter);
	            if(nodes!=null) {  
	                for (int i = 0; i < nodes.size(); i++) {
	                    textnode = (Node) nodes.elementAt(i);
	                    nextUrl = getUrl(textnode.getText());
	                    if(!"".equals(nextUrl)){
	                		value = textnode.getFirstChild().getText();
	                    	if(depth == 0){
	                    		code = nextUrl.split("\\.")[0] + "0000000000";
	                    		if(!Cache.areaMap.containsKey(code)){
	                    			PArea area = new PArea(code,value,"0"+depth,"");
	                    			publicDao.add(area);
	                    			Cache.areaMap.put(code, value);
	                    		}
	                    		startGet(getBaseUrl(url)+nextUrl,nextDepth);
	                    	}else{
	                    		if(map.containsKey(nextUrl)){
	                    			code = map.get(nextUrl);
	                    			if(!Cache.areaMap.containsKey(code)){
		                    			PArea area = new PArea(code,value,"0"+depth,"");
		                    			publicDao.add(area);
		                    			Cache.areaMap.put(code, value);
		                    		}
	                    			startGet(getBaseUrl(url)+nextUrl,nextDepth);
	                    		}else{
	                    			map.put(nextUrl, value);
	                    		}
	                    	}
	                    }
	                }  
	            }
			}else{
				NodeFilter filter = new HasAttributeFilter("class","villagetr");
	            NodeList nodes = parser.extractAllNodesThatMatch(filter);
	            if(nodes!=null) {  
	                for (int i = 0; i < nodes.size(); i++) {
	                	textnode = (Node) nodes.elementAt(i);
	                	NodeList list = textnode.getChildren();
	                	code = list.elementAt(0).getFirstChild().getText();
	                	value = list.elementAt(2).getFirstChild().getText();
	                	type = list.elementAt(1).getFirstChild().getText();
	                	if(!Cache.areaMap.containsKey(code)){
                			PArea area = new PArea(code,value,"0"+depth,type);
                			publicDao.add(area);
                			Cache.areaMap.put(code, value);                			
                		}
	                }
	            }
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private String getBaseUrl(String str){
		String rtn = "";
		for(int i = str.length()-1;i >= 0 ; i --){
			if(str.charAt(i) == '/'){
				rtn = str.substring(0, i+1);
				break;
			}
		}
		return rtn;
	}
	
	/**
	 * 获取a标签中的href值
	 * @param str a标签node值
	 * @return
	 */
	private String getUrl(String str){
		String rtn = str.substring(0, 8);
		if("a href='".equals(rtn)){
			rtn = str.substring(8);
			rtn = rtn.substring(0, rtn.length()-1);
		}else{
			rtn = "";
		}
		return rtn;
	}
}
