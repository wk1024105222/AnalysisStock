package crawler.cases.stock.ppcc.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import crawler.base.Action;
import crawler.base.Task;
import crawler.cases.stock.ppcc.service.PpccService;
import crawler.cases.stock.vo.Ppcc;
import crawler.util.LogUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

/**
 * @ClassName: ComputePpccAction
 * @Description: 计算任意2支股票的相关系数
 * @author yt
 * @date 2015-5-16 下午01:33:54
 */
@Controller
public class ComputePpccAction implements Action<Ppcc> {

	@Resource
	private Task<Ppcc> ppccTaskManager;

	@Resource
	private PpccService ppccService;

	public Ppcc getTask() {

		return ppccTaskManager.getTask();
	}

	public void handle(Ppcc t) {

		Result rlt = ppccService.computePpcc(t);
		LogUtil.ppcc.info(rlt.getDes());
		if(rlt.getFlag() == ResultFlag.Success) {
			rlt = ppccService.updatePpcc(t);
			LogUtil.ppcc.info(rlt.getDes());
		}

	}

}
