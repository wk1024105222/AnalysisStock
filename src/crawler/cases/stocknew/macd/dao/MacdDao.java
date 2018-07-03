package crawler.cases.stocknew.macd.dao;

import java.io.Serializable;
import java.util.List;

import crawler.cases.stocknew.vo.MacdCross;


public interface MacdDao {

	Serializable saveMacdCross(MacdCross ms);

	List<String> getLsitWithputMacd();

}
