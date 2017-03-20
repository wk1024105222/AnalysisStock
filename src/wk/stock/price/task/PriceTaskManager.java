package wk.stock.price.task;

import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import wk.stock.info.service.StockInfoService;
import wk.stock.price.service.PriceService;
import wk.stock.vo.StockInfo;
import wk.util.LogUtil;
import wk.util.inf.Task;

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
			downLackFromYahoo();
			break;
		case 1:
			downTdyFromSina();
			break;
		case 2:
			downLackFromSina();
			break;
		case 3:
			downAllFromYahoo();
			break;
		case 4:
			downAllFromSina();
			break;
		default:
			LogUtil.downtofile.info("参数taskType未配置");
		}
	}

	private void downAllFromSina() {

		List<StockInfo> tmp = stockInfoService.getAllStockInfo();

		for(StockInfo si : tmp) {

			allTask.add(new DownDataTask(si.getCode(), si.getListedDate()));
		}

		LogUtil.downtofile.info("本次任务：" + allTask.size());

	}

	private void downAllFromYahoo() {

		List<StockInfo> tmp = stockInfoService.getAllStockInfo();

		for(StockInfo si : tmp) {

			allTask.add(new DownDataTask(si.getCode(), si.getListedDate()));
		}

		LogUtil.downtofile.info("本次任务：" + allTask.size());

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

		LogUtil.downtofile.info("本次任务：" + allTask.size());

	}

	private void downTdyFromSina() {

		Date today = new Date();

		List tmp = priceService.getEveryStockLatestTxnDate();

		Object[] o = null;
		for(Iterator itr = tmp.iterator(); itr.hasNext();) {
			o = (Object[]) itr.next();
			if(today.before((Date) o[1])) {
				continue;
			}
			allTask.add(new DownDataTask((String) o[0], new Date()));
		}

		LogUtil.downtofile.info("本次任务：" + allTask.size());

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

		LogUtil.downtofile.info("本次任务：" + allTask.size());

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
