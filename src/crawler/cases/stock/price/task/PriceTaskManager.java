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
 * 根据downPrice.type生成待下载股价信息任务队列<股票代码, 开始下载时间>
 * @ClassName: PriceTaskManager
 * @Description: 
 * @author wkai
 * @date 2018年4月8日 上午10:48:17
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
			//从雅虎下载缺失股价信息 数据库已存最大时间和当前时间 缺失部分
			downLackFromYahoo();
			break;
		case 1:
			//从新浪财经下载当日股价信息
			downTdyFromSina();
			break;
		case 2:
			//从新浪财经下载缺失股价信息
			downLackFromSina();
			break;
		case 3:
			//从雅虎下载所有股价信息
			downAllFromYahoo();
			break;
		case 4:
			//从新浪财经下载所有股价信息
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

		//获取数据库每支股票最大时间
		List tmp = priceService.getEveryStockLatestTxnDate();

		Object[] o = null;
		for(Iterator itr = tmp.iterator(); itr.hasNext();) {
			o = (Object[]) itr.next();
			if(today.before((Date) o[1])) {
				continue;
			}
			allTask.add(new DownDataTask((String) o[0], today));
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
