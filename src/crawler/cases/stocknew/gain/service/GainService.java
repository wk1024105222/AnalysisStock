package crawler.cases.stocknew.gain.service;

import java.util.List;

import crawler.cases.stocknew.vo.StockInfo;
import crawler.util.Result;


public interface GainService {
	public Result savecalculationGain(StockInfo si);
	
	public String createGainStr(StockInfo si);
}
