package crawler.cases.stocknew.price.dao;

import java.io.Serializable;
import java.util.List;

import crawler.cases.stocknew.vo.PriceDownLog;
import crawler.cases.stocknew.vo.StockData;

public interface PriceDao {

	public Serializable saveStockData(StockData sd);

	public void saveOrUpdateStockData(StockData sd);

	public Serializable savePriceDownLog(PriceDownLog priceDownLog);

	public List getSuccessLogBySeason(String code, String year, String season);

	public List<StockData> getPriceListByCode(String code);

	public StockData getPreTxnData(StockData td);
	
	public List<StockData> getAllPreTxnData(StockData td);

	public boolean isListedSecondDay(StockData td);

	public void updateStockData(StockData sd);

	public List getEveryStockLatestTxnDate();

	public List getComputeGainTask();

}
