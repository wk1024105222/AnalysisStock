package wk.fund.info.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wk.fund.info.dao.FundInfoDao;
import wk.fund.info.service.FundInfoService;
import wk.fund.vo.FundInfo;

@Service
public class FundInfoServiceImpl implements FundInfoService {
	
	@Resource
	private FundInfoDao fundInfoDao;

	public List<FundInfo> getAllFundInfo() {

		
		return fundInfoDao.getAllFundInfo();
	}

}
