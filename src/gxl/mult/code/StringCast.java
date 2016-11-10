package gxl.mult.code;

/**
 * 表字段名与java属性名之间的转换
 * @author gongxl
 *
 */
public class StringCast {
	/**
	 * 将数据库中表的字段转换为实体类中的属性名称
	 * @param voName
	 * @return
	 */
	public static String toOntRowName(String voName) {
		StringBuffer sb = new StringBuffer();
 		boolean flag=false;
		for (int i = 0; i < voName.length(); i++) {
			char cur = voName.charAt(i);
			if(cur=='_'){
				flag=true;
			}else{
				if(flag){
					sb.append(Character.toUpperCase(cur));
					flag=false;
				}else{
					sb.append(Character.toLowerCase(cur));
				}
			}
		}
		return sb.toString();

	}

	/**
	 * 将实体类中的属性名称转换成数据库表中的字段
	 * @param voName
	 * @return
	 */
	public static String toInRowName(String voName) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < voName.length(); i++){
			char cur = voName.charAt(i);
			if(Character.isUpperCase(cur)){
				sb.append("_");
				sb.append(cur);
			}else{
				sb.append(cur);
			}
		}
		return sb.toString().toUpperCase();
	}
}
