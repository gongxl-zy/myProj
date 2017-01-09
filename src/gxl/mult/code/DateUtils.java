package gxl.mult.code;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	public static String getNowTimeStr(){
		String format = "yyyyMMddHHmmss";
		SimpleDateFormat sf = new SimpleDateFormat(format);
		return sf.format(new Date());
	}
}
