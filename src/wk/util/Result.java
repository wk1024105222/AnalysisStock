package wk.util;

public class Result {
	private int flag;
	private Object des;

	public Result() {
		super();
	}

	public Result(int flag, Object des) {
		super();
		this.flag = flag;
		this.des = des;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Object getDes() {
		return des;
	}

	public void setDes(Object des) {
		this.des = des;
	}

}
