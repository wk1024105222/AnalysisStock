package crawler.cases.stock.info.service;

import java.util.List;

import crawler.cases.stock.vo.StockInfo;
import crawler.util.Result;


public interface StockInfoService {

	public List<StockInfo> getAllStockInfo();

	public String getSinaStockInfoUrl(String code);

	public Result updateStockInfoFromSina(StockInfo si, String html);

}
