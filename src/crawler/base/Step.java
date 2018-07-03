package crawler.base;

import java.util.Map;

/**
 * 
 * @ClassName: Step
 * @Description: 爬虫步骤基础接口 包含3个方法
 * @author wkai
 * @date 2018年7月3日 下午11:45:04
 *
 */
public interface Step<T> {

	/**
	 * 
	 * @Title: getTask
	 * @Description: 获取一个任务
	 * @return T
	 * @throws
	 */
	public T getTask();

	/**
	 * 
	 * @Title: handle
	 * @Description: 针对获取任务完成处理
	 * @param t void
	 * @throws
	 */
	public void handle(T t);
	
	/**
	 * 
	 * @Title: isDone
	 * @Description: 任务是否处理过
	 * @param t
	 * @return boolean
	 * @throws
	 */
	public boolean isDone(T t);
	
//	/**
//	 * 
//	 * @Title: fillInputQueue
//	 * @Description: 初始化 inputQueue 只能执行一次
//	 * @param queueName void 队列名称
//	 * @throws
//	 */
//	public void fillInputQueue(String queueName);
//	
//	/**
//	 * 
//	 * @Title: fillDoneMap
//	 * @Description: 将处理过的任务填入Map
//	 * @return Map
//	 * @throws
//	 */
//	public Map fillDoneMap();

}
