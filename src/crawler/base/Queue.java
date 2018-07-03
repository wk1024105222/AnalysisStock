package crawler.base;

/**
 * @ClassName: Queue
 * @Description: ������� ���� ������4�� ���󷽷� ���ں������� ����ʵ��
 * @author wkai
 * @date 2018��7��3�� ����11:38:39
 */
public interface Queue<T> {

	public void put(T t);

	public T get();

	public boolean empty();

	public int size();

}
