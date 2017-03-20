package wk.util;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class HandleTread extends Thread {
	public String actionClassName;

	public HandleTread(String actionClassName) {
		super();
		this.actionClassName = actionClassName;
	}

	@Override
	public void run() {
		Class<?> actionClass = null;
		Class<?> taskClass = null;
		Thread t = Thread.currentThread();
		Method getTaskClassName;

		Method getTask;

		Method taskEnd;

		Method handle;

		Method taskBegin;

		Object action;

		Object task;

		try {
			actionClass = Class.forName(actionClassName);

			action = actionClass.newInstance();

			getTaskClassName = actionClass.getMethod("getTaskClassName");

			taskClass = Class.forName((String) getTaskClassName.invoke(action));

			getTask = actionClass.getMethod("getTask");

			handle = actionClass.getMethod("handle", taskClass);

			while ((task = getTask.invoke(action)) != null) {
				try {
					handle.invoke(action, task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext a= new FileSystemXmlApplicationContext(System.getProperty("user.dir") + "\\src\\applicationContext.xml");  
		// -Xmx3072m -Xms3072m
		for (int i = 0; i != 1; i++) {
			// new HandleTread("wk.stock.input.ImportDBAction").start();
			// 计算MACD
			//new HandleTread("wk.stock.macd.ComputeAllStocksMacdAction").start();
			// 计算涨幅
			//new HandleTread("wk.stock.gain.ComputeAllStocksGainAction").start();
			//根据涨幅  跌幅找最大相似
			//new HandleTread("wk.stock.ppcc.ComputeAllStocksPPCCAction").start();
			
			//new HandleTread("wk.stock.down.DownLoadDataAction").start();
			
			//new HandleTread("wk.stock.input.ImportDBAction").start();
			
			new HandleTread("wk.stock.bis.DownLoadBISAction").start();
			
			
			
			

		}

	}

}
