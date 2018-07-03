package crawler.cases.stocknew.info.dao;

import java.util.List;

import crawler.cases.stocknew.vo.StockInfo;


public interface StockInfoDao {

	public void updateStockInfo(StockInfo si);

	public List<StockInfo> getAllStockInfo();

}
