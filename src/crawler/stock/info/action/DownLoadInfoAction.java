package crawler.stock.info.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.stock.info.service.StockInfoService;
import crawler.stock.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;
import crawler.util.inf.Action;
import crawler.util.inf.Task;

/***
 * �������й�˾��Ϣ
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
			LogUtil.info.info(si + "��Ϣ����,�������");
			return;
		}
		//���ݹ�Ʊ�����������˲ƾ� ��˾���ҳ��htlm 
		String url = stockInfoService.getSinaStockInfoUrl(si.getCode());
		//���ݹ�˾���url����html ������
		String html = NetUtil.getHtmlSourceByUrl(url, 10);
		
		//��html�н��� ��˾���� ����ʱ�� �����
		Result rt = stockInfoService.updateStockInfoFromSina(si,html);
		LogUtil.info.info(rt.getDes()); 
	}

}
