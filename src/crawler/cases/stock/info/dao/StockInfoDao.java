package crawler.cases.stock.info.dao;

import java.util.List;

import crawler.cases.stock.vo.StockInfo;


public interface StockInfoDao {

	public void updateStockInfo(StockInfo si);

	public List<StockInfo> getAllStockInfo();

}
