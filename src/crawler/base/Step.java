package crawler.base;

import java.util.Map;

/**
 * 
 * @ClassName: Step
 * @Description: ���沽������ӿ� ����3������
 * @author wkai
 * @date 2018��7��3�� ����11:45:04
 *
 */
public interface Step<T> {

	/**
	 * 
	 * @Title: getTask
	 * @Description: ��ȡһ������
	 * @return T
	 * @throws
	 */
	public T getTask();

	/**
	 * 
	 * @Title: handle
	 * @Description: ��Ի�ȡ������ɴ���
	 * @param t void
	 * @throws
	 */
	public void handle(T t);
	
	/**
	 * 
	 * @Title: isDone
	 * @Description: �����Ƿ����
	 * @param t
	 * @return boolean
	 * @throws
	 */
	public boolean isDone(T t);
	
//	/**
//	 * 
//	 * @Title: fillInputQueue
//	 * @Description: ��ʼ�� inputQueue ֻ��ִ��һ��
//	 * @param queueName void ��������
//	 * @throws
//	 */
//	public void fillInputQueue(String queueName);
//	
//	/**
//	 * 
//	 * @Title: fillDoneMap
//	 * @Description: �����������������Map
//	 * @return Map
//	 * @throws
//	 */
//	public Map fillDoneMap();

}
