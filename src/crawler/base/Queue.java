package crawler.base;

/**
 * @ClassName: Queue
 * @Description: 任务队列 基类 定义了4个 抽象方法 便于后续更换 队列实现
 * @author wkai
 * @date 2018年7月3日 下午11:38:39
 */
public interface Queue<T> {

	public void put(T t);

	public T get();

	public boolean empty();

	public int size();

}
