package wk.stock.macd.action;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.stock.macd.service.MacdService;
import wk.stock.vo.StockInfo;
import wk.util.LogUtil;
import wk.util.Result;
import wk.util.inf.Action;
import wk.util.inf.Task;

/***
 * ��ȡÿ֧��Ʊ������Ч���� ����MACDΪ�յ� �����MACD
 * ���ÿռ任���� һ�ν�һֻ��Ʊ������Ч����select���ڴ� Ȼ�������������Ҫ����Ľ�������ʱ�������ȡ��һ�ս�������
 * 04.18 ������04.10-04.17 �����ݺ�ֱ�����У�����Ԥ�ڽ�� 8500�� ��ʱ8min
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
