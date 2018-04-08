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

	/**
	 * 0- ���Ż�����ȱʧ�ɼ���Ϣ ���ݿ��Ѵ����ʱ��͵�ǰʱ�� ȱʧ����
	 * 1-�����˲ƾ����ص��չɼ���Ϣ
	 * 2-�����˲ƾ�����ȱʧ�ɼ���Ϣ
	 * 3-���Ż��������йɼ���Ϣ
	 * 4-�����˲ƾ��������йɼ���Ϣ
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
			//1-�����˲ƾ����ص��չɼ���Ϣ 
			rt = priceService.savePriceFromSinaTdy(t.getCode(),priceService.downFromSinaTdy(t));
			LogUtil.downtofile.info(rt.getDes());
			break;
		}
		case 2: {
			//2-�����˲ƾ�����ȱʧ�ɼ���Ϣ
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
			LogUtil.downtofile.info("����downTypeδ����,�˳�����");
		}

	}

}
