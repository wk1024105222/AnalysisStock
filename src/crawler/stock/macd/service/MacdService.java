package crawler.stock.macd.service;

import java.util.List;

import crawler.stock.vo.StockInfo;
import crawler.util.Result;


public interface MacdService {

	public Result saveMacd(StockInfo si);
	public Result saveMacdCross(String code);
	public List<String> getLsitWithputMacd(); 

}
