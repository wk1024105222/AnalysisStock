package crawler.fund.manager.sevice;

import crawler.util.Result;


public interface FundManagerService {

	Result saveManageHistoryFromTianTian(String code, String html);

	String getTiantianManagerUrl(String code);

	Result saveFundManagerFromTiantian(String code, String html);


}
