package wk.stock.ppcc.task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import wk.stock.info.service.StockInfoService;
import wk.stock.ppcc.service.PpccService;
import wk.stock.vo.Ppcc;
import wk.stock.vo.StockInfo;
import wk.util.LogUtil;
import wk.util.inf.Task;

@Component
public class PpccTaskManager implements Task<Ppcc> {

	public LinkedList<Ppcc> allTask = new LinkedList<Ppcc>();

	@Value("${ppcc.beginDate}")
	private String beginDate;

	@Value("${ppcc.endDate}")
	private String endDate;

	@Resource
	private PpccService ppccService;

	@Resource
	private StockInfoService stockInfoService;

	@PostConstruct
	public void getAllTask() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date begin = null;
		Date end = null;

		try {
			begin = sdf.parse(beginDate);
			end = sdf.parse(endDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		boolean isNewTask = ppccService.isNewComputePpccTask(begin, end);
		if(isNewTask) {// 新计算任务
			List<StockInfo> allStocks = stockInfoService.getAllStockInfo();
			Ppcc p = null;
			for(int i = 0; i != allStocks.size(); i++) {
				for(int j = i + 1; j != allStocks.size(); j++) {
					StockInfo a = allStocks.get(i);
					StockInfo b = allStocks.get(j);
					p = new Ppcc(a.getCode(), b.getCode(), begin, end);
					ppccService.savePpcc(p);
					allTask.add(p);
				}
			}
			LogUtil.ppcc.info("本次任务：" + (1 + allStocks.size()) * allStocks.size() / 2);
		} else {
			List<Ppcc> array = ppccService.getNotFinishPpcc(begin, end);
			for(Ppcc p : array) {
				allTask.add(p);
			}
			LogUtil.ppcc.info("本次任务：" + array.size());
		}
	}

	public Ppcc getTask() {

		Ppcc p = null;
		try {
			p = allTask.getFirst();
		} catch (NoSuchElementException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

		allTask.removeFirst();

		return p;
	}

}
