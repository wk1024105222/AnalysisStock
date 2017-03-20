package wk.stock.bis.dao;

import java.io.Serializable;

import wk.stock.vo.Balance;
import wk.stock.vo.CashFlow;
import wk.stock.vo.Income;

public interface StockBisDao {

	public Serializable saveIncome(Income income);

	public Serializable saveBalance(Balance balance);

	public Serializable saveCashFlow(CashFlow cashFlow);
}
