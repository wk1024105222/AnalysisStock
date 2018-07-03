package crawler.cases.fund.manager.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.fund.manager.sevice.FundManagerService;
import crawler.cases.fund.vo.FundInfo;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;

@Controller
public class DownFundManagerAction implements Action<FundInfo> {

	@Resource
	private Task<FundInfo> allFundsTaskManager;
	
	@Resource
	private FundManagerService fundManagerService;

	public FundInfo getTask() {

		return allFundsTaskManager.getTask();
	}

	public void handle(FundInfo t) {

		String url = fundManagerService.getTiantianManagerUrl(t.getCode());
		String html = NetUtil.getHtmlSourceByUrl(url, 10);
		
		Result rt = fundManagerService.saveManageHistoryFromTianTian(t.getCode(),html);
		LogUtil.downFunPrice.info(rt.getDes());
		
		rt = fundManagerService.saveFundManagerFromTiantian(t.getCode(),html);
		LogUtil.downFunPrice.info(rt.getDes());

	}

}
