package gxl.mult.code;


/**
 * ID生成器
 * @author gongxl
 *
 */
public class Uuid {
    public static String build() {
    	String uuid = java.util.UUID.randomUUID().toString();
        return uuid.replaceAll("-", "");
    }
}
