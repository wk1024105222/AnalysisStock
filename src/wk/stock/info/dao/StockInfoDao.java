package wk.stock.info.dao;

import java.util.List;

import wk.stock.vo.StockInfo;


public interface StockInfoDao {

	public void updateStockInfo(StockInfo si);

	public List<StockInfo> getAllStockInfo();

}
