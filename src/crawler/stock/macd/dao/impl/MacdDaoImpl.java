package crawler.stock.macd.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.stock.macd.dao.MacdDao;
import crawler.stock.vo.MacdCross;

@Repository
public class MacdDaoImpl extends HibernateDaoSupport implements MacdDao {
	
	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public Serializable saveMacdCross(MacdCross ms) {

		return this.getHibernateTemplate().save(ms);

	}

	public List<String> getLsitWithputMacd() {

		String hql = "select distinct t.code from StockData t where t.ema12 is null ";
		return this.getHibernateTemplate().find(hql);
	}

}
