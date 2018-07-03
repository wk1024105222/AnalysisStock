package crawler.base;

/**
 * @ClassName: BaseStep
 * @Description: ���沽������� ���������ݽṹ
 *               ����3������ʹ�� String
 * @author wkai
 * @date 2018��7��3�� ����11:39:00
 */
public abstract class BaseStep<T> implements Step<T> {

	// �����������
	private String inputQueueName;

	// ����������
	private String outputQueueName;

	// �첽 ������ ����Ϊ��
	private String dbQueueName;

	// ��ȡ�����ʱ �Ⱥ�ʱ�� ms
	private int emptyWait;

	// ��������ʱ���� ms
	private int requestWait;

	public BaseStep() {

		super();
	}

	public BaseStep(String inputQueueName, String outputQueueName, String dbQueueName, int emptyWait, int requestWait) {

		super();
		this.inputQueueName = inputQueueName;
		this.outputQueueName = outputQueueName;
		this.dbQueueName = dbQueueName;
		this.emptyWait = emptyWait;
		this.requestWait = requestWait;
	}

}
