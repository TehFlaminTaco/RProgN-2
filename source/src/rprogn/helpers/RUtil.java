package rprogn.helpers;

import java.math.BigDecimal;

public class RUtil {
	
	public static boolean is_number(String str){
		try{
			new BigDecimal(str);
			return true;
		}catch(NumberFormatException e){
			return false;
		}
	}
	
	public static boolean is_number_fast(String str){
		if(str.matches("^(-\\d+(\\.\\d+)?|\\d+(\\.\\d+)?|\\.\\d+)$")){
			return true;
		}
		return false;
	}
	
}
