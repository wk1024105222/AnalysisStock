package crawler.cases.stock.bis.dao;

import java.io.Serializable;

import crawler.cases.stock.vo.Balance;
import crawler.cases.stock.vo.CashFlow;
import crawler.cases.stock.vo.Income;

public interface StockBisDao {

	public Serializable saveIncome(Income income);

	public Serializable saveBalance(Balance balance);

	public Serializable saveCashFlow(CashFlow cashFlow);
}
