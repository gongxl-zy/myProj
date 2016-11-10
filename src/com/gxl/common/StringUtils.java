package com.gxl.common;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	public static String getTimeStr(Date d) {
    	if (d!=null){
    		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		return df.format(d);
    	}else
    		return "";
	}
	public static String getMinTimeStr(Date d) {
    	if (d!=null){
    		SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
    		return df.format(d);
    	}else
    		return "";
	}
	public static String getDateStr(Date d) {
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
    	if (d!=null)
    		return df.format(d);
    	else
    		return "";
	}
	
	public static String getNowTimeStr(){
		return getTimeStr(new Date());
	}
	
	public static String getNowTimeMinStr(){
		return getMinTimeStr(new Date());
	}
}
