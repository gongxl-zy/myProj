package com.gxl.common;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectUtils {
	/**
	 * 根据子类实例创建父类实例Object
	 * @param childObj
	 * @return 父类实例
	 * @throws Exception
	 */
	public static Object getFatherPo(Object childObj){
		Class<?> faClass = childObj.getClass().getSuperclass();
		Object faObj = null;
		try{
			faObj = faClass.newInstance();
			Field[] fields = faClass.getDeclaredFields();
			String fieldName = "";
			for (Field fld : fields){
				fieldName = fld.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);//首字母转大写
				Method getMethod = faClass.getMethod("get"+fieldName);
				Method setMethod = faClass.getMethod("set"+fieldName, fld.getType());
				setMethod.invoke(faObj, getMethod.invoke(childObj));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return faObj;
	}
	
	/**
	 * 根据父类实例创建初始化子类实例
	 * @param childObj
	 * @param faObj
	 * @throws Exception
	 */
	public static void setChildPo(Object childObj,Object faObj){
		Class<?> faClass = faObj.getClass();
		Field[] fields = faClass.getDeclaredFields();
		String fieldName = "";
		try{
			for (Field fld : fields){
				fieldName = fld.getName();
				fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);//首字母转大写
				Method getMethod = faClass.getMethod("get"+fieldName);
				Method setMethod = faClass.getMethod("set"+fieldName, fld.getType());
				setMethod.invoke(childObj, getMethod.invoke(faObj));
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
