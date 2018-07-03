package crawler.cases.stock.bis.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.stock.bis.service.StockBisService;
import crawler.cases.stock.vo.StockInfo;

/***
 * ����ÿֻ��Ʊ���Ų��񱨱� ���������ݿ�
 * ����ע�ⵥλ Ԫ ǧԪ ��Ԫ ʮ��
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
