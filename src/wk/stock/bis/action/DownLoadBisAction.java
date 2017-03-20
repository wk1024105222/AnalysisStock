package wk.stock.bis.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.stock.bis.service.StockBisService;
import wk.stock.vo.StockInfo;
import wk.util.inf.Action;
import wk.util.inf.Task;

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
