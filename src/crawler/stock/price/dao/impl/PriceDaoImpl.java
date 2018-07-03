package crawler.stock.price.dao.impl;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import crawler.stock.price.dao.PriceDao;
import crawler.stock.vo.PriceDownLog;
import crawler.stock.vo.StockData;

@SuppressWarnings("unchecked")
@Repository
public class PriceDaoImpl extends HibernateDaoSupport implements PriceDao {

	@Resource(name = "sessionFactory")
	public void setBaseDaoSessionFactory(SessionFactory sessionFactory) {

		super.setSessionFactory(sessionFactory);
	}

	public Serializable saveStockData(StockData sd) {

		// TODO Auto-generated method stub
		return getHibernateTemplate().save(sd);
	}

	public void saveOrUpdateStockData(StockData sd) {

		getHibernateTemplate().saveOrUpdate(sd);

	}

	public Serializable savePriceDownLog(PriceDownLog priceDownLog) {

		return getHibernateTemplate().save(priceDownLog);

	}

	public List getSuccessLogBySeason(String code, String year, String season) {

		String hql = "from PriceDownLog t where t.code=? and t.year=? and t.season=? and t.flag=?";
		Object[] params = new Object[]{code, year, season, "1"};

		return getHibernateTemplate().find(hql, params);
	}

	public List<StockData> getPriceListByCode(String code) {

		String hql = "select t from StockData t where t.code=? order by t.txnDate";
		
		return getHibernateTemplate().find(hql, new Object[]{code});
	}

	public StockData getPreTxnData(StockData td) {

		List<StockData> allPre = getAllPreTxnData(td);
		if(allPre.size() > 0 ) {
			return allPre.get(allPre.size()-1);
		} else {
			return null;
		}
	}

	public List<StockData> getAllPreTxnData(StockData td) {

		String hql = "from StockData t " +
						"where t.volume>? " +
						"and t.code=? " +
						"and t.txnDate<? " +
						"order by t.txnDate";
		
		return getHibernateTemplate().find(hql, new Object[]{new Long(0), td.getCode(), td.getTxnDate()});
	}

	public boolean isListedSecondDay(StockData td) {

		List<StockData> rlt = getAllPreTxnData(td);
		return rlt.size()==1;
	}

	public void updateStockData(StockData sd) {

		getHibernateTemplate().update(sd);
		
	}

	public List getEveryStockLatestTxnDate() {

		String hql = "select t.code,max(t.txnDate) from StockData t " +
				"group by t.code " +
				"having max(t.txnDate)<>to_date(to_char(sysdate,'yyyymmdd'),'yyyymmdd') " +
				"and max(t.txnDate)>to_date('20150101','yyyymmdd') " +
				"order by max(txndate) desc";
//		String hql = "select t.code, to_date('1990-12-19', 'yyyy-mm-dd') from StockInfo t where t.listedDate is null";
//		String hql ="select i.code, i.listedDate-1 from StockInfo i where not exists (select 1 from StockData d where d.code=i.code) and i.listedDate is not null";
		return this.getHibernateTemplate().find(hql);
	}

	public List getComputeGainTask() {

		String hql = "select distinct t.code from StockData t where t.gain is null ";
		return this.getHibernateTemplate().find(hql);
	}



}
