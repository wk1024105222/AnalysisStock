package crawler.cases.stock.gain.service;

import java.util.List;

import crawler.cases.stock.vo.StockInfo;
import crawler.util.Result;


public interface GainService {
	public Result savecalculationGain(StockInfo si);
	
	public String createGainStr(StockInfo si);
}
