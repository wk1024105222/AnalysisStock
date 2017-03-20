package wk.stock.info.service;

import java.util.List;

import wk.stock.vo.StockInfo;
import wk.util.Result;


public interface StockInfoService {

	public List<StockInfo> getAllStockInfo();

	public String getSinaStockInfoUrl(String code);

	public Result updateStockInfoFromSina(StockInfo si, String html);

}
