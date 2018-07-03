package crawler.stock.gain.service;

import java.util.List;

import crawler.stock.vo.StockInfo;
import crawler.util.Result;


public interface GainService {
	public Result savecalculationGain(StockInfo si);
	
	public String createGainStr(StockInfo si);
}
