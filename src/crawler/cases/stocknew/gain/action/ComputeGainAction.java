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
 * @Description: ��ȡÿ֧��Ʊ������Ч���� �����Ƿ�Ϊ�յ� ������Ƿ�
 *               ���ÿռ任���� һ�ν�һֻ��Ʊ������Ч����select���ڴ� Ȼ�������
 *               ������Ҫ����Ľ�������ʱ�������ȡ��һ�ս�������
 *               ������ɺ�ֱ��update�����ݿ�
 * @author yt
 * @date 2015-5-15 ����02:44:14
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
//			LogUtil.gain.info(si.getCode()+"�������");
//		} else {
//			LogUtil.gain.info(si.getCode()+"�������");
//		}
		

	}

}
