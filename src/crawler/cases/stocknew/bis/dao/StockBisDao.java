package crawler.cases.stocknew.bis.dao;

import java.io.Serializable;

import crawler.cases.stocknew.vo.Balance;
import crawler.cases.stocknew.vo.CashFlow;
import crawler.cases.stocknew.vo.Income;

public interface StockBisDao {

	public Serializable saveIncome(Income income);

	public Serializable saveBalance(Balance balance);

	public Serializable saveCashFlow(CashFlow cashFlow);
}
