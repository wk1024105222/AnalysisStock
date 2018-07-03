package crawler.cases.fund.price.dao;

import java.io.Serializable;
import java.util.List;

import crawler.cases.fund.vo.FundData;


public interface FundPriceDao {

	Serializable saveFundData(FundData fundData);

	List getMaxDateByCode(String code);

}
