package crawler.cases.fund.price.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.fund.price.service.FundPriceService;
import crawler.cases.fund.vo.FundInfo;
import crawler.util.FileUtil;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;

@Controller
public class DownFundPriceAction implements Action<FundInfo> {

	@Resource
	private Task<FundInfo> allFundsTaskManager;

	@Resource
	private FundPriceService fundPriceService;

	public FundInfo getTask() {

		return allFundsTaskManager.getTask();
	}

	public void handle(FundInfo t) {
		
		String url = fundPriceService.getHexunPriceUrl(t.getCode());
		String html = NetUtil.getHtmlSourceByUrl(url, 10);

		FileUtil.writeToFile(html, "D:\\fund\\"+t.getCode()+".html", false);
		LogUtil.downFunPrice.info(t.getCode()+"œ¬‘ÿÕÍ≥…");
//		Result rt = fundPriceService.saveFundPriceFromHexun(t.getCode(), html);
//		LogUtil.downFunPrice.info(rt.getDes());

	}

}
