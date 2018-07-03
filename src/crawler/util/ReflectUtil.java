package crawler.util;

import java.lang.reflect.Field;

import crawler.stock.vo.StockData;

public class ReflectUtil {
	public static String toString(Object o) {
		Class objectClass = null;
		Field[] allFields = null;
		String rlt = "";
		try {
			objectClass = o.getClass();

			allFields = objectClass.getDeclaredFields();
			for (Field f : allFields) {
				f.setAccessible(true);
				rlt += f.getName() + ":" + f.get(o) + "\t";
			}
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rlt;
	}
	
	public static void main(String[] args) {
		System.out.println(ReflectUtil.toString(new StockData()));
	}
}
