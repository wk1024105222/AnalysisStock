package crawler.cases.fund.task;

import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import crawler.base.Task;
import crawler.cases.fund.info.service.FundInfoService;
import crawler.cases.fund.vo.FundInfo;
import crawler.util.LogUtil;

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

		LogUtil.downFunPrice.info("��������" + allTask.size());
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
