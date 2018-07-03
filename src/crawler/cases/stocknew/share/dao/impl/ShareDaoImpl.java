package crawler.cases.stocknew.share.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.cases.stocknew.share.dao.ShareDao;
import crawler.cases.stocknew.vo.StockBonus;

@Repository
public class ShareDaoImpl extends HibernateDaoSupport implements ShareDao {
	
	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public Serializable saveShareInfo(StockBonus sb) {

		return this.getHibernateTemplate().save(sb);

	}

}
