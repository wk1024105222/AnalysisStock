package crawler.cases.stock.price.task;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import crawler.base.Task;
import crawler.cases.stock.info.service.StockInfoService;
import crawler.cases.stock.price.service.PriceService;
import crawler.cases.stock.vo.StockInfo;
import crawler.util.LogUtil;

/***
 * ����downPrice.type���ɴ����عɼ���Ϣ�������<��Ʊ����, ��ʼ����ʱ��>
 * @ClassName: PriceTaskManager
 * @Description: 
 * @author wkai
 * @date 2018��4��8�� ����10:48:17
 *
 */
@Component
public class PriceTaskManager implements Task<DownDataTask> {

	public LinkedList<DownDataTask> allTask = new LinkedList<DownDataTask>();

	@Value("${downPrice.type}")
	public String taskType = "";

	@Resource
	private PriceService priceService;
	
	@Resource
	private StockInfoService stockInfoService;

	@PostConstruct
	public void getAllTask() {

		switch(Integer.parseInt(taskType)) {
		case 0:
			//���Ż�����ȱʧ�ɼ���Ϣ ���ݿ��Ѵ����ʱ��͵�ǰʱ�� ȱʧ����
			downLackFromYahoo();
			break;
		case 1:
			//�����˲ƾ����ص��չɼ���Ϣ
			downTdyFromSina();
			break;
		case 2:
			//�����˲ƾ�����ȱʧ�ɼ���Ϣ
			downLackFromSina();
			break;
		case 3:
			//���Ż��������йɼ���Ϣ
			downAllFromYahoo();
			break;
		case 4:
			//�����˲ƾ��������йɼ���Ϣ
			downAllFromSina();
			break;
		default:
			LogUtil.downtofile.info("����taskTypeδ����");
		}
	}

	private void downAllFromSina() {

		List<StockInfo> tmp = stockInfoService.getAllStockInfo();

		for(StockInfo si : tmp) {

			allTask.add(new DownDataTask(si.getCode(), si.getListedDate()));
		}

		LogUtil.downtofile.info("��������" + allTask.size());

	}

	private void downAllFromYahoo() {

		List<StockInfo> tmp = stockInfoService.getAllStockInfo();

		for(StockInfo si : tmp) {

			allTask.add(new DownDataTask(si.getCode(), si.getListedDate()));
		}

		LogUtil.downtofile.info("��������" + allTask.size());

	}

	private void downLackFromSina() {

		Date today = new Date();

		List tmp = priceService.getEveryStockLatestTxnDate();

		Object[] o = null;
		for(Iterator itr = tmp.iterator(); itr.hasNext();) {
			o = (Object[]) itr.next();
			if(today.before((Date) o[1])) {
				continue;
			}
			allTask.add(new DownDataTask((String) o[0], (Date) o[1]));
		}

		LogUtil.downtofile.info("��������" + allTask.size());

	}

	private void downTdyFromSina() {

		Date today = new Date();

		//��ȡ���ݿ�ÿ֧��Ʊ���ʱ��
		List tmp = priceService.getEveryStockLatestTxnDate();

		Object[] o = null;
		for(Iterator itr = tmp.iterator(); itr.hasNext();) {
			o = (Object[]) itr.next();
			if(today.before((Date) o[1])) {
				continue;
			}
			allTask.add(new DownDataTask((String) o[0], today));
		}

		LogUtil.downtofile.info("��������" + allTask.size());

	}

	private void downLackFromYahoo() {

		Date today = new Date();

		List tmp = priceService.getEveryStockLatestTxnDate();

		Object[] o = null;
		for(Iterator itr = tmp.iterator(); itr.hasNext();) {
			o = (Object[]) itr.next();
			if(today.before((Date) o[1])) {
				continue;
			}
			allTask.add(new DownDataTask((String) o[0], (Date) o[1]));
		}

		LogUtil.downtofile.info("��������" + allTask.size());

	}

	public synchronized DownDataTask getTask() {

		DownDataTask task = null;
		try {
			task = allTask.getFirst();

		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return null;
		}

		allTask.removeFirst();

		return task;
	}

	public String getTaskType() {

		return taskType;
	}

	public void setTaskType(String taskType) {

		this.taskType = taskType;
	}
}
