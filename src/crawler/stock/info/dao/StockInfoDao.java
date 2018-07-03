package crawler.stock.info.dao;

import java.util.List;

import crawler.stock.vo.StockInfo;


public interface StockInfoDao {

	public void updateStockInfo(StockInfo si);

	public List<StockInfo> getAllStockInfo();

}
