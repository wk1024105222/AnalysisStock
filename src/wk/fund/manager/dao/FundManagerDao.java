package wk.fund.manager.dao;

import java.io.Serializable;

import wk.fund.vo.FundManager;
import wk.fund.vo.ManageHistory;


public interface FundManagerDao {

	Serializable saveManageHistory(ManageHistory manageHistory);

	Serializable saveFundManager(FundManager fundManager);

}
