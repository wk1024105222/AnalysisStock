package crawler.fund.task;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import crawler.fund.info.service.FundInfoService;
import crawler.fund.vo.FundInfo;
import crawler.util.LogUtil;
import crawler.util.inf.Task;

@Service
public class AllFundsTaskManager implements Task<FundInfo> {

	public LinkedList<FundInfo> allTask = new LinkedList<FundInfo>();

	@Resource
	private FundInfoService fundInfoService;

	@PostConstruct
	public void getAllTask() {

		List<FundInfo> tmp = fundInfoService.getAllFundInfo();

		for(FundInfo fi : tmp) {

			allTask.add(fi);
		}

		LogUtil.downFunPrice.info("本次任务：" + allTask.size());
	}

	public synchronized FundInfo getTask() {

		FundInfo task = null;
		try {
			task = allTask.getFirst();

		} catch (NoSuchElementException e) {
			// e.printStackTrace();
			return null;
		}

		allTask.removeFirst();

		return task;
	}

}
