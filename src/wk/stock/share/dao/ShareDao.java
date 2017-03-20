package wk.stock.share.dao;

import java.io.Serializable;

import wk.stock.vo.StockBonus;


public interface ShareDao {

	Serializable saveShareInfo(StockBonus sb);

}
