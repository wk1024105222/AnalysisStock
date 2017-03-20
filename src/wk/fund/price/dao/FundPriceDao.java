package wk.fund.price.dao;

import java.io.Serializable;
import java.util.List;

import wk.fund.vo.FundData;


public interface FundPriceDao {

	Serializable saveFundData(FundData fundData);

	List getMaxDateByCode(String code);

}
