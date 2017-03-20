package wk.stock.macd.service;

import java.util.List;

import wk.stock.vo.StockInfo;
import wk.util.Result;


public interface MacdService {

	public Result saveMacd(StockInfo si);
	public Result saveMacdCross(String code);
	public List<String> getLsitWithputMacd(); 

}
