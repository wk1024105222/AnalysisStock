package wk.util.inf;

public interface Action<T> {

	public T getTask();

	public void handle(T t);

}
