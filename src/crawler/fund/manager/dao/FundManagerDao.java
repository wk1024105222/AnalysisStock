package crawler.fund.manager.dao;

import java.io.Serializable;

import crawler.fund.vo.FundManager;
import crawler.fund.vo.ManageHistory;


public interface FundManagerDao {

	Serializable saveManageHistory(ManageHistory manageHistory);

	Serializable saveFundManager(FundManager fundManager);

}
