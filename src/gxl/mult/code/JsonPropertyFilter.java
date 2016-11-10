package gxl.mult.code;

import net.sf.json.util.PropertyFilter;

public class JsonPropertyFilter implements PropertyFilter{
	public boolean apply(Object arg0, String arg1, Object arg2) {
        if (arg1.equals("rows") ||arg1.equals("total")) {
               return true;
           } else {
               return false;
           }
   }
}
