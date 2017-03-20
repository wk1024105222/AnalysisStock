package wk.stock.bis.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.stock.bis.service.StockBisService;
import wk.stock.vo.StockInfo;
import wk.util.inf.Action;
import wk.util.inf.Task;

/***
 * 下载每只股票三张财务报表 并导入数据库
 * 导入注意单位 元 千元 万元 十万
 * 
 * @author yt
 */
@Controller
public class DownLoadBisAction implements Action<StockInfo> {

	@Resource
	private StockBisService stockBisService;

	@Resource
	private Task<StockInfo> allStocksTaskManager;

	public StockInfo getTask() {

		return allStocksTaskManager.getTask();
	}

	public void handle(StockInfo si) {

		stockBisService.saveIncome(stockBisService.downIncome(si));
		stockBisService.saveBalance(stockBisService.downBalance(si));
		stockBisService.saveCashFlow(stockBisService.downCashFlow(si));
	}
}
