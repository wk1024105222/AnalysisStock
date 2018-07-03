package crawler.cases.stock.task;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import crawler.base.Task;
import crawler.cases.stock.info.service.StockInfoService;
import crawler.cases.stock.vo.StockInfo;
import crawler.util.LogUtil;

@Component 
public class AllStocksTaskManager implements Task<StockInfo> {

	public LinkedList<StockInfo> allTask;

	@Resource
	private StockInfoService StockInfoService;

	@PostConstruct
	public void getAllTask() {

		allTask = new LinkedList<StockInfo>();

		List<StockInfo> tmp = StockInfoService.getAllStockInfo();

		for(StockInfo a : tmp) {
			allTask.add(a);
		}

		LogUtil.info.info("本次任务：" + tmp.size());

	}

	public synchronized StockInfo getTask() {

		StockInfo si = null;
		try {
			si = allTask.getFirst();

		} catch (NoSuchElementException e) {

			return null;
		}

		allTask.removeFirst();

		return si;
	}

}
