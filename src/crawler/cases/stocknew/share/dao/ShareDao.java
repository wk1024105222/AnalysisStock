package crawler.cases.stocknew.share.dao;

import java.io.Serializable;

import crawler.cases.stocknew.vo.StockBonus;


public interface ShareDao {

	Serializable saveShareInfo(StockBonus sb);

}
