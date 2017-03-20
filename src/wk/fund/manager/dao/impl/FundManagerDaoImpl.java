package wk.fund.manager.dao.impl;

import java.io.Serializable;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import wk.fund.manager.dao.FundManagerDao;
import wk.fund.vo.FundManager;
import wk.fund.vo.ManageHistory;

@Repository
public class FundManagerDaoImpl extends HibernateDaoSupport implements FundManagerDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public Serializable saveFundManager(FundManager fundManager) {

		return getHibernateTemplate().save(fundManager);

	}

	public Serializable saveManageHistory(ManageHistory manageHistory) {

		return getHibernateTemplate().save(manageHistory);

	}
}
