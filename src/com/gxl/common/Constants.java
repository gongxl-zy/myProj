package com.gxl.common;

public class Constants {
	public static String getDesc(String key){
		return key;
	}
	public static final String PM_USER = "pmUser";
	public static final String FUNC_LIST = "funcList";
	public static final Integer CHKWAY = 2;
	

	public static final String MODEL_MENU = "1";//模块
	public static final String PROG_MENU = "2";//程序
	public static final String FUNC_MENU = "3";//功能
	

	public static final Integer JDBC_UPDATE = 1;//更新操作
	public static final Integer JDBC_QUERY = 2;//查询操作
	public static final Integer JDBC_BATCH = 3;//批量操作
	
	public static final String PM_MENU = "pmMenu";//平台菜单
	public static final String PM_LIMIT = "pmLimit";//平台权限
	
	public static final String OP_A = "add";//新增
	public static final String OP_D = "del";//删除
	public static final String OP_U = "edit";//更新
	public static final String OP_R = "refresh";//刷新
	public static final String OP_F_M = "funcMng";//功能管理

	public static final String START= "1";//启用
	public static final String STOP = "0";//停用
	
	public static final String FULL_QUERY= "1";//全查询
	public static final String SUB_QUERY = "2";//子查询
	
}
