package crawler.stock.macd.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.stock.macd.service.MacdService;
import crawler.stock.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.Result;
import crawler.util.inf.Action;
import crawler.util.inf.Task;

/***
 * 获取每支股票所有有效数据 发现MACD为空的 则计算MACD
 * 采用空间换性能 一次将一只股票所有有效数据select到内存 然后遍历，发现需要计算的交易数据时，方便获取上一日交易数据
 * 04.18 下载完04.10-04.17 的数据后，直接运行，到达预期结果 8500条 耗时8min
 * 
 * @author yt
 */
@Controller
public class ComputeMacdAction implements Action<StockInfo> {

	@Resource
	private MacdService macdService;

	@Resource
	private Task<StockInfo> allStocksTaskManager;

	public StockInfo getTask() {

		return allStocksTaskManager.getTask();
	}

	public void handle(StockInfo si) {

		Result rlt = macdService.saveMacd(si);
//		Result rlt = macdService.saveMacdCross(si.getCode());

		LogUtil.macd.info(rlt.getDes());

	}
}
