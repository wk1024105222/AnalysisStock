package wk.fund.price.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import wk.fund.price.dao.FundPriceDao;
import wk.fund.vo.FundData;

@Repository
public class FundPriceDaoImpl extends HibernateDaoSupport implements FundPriceDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public Serializable saveFundData(FundData fd) {

		// TODO Auto-generated method stub
		return getHibernateTemplate().save(fd);
	}

	public List getMaxDateByCode(String code) {

		// TODO Auto-generated method stub  select t.txnDate from FundData t where t.code=''
	
		return getHibernateTemplate().find("select max(t.txnDate) from FundData t where t.code='"+code+"'");
	}
}
