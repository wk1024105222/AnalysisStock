package crawler.cases.stock.info.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.cases.stock.info.dao.StockInfoDao;
import crawler.cases.stock.vo.StockInfo;

@Repository
public class StockInfoDaoImpl extends HibernateDaoSupport implements StockInfoDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public void updateStockInfo(StockInfo si) {

		this.getHibernateTemplate().update(si);

	}

	public List<StockInfo> getAllStockInfo() {

		return this.getHibernateTemplate().find("from StockInfo t order by t.code");
	}

}
