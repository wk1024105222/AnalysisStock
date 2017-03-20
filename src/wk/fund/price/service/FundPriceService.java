package wk.fund.price.service;

import wk.util.Result;


public interface FundPriceService {

	Result saveFundPriceFromHexun(String code, String downFundPrice);

	String getHexunPriceUrl(String code);

}
