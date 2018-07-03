package crawler.cases.stocknew.gain.task;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import crawler.base.Task;
import crawler.cases.stocknew.price.service.PriceService;
import crawler.cases.stocknew.vo.StockInfo;
import crawler.util.LogUtil;

@Component
public class GainTaskManager implements Task<StockInfo> {

	public LinkedList<StockInfo> allTask = new LinkedList<StockInfo>();

	@Resource
	private PriceService priceService;

	@PostConstruct
	public void getAllTask() {

		List tmp = priceService.getComputeGainTask();

		for(Object a : tmp) {
			allTask.add(new StockInfo((String)a));
		}

		LogUtil.gain.info("本次任务：" + tmp.size());

	}

	public synchronized StockInfo getTask() {

		StockInfo si = null;
		try {
			si = allTask.getFirst();

		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return null;
		}

		allTask.removeFirst();
		return si;
	}

	public PriceService getPriceService() {
	
		return priceService;
	}

	public void setPriceService(PriceService priceService) {
	
		this.priceService = priceService;
	}
}
