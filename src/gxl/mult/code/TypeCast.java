package gxl.mult.code;
/**
 * 类型转换
 * @author gongxl
 *
 */
public class TypeCast {
	public static int longToInt(Long l){
		String str = l.toString();
		return Integer.parseInt(str);
	}
}
