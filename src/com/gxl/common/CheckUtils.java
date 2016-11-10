package com.gxl.common;

import java.math.BigDecimal;
import java.util.List;

public class CheckUtils {
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNull(String str){
		return str == null || "".equals(str);
	}
	/**
	 * 判断字符串是否为空
	 * @param str
	 * @return
	 */
	public static boolean isNotNull(String str){
		return str != null && !"".equals(str);
	}
	
	/**
	 * 判断List是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNull(List<Object> list){
		return list == null || list.isEmpty();
	}
	/**
	 * 判断List是否为空
	 * @param list
	 * @return
	 */
	public static boolean isNotNull(List<Object> list){
		return list != null && !list.isEmpty();
	}
	
	/**
	 * 判断Integer是否为空
	 * @param itg
	 * @return
	 */
	public static boolean isNull(Integer itg){
		return itg == null;
	}
	/**
	 * 判断Integer是否为空
	 * @param itg
	 * @return
	 */
	public static boolean isNotNull(Integer itg){
		return itg != null;
	}
	
	/**
	 * 判断BigDecimal是否为空
	 * @param bdg
	 * @return
	 */
	public static boolean isNull(BigDecimal bdg){
		return bdg == null;
	}
	/**
	 * 判断BigDecimal是否为空
	 * @param bdg
	 * @return
	 */
	public static boolean isNotNull(BigDecimal bdg){
		return bdg != null;
	}
}
