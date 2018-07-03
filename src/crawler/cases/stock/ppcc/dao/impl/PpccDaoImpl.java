package crawler.cases.stock.ppcc.dao.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.cases.stock.ppcc.dao.PpccDao;
import crawler.cases.stock.vo.Ppcc;
import crawler.cases.stock.vo.StockData;

@Repository
public class PpccDaoImpl extends HibernateDaoSupport implements PpccDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public int getComputePpccNum(Date beginDate, Date endDate) {

		String hql = "select count(*) from Ppcc t where t.beginDate=? and t.endDate=?";

		Long rlt = (Long)(this.getHibernateTemplate().find(hql, new Object[]{beginDate, endDate}).listIterator().next());

		return rlt.intValue();
	}

	public List<Ppcc> getNotFinishPpcc(Date beginDate, Date endDate) {

		String hql = "from Ppcc t where t.beginDate=? and t.endDate=? and t.close is null";
		
		return this.getHibernateTemplate().find(hql, new Object[]{beginDate, endDate});
	}

	public Serializable savePpcc(Ppcc t) {

		return this.getHibernateTemplate().save(t);
		
	}

	public void updatePpcc(Ppcc t) {

		this.getHibernateTemplate().update(t);
		
	}

	public List<StockData> getRangeDataByCode(String code, Date beginDate, Date endDate) {

		String hql = "select t from StockData t " +
		"where t.code=? " +
		"and t.txnDate>=? " +
		"and t.txnDate<=? " +
		"order by t.txnDate ";
		return this.getHibernateTemplate().find(hql, new Object[]{code, beginDate, endDate});
	}

}
