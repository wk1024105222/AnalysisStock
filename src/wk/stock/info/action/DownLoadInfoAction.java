package wk.stock.info.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.stock.info.service.StockInfoService;
import wk.stock.vo.StockInfo;
import wk.util.LogUtil;
import wk.util.NetUtil;
import wk.util.Result;
import wk.util.inf.Action;
import wk.util.inf.Task;

/***
 * 下载上市公司信息
 * 
 * @author yt
 */
@Controller
public class DownLoadInfoAction implements Action<StockInfo> {

	@Resource
	private StockInfoService stockInfoService;

	@Resource
	private Task<StockInfo> allStocksTaskManager;

	public StockInfo getTask() {

		return allStocksTaskManager.getTask();
	}

	public void handle(StockInfo si) {
		if(si.getListedDate() != null && si.getName() != null) {
			LogUtil.info.info(si + "信息完整,无需更新");
			return;
		}
		String url = stockInfoService.getSinaStockInfoUrl(si.getCode());
		String html = NetUtil.getHtmlSourceByUrl(url, 10);
		
		Result rt = stockInfoService.updateStockInfoFromSina(si,html);
		LogUtil.info.info(rt.getDes()); 
	}

}
