package crawler.cases.stock.bis.service;

import crawler.cases.stock.vo.StockInfo;
import crawler.util.Result;

/***
 * @author yt
 */
public interface StockBisService {

	/**
	 * 利润表（Income Statement）
	 * 
	 * @param code
	 * @return
	 */
	public String getISUrl(String code);

	/**
	 * 资产负债表（Balance Sheet）
	 * 
	 * @param code
	 * @return
	 */
	public String getBSUrl(String code);

	/**
	 * 现金流量表（Cash Flow Statements）
	 * 
	 * @param code
	 * @return
	 */
	public String getCSUrl(String code);

	public String getISFileName(String code);

	public String getBSFileName(String code);

	public String getCSFileName(String code);

	/**
	 * 将利润表.xls数据导入数据库
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveIncome(String fileName);

	/**
	 * 将资产负债表.xls数据导入数据库
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveBalance(String fileName);

	/**
	 * 将现金流量表.xls数据导入数据库
	 * 
	 * @param fileName
	 * @return
	 */
	public Result saveCashFlow(String fileName);
	
	public String downIncome(StockInfo si);
	
	public String downBalance(StockInfo si);
	
	public String downCashFlow(StockInfo si);

}
