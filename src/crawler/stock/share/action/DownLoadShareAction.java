package crawler.stock.share.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.stock.share.service.ShareService;
import crawler.stock.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.Result;
import crawler.util.inf.Action;
import crawler.util.inf.Task;

/***
 * @ClassName: DownLoadShareAction
 * @Description: 下载送股 增股 派息 信息
 * @author yt
 * @date 2015-5-19 上午08:33:48
 */
@Controller
public class DownLoadShareAction implements Action<StockInfo> {

	@Resource
	private ShareService shareService;

	@Resource
	private Task<StockInfo> allStocksTaskManager;

	public StockInfo getTask() {

		return allStocksTaskManager.getTask();
	}

	public void handle(StockInfo si) {

		Result rlt = shareService.saveShareInfo(si);

		LogUtil.share.info(rlt.getDes());
	}

}
