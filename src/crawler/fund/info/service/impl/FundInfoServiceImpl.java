package crawler.fund.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import crawler.fund.info.dao.FundInfoDao;
import crawler.fund.info.service.FundInfoService;
import crawler.fund.vo.FundInfo;

@Service
public class FundInfoServiceImpl implements FundInfoService {
	
	@Resource
	private FundInfoDao fundInfoDao;

	public List<FundInfo> getAllFundInfo() {

		
		return fundInfoDao.getAllFundInfo();
	}

}
