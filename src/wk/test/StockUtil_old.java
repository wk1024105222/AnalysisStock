package wk.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import wk.stock.vo.StockData;
import wk.stock.vo.StockInfo;
import wk.util.HibernateUtil_old;

public class StockUtil_old {
	public static Map<String,List<StockData>> allStockDatas = new HashMap<String,List<StockData>>();
	//public static List<StockInfo> allStockInfo = null;
	
	static {
		allStockDatas.clear();
		//allStockInfo = StockUtil.getAllStockInfo();
	}
	
	/**
	 * 获取某股票所有有效日交易数据 交易量>0
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<StockData> getEffDataByCode(String code) {
		String hql = "select t from StockData t where t.code=? and t.volume>? order by t.txnDate";
		
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(0);
	
		return (List<StockData>)HibernateUtil_old.query(hql, params);
	}
	
	/**
	 * 获取某股票所有日交易数据 
	 * @param code
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<StockData> getAllDataByCode(String code) {
		String hql = "select t from StockData t where t.code=? order by t.txnDate";
		
		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(new Integer(0));
	
		return (List<StockData>)HibernateUtil_old.query(hql, params);
	}
	
	@SuppressWarnings("unchecked")
	public static List<StockData> getRangeDataByCode(String code, Date begin, Date end) {
		if(allStockDatas.containsKey(code) ) {
			return allStockDatas.get(code);
		}
		String hql = "select t from StockData t " +
				"where t.code=? " +
				"and t.txnDate>=? " +
				"and t.txnDate<=? " +
				"order by t.txnDate ";

		List<Object> params = new ArrayList<Object>();
		params.add(code);
		params.add(begin);
		params.add(end);
	
		List<StockData> rlt = (List<StockData>)HibernateUtil_old.query(hql, params);
		allStockDatas.put(code, rlt);
		return rlt;
	}
	
	/**
	 * 判断该交易日是否是上市第二天
	 * @param s
	 * @return
	 */
	public static boolean isListedSecondDay(StockData s) {
		List<StockData> rlt = StockUtil_old.getAllPreEffTxnData(s);
		return rlt.size()==1;
	}

	/***
	 * 获取制定交易的上一笔交易(不包含停盘 交易量>0)
	 * @param s
	 * @return
	 */
	public static StockData getPreTxnData(StockData s) {
		List<StockData> rlt = StockUtil_old.getAllPreEffTxnData(s);
		if(rlt.size() > 0 ) {
			return (StockData) rlt.get(rlt.size()-1);
		} else {
			return null;
		}
	}
	
	/***
	 * 获取制定交易前的所有交易(不包含停盘 交易量>0)
	 * @param s
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<StockData> getAllPreEffTxnData(StockData s) {
		String hql = "select t from StockData t " +
					"where t.volume>? " +
					"and t.code=? " +
					"and t.txnDate<? " +
					"order by t.txnDate";
		
		List<Object> params = new ArrayList<Object>();
		params.add(new Integer(0));
		params.add(s.getCode());
		params.add(s.getTxnDate());
	
		List<StockData> allData = (List<StockData>)HibernateUtil_old.query(hql, params);
		return allData;
	}
	
	/***
	 * 获取所有股票信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<StockInfo> getAllStockInfo() {
		//return (List<StockInfo>)HibernateUtil_old.query("from StockInfo t order by t.code");
		
			return (List<StockInfo>)HibernateUtil_old.query("from StockInfo t where t.code='002483' or t.code='002684'");
	}
	
	public static void main(String args[]) {
		LinkedList<String> a = new LinkedList<String>();
		// System.out.print(allTask.getFirst());
	}
}
