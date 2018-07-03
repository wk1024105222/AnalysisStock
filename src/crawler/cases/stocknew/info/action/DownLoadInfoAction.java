package crawler.cases.stocknew.info.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.stocknew.info.service.StockInfoService;
import crawler.cases.stocknew.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;

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
		//根据股票代码生成新浪财经 公司简介页面htlm 
		String url = stockInfoService.getSinaStockInfoUrl(si.getCode());
		//根据公司简介url下载html 待解析
		String html = NetUtil.getHtmlSourceByUrl(url, 10);
		
		//从html中解析 公司名称 上市时间 待入库
		Result rt = stockInfoService.updateStockInfoFromSina(si,html);
		LogUtil.info.info(rt.getDes()); 
	}

}
