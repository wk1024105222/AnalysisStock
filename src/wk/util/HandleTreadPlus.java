package wk.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import wk.util.inf.Action;

@SuppressWarnings("unchecked")
public class HandleTreadPlus extends Thread {
	
	private Action action;

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	@Override
	public void run() {
		Object task = null;
		
		while ((task = action.getTask()) != null) {
				try {
					action.handle(task);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		//System.out.println(this.getId()+":"+this+":"+action);
		
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ApplicationContext b= new FileSystemXmlApplicationContext(System.getProperty("user.dir") + "\\src\\applicationContext.xml");  
//		AllStocksTaskManager c = (AllStocksTaskManager)b.getBean("allStocksTaskManager");
//		System.out.println(c.allTask.size());
		// -Xmx2000m -Xms2000m
		for (int i = 0; i != 10; i++) {
			HandleTreadPlus a = ((HandleTreadPlus) b.getBean("handleTreadPlus"));
			//System.out.println("----------"+a);
			a.start();
		}
	}
}
