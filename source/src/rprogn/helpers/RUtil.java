package rprogn.helpers;

import java.math.BigDecimal;

import org.nevec.rjm.BigDecimalMath;

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
	
	public static BigDecimal optimiseDec(BigDecimal a){
		a=a.setScale(Math.max(a.scale(), 10));
		while(a.scale()>0&&a.multiply(BigDecimal.TEN.pow(a.scale())).remainder(BigDecimal.TEN).compareTo(BigDecimal.ZERO)==0){
			a=a.setScale(a.scale()-1);
		}
		return a;
	}
	
	public static BigDecimal pow(BigDecimal from, BigDecimal to){
		to=to.setScale(Math.max(to.scale(), from.scale())+10);
		BigDecimal out = BigDecimalMath.pow(from.setScale(to.scale()), to);
		return out.setScale(out.scale()-1, BigDecimal.ROUND_HALF_UP);
	}
	
	public static BigDecimal log(BigDecimal base, BigDecimal raised, int scale){
		BigDecimal out = BigDecimal.ZERO.setScale(scale);
		if(base.compareTo(BigDecimal.ONE)==-1){
			return new BigDecimal(0);
		}
		if(raised.compareTo(BigDecimal.ONE)==-1){
			return new BigDecimal(-1);
		}
		if(raised.compareTo(BigDecimal.ONE)==0){
			return BigDecimal.ZERO;
		}
		if(raised.compareTo(base)==0){
			return BigDecimal.ONE;
		}
		
		out = BigDecimalMath.log(raised.setScale(scale))
				.divide(BigDecimalMath.log(base.setScale(scale)),BigDecimal.ROUND_DOWN).setScale(scale,BigDecimal.ROUND_DOWN);
		return out.setScale(out.scale()-1, BigDecimal.ROUND_HALF_UP);
	}
	
}
