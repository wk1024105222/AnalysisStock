package crawler.cases.fund.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import crawler.cases.fund.info.dao.FundInfoDao;
import crawler.cases.fund.info.service.FundInfoService;
import crawler.cases.fund.vo.FundInfo;

@Service
public class FundInfoServiceImpl implements FundInfoService {
	
	@Resource
	private FundInfoDao fundInfoDao;

	public List<FundInfo> getAllFundInfo() {

		
		return fundInfoDao.getAllFundInfo();
	}

}
