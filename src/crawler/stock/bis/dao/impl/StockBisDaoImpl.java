package crawler.stock.bis.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.stock.bis.dao.StockBisDao;
import crawler.stock.vo.Balance;
import crawler.stock.vo.CashFlow;
import crawler.stock.vo.Income;

@Repository
public class StockBisDaoImpl extends HibernateDaoSupport implements StockBisDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public static void main(String[] args) {

	}

	public Serializable saveIncome(Income income) {

		return getHibernateTemplate().save(income);

	}

	public Serializable saveBalance(Balance balance) {

		return getHibernateTemplate().save(balance);
	}

	public Serializable saveCashFlow(CashFlow cashFlow) {

		return getHibernateTemplate().save(cashFlow);
	}

}
