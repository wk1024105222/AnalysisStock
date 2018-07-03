package crawler.cases.fund.manager.dao;

import java.io.Serializable;

import crawler.cases.fund.vo.FundManager;
import crawler.cases.fund.vo.ManageHistory;


public interface FundManagerDao {

	Serializable saveManageHistory(ManageHistory manageHistory);

	Serializable saveFundManager(FundManager fundManager);

}
