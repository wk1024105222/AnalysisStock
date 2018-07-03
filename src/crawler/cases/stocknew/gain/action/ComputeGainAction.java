package crawler.cases.stocknew.gain.action;

import java.io.File;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.stocknew.gain.service.GainService;
import crawler.cases.stocknew.vo.StockInfo;
import crawler.util.FileUtil;
import crawler.util.LogUtil;
import crawler.util.Result;

/**
 * @ClassName: ComputeAllStocksGainAction
 * @Description: 获取每支股票所有有效数据 发现涨幅为空的 则计算涨幅
 *               采用空间换性能 一次将一只股票所有有效数据select到内存 然后遍历，
 *               发现需要计算的交易数据时，方便获取上一日交易数据
 *               计算完成后直接update到数据库
 * @author yt
 * @date 2015-5-15 下午02:44:14
 */
@Controller
public class ComputeGainAction implements Action<StockInfo> {

	@Resource
	private Task<StockInfo> gainTaskManager;
	//private Task<StockInfo> allStocksTaskManager;

	@Resource
	private GainService gainService;

	public StockInfo getTask() {

		return gainTaskManager.getTask();
	}

	public void handle(StockInfo si) {

		Result rt = gainService.savecalculationGain(si);
		LogUtil.gain.info(rt.getDes());
//		File b = new File("testfile\\"+si.getCode()+".txt");
//		if(!b.exists() ) {
//			String a = gainService.createGainStr(si);
//			FileUtil.writeToFile(a, "testfile\\"+si.getCode()+".txt", false);
//			LogUtil.gain.info(si.getCode()+"处理完成");
//		} else {
//			LogUtil.gain.info(si.getCode()+"处理完成");
//		}
		

	}

}
