package wk.fund.manager.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.fund.manager.sevice.FundManagerService;
import wk.fund.vo.FundInfo;
import wk.util.LogUtil;
import wk.util.NetUtil;
import wk.util.Result;
import wk.util.inf.Action;
import wk.util.inf.Task;

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
