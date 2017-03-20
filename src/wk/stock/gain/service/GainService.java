package wk.stock.gain.service;

import java.util.List;

import wk.stock.vo.StockInfo;
import wk.util.Result;


public interface GainService {
	public Result savecalculationGain(StockInfo si);
	
	public String createGainStr(StockInfo si);
}
