package wk.util;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class MyRobot {
	public Robot robot;
	
	public MyRobot() {
		try {
			this.robot = new Robot();
		} catch (AWTException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/** 
	 * 等候
	 * @param ms 毫秒
	 */
	public void wait(int ms) { 
		robot.delay(ms);
	}
	
	/**
	 * 在指定坐标点击左键
	 * @param x
	 * @param y
	 */
	public void pressMouseLeft(int x, int y) { 
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON1_MASK );
		robot.mouseRelease(InputEvent.BUTTON1_MASK );
	}
	
	/**
	 * 在指定坐标点击鼠标右键
	 * @param x
	 * @param y
	 */
	public void pressMouseRight(int x, int y) { 
		robot.mouseMove(x, y);
		robot.mousePress(InputEvent.BUTTON3_MASK );
		robot.mouseRelease(InputEvent.BUTTON3_MASK );
	}
	
	/**
	 * 点击按键
	 * @param keyvalue
	 */
	public void pressKey(int keyvalue) {
		robot.keyPress(keyvalue); //
		robot.keyRelease(keyvalue);
	} 

	/**
	 * ctrl + 按键一起按
	 * @param keyvalue
	 */
	public void pressKeyWithCtrl(int keyvalue) { 
		robot.keyPress(KeyEvent.VK_CONTROL);
		robot.keyPress(keyvalue); 
		robot.keyRelease(keyvalue);
		robot.keyRelease(KeyEvent.VK_CONTROL);
	} 
	
	/**
	 * alt + 按键一起按
	 * @param keyvalue
	 */
	public void pressKeyWithAlt(int keyvalue) { 
		robot.keyPress(KeyEvent.VK_ALT);
		robot.keyRelease(KeyEvent.VK_ALT);
		robot.keyPress(keyvalue);
		robot.keyRelease(keyvalue);
	} 
}
