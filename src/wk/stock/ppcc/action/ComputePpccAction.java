package wk.stock.ppcc.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import wk.stock.ppcc.service.PpccService;
import wk.stock.vo.Ppcc;
import wk.util.LogUtil;
import wk.util.Result;
import wk.util.ResultFlag;
import wk.util.inf.Action;
import wk.util.inf.Task;

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
