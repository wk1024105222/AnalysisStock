package wk.fund.info.dao.impl;

import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import wk.fund.info.dao.FundInfoDao;
import wk.fund.vo.FundInfo;

@Repository
public class FundInfoDaoImpl extends HibernateDaoSupport implements FundInfoDao {
	
	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public List<FundInfo> getAllFundInfo() {

		// TODO Auto-generated method stub
		return this.getHibernateTemplate().find("from FundInfo");
	}

}
