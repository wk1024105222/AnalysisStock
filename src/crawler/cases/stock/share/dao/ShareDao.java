package crawler.cases.stock.share.dao;

import java.io.Serializable;

import crawler.cases.stock.vo.StockBonus;


public interface ShareDao {

	Serializable saveShareInfo(StockBonus sb);

}
