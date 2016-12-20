package com.gxl.common.thread;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.HasAttributeFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;

import com.gxl.biz.ThreadBiz;
import com.gxl.common.Cache;

public class AreaCodeTask {
	private static Map<String,String> map = new HashMap<String,String>();
	private static String startUrl = "http://www.stats.gov.cn/tjsj/tjbz/tjyqhdmhcxhfdm/2013/index.html";
	private static Integer maxRecs = 1500;
	private List<String> areaList;
	private Integer times;
	private Integer recs;
	private Integer doRecs;
	
	private ThreadBiz threadBiz;
	
	public AreaCodeTask(ThreadBiz threadBiz){
		this.threadBiz = threadBiz;
		this.areaList = new ArrayList<String>();
		this.times = 0;
		this.recs = 0;
		this.doRecs = 0;
		this.run();
	}
	
	private void run() {
		if(Cache.urlMap.isEmpty()){
			Cache.urlMap.put(startUrl, 0);
		}
		while(!Cache.urlMap.isEmpty()){
			try {
				Thread.currentThread();
				Thread.sleep(50);//睡眠防止卡死
				for(String key : Cache.urlMap.keySet()){
					if(Cache.urlMap.containsValue(4)){
						if(Cache.urlMap.get(key) ==4){
							doGet(key);
							break;
						}
					}else if(Cache.urlMap.containsValue(3)){
						if(Cache.urlMap.get(key) ==3){
							doGet(key);
							break;
						}
					}else if(Cache.urlMap.containsValue(2)){
						if(Cache.urlMap.get(key) ==2){
							doGet(key);
							break;
						}
					}else if(Cache.urlMap.containsValue(1)){
						if(Cache.urlMap.get(key) ==1){
							doGet(key);
							break;
						}
					}else if(Cache.urlMap.containsValue(0)){
						if(Cache.urlMap.get(key) ==0){
							doGet(key);
							break;
						}
					}
				}
				if(recs >= maxRecs){
					System.out.println("=========================正在批量录入"+recs+"条数据========================");
					threadBiz.txAddAreaCode(areaList);
					areaList.clear();
					System.out.println("=========================录入成功========================");
					doRecs = doRecs + recs;
					recs = 0;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if(recs > 0){
			System.out.println("=========================正在批量录入"+recs+"条数据========================");
			try {
				threadBiz.txAddAreaCode(areaList);
			} catch (Exception e) {
				e.printStackTrace();
			}
			areaList.clear();
			System.out.println("=========================全部录入成功========================");
			doRecs = doRecs + recs;
			recs = 0;
		}
		
	}
	private void doGet(String key){
		times ++;
		System.out.println("=========================第（"+times+"）次通讯,已录入"+doRecs+"条数据，url("+key+")========================");
		doGet(key,Cache.urlMap.get(key));
	}
	
	private void addAreaList(String areaCode, String areaName, String areaLevel,String areaType){
		areaList.add(areaCode);
		areaList.add(areaName);
		areaList.add(areaLevel);
		areaList.add(areaType);
		recs ++;
	}

	private void doGet(String url,int depth){
		int nextDepth = depth + 1;
		String nextUrl = "";
		String code = "";
		String value = "";
		String type = "";
		Node textnode = null;
		Cache.urlMap.remove(url);
		try {
			Parser parser = new Parser( (HttpURLConnection) (new URL(url)).openConnection());
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
	                    			addAreaList(code,value,"0"+depth,"");
	                    			//Cache.areaMap.put(code, value);
	                    			Cache.urlMap.put(getBaseUrl(url)+nextUrl, nextDepth);
	                    		}
	                    	}else{
	                    		if(map.containsKey(nextUrl)){
	                    			code = map.get(nextUrl);
	                    			if(!Cache.areaMap.containsKey(code)){
	                    				addAreaList(code,value,"0"+depth,"");
		                    			//Cache.areaMap.put(code, value);
		                    			Cache.urlMap.put(getBaseUrl(url)+nextUrl, nextDepth);
		                    		}
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
	                		addAreaList(code,value,"0"+depth,type);
                			//Cache.areaMap.put(code, value);                			
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