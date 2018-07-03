package crawler.stock.bis.dao;

import java.io.Serializable;

import crawler.stock.vo.Balance;
import crawler.stock.vo.CashFlow;
import crawler.stock.vo.Income;

public interface StockBisDao {

	public Serializable saveIncome(Income income);

	public Serializable saveBalance(Balance balance);

	public Serializable saveCashFlow(CashFlow cashFlow);
}
