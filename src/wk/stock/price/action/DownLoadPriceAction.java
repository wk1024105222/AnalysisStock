package wk.stock.price.action;

import java.util.ArrayList;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

import wk.stock.price.service.PriceService;
import wk.stock.price.task.DownDataTask;
import wk.util.LogUtil;
import wk.util.Result;
import wk.util.inf.Action;
import wk.util.inf.Task;

@Controller
public class DownLoadPriceAction implements Action<DownDataTask> {

	@Value("${downPrice.type}")
	private String downType;

	@Resource
	private Task<DownDataTask> priceTaskManager;

	@Resource
	private PriceService priceService;

	public DownDataTask getTask() {

		return priceTaskManager.getTask();
	}

	/***
	 * 0:downLackFromYahoo();
	   1:downTdyFromSina();
	   2:downLackFromSina();
	   3:downAllFromYahoo();
       4:downAllFromSina();
	 * <p>Title: handle</p>
	 * <p>Description: </p>
	 * @param t
	 * @see wk.util.inf.Action#handle(java.lang.Object)
	 */
	public void handle(DownDataTask t) {
		Result rt = null;

		switch(Integer.parseInt(downType)) {
		case 0:{
			rt = priceService.savePriceFromYahoo(t.getCode(),priceService.downFromYahoo(t));
			LogUtil.downtofile.info(rt.getDes());
			break;
		}
		case 1: {
			rt = priceService.savePriceFromSinaTdy(t.getCode(),priceService.downFromSinaTdy(t));
			LogUtil.downtofile.info(rt.getDes());
			break;
		}
		case 2: {
			ArrayList<String> allHtmls = priceService.downFromSinaSeason(t);
			for(String a : allHtmls) {
				rt = priceService.savePriceFromSinaSeason(t.getCode(),t.getBegindate(), a);
				LogUtil.downtofile.info(rt.getDes());
			}

			break;
		}
		case 3: {
			rt = priceService.savePriceFromYahoo(t.getCode(),priceService.downFromYahoo(t));
			LogUtil.downtofile.info(rt.getDes());
			break;
		}
		case 4: {
			ArrayList<String> allHtmls = priceService.downFromSinaSeason(t);
			for(String a : allHtmls) {
				rt = priceService.savePriceFromSinaSeason(t.getCode(),t.getBegindate(), a);
				LogUtil.downtofile.info(rt.getDes());
			}

			break;
		}
		default:
			LogUtil.downtofile.info("参数downType未配置,退出运行");
		}

	}

}
