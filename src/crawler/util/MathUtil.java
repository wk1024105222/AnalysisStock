package crawler.util;

import java.math.BigDecimal;

public class MathUtil {
	public static double sswr(double a, int num) {
		return  new BigDecimal(a).setScale(num, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	public static double sswr(double a) {
		return  new BigDecimal(a).setScale(4, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	
	
	

}
