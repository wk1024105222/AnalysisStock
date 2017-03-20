package wk.stock.macd.dao;

import java.io.Serializable;
import java.util.List;

import wk.stock.vo.MacdCross;


public interface MacdDao {

	Serializable saveMacdCross(MacdCross ms);

	List<String> getLsitWithputMacd();

}
