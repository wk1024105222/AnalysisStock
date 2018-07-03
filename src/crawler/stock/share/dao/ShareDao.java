package crawler.stock.share.dao;

import java.io.Serializable;

import crawler.stock.vo.StockBonus;


public interface ShareDao {

	Serializable saveShareInfo(StockBonus sb);

}
