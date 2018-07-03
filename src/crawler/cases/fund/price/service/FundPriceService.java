package crawler.cases.fund.price.service;

import crawler.util.Result;


public interface FundPriceService {

	Result saveFundPriceFromHexun(String code, String downFundPrice);

	String getHexunPriceUrl(String code);

}
