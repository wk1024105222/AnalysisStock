package crawler.cases.stock.bis.service;

import crawler.cases.stock.vo.StockInfo;
import crawler.util.Result;

/***
 * @author yt
 */
public interface StockBisService {

	/**
	 * �����Income Statement��
	 * 
	 * @param code
	 * @return
	 */
	public String getISUrl(String code);

	/**
	 * �ʲ���ծ��Balance Sheet��
	 * 
	 * @param code
	 * @return
	 */
	public String getBSUrl(String code);

	/**
	 * �ֽ�������Cash Flow Statements��
	 * 
	 * @param code
	 * @return
	 */
	public String getCSUrl(String code);

	public String getISFileName(String code);

	public String getBSFileName(String code);

	public String getCSFileName(String code);

	/**
	 * �������.xls���ݵ������ݿ�
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveIncome(String fileName);

	/**
	 * ���ʲ���ծ��.xls���ݵ������ݿ�
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveBalance(String fileName);

	/**
	 * ���ֽ�������.xls���ݵ������ݿ�
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveCashFlow(String fileName);
	
	public String downIncome(StockInfo si);
	
	public String downBalance(StockInfo si);
	
	public String downCashFlow(StockInfo si);

}
