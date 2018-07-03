package crawler.stock.macd.dao;

import java.io.Serializable;
import java.util.List;

import crawler.stock.vo.MacdCross;


public interface MacdDao {

	Serializable saveMacdCross(MacdCross ms);

	List<String> getLsitWithputMacd();

}
