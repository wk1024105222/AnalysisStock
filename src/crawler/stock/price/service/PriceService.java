package crawler.stock.price.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import crawler.stock.price.task.DownDataTask;
import crawler.stock.vo.StockData;
import crawler.util.Result;

public interface PriceService {

	/**********************************************************************************/
	public String getYahooUrl(DownDataTask task);

	public ArrayList<String> downFromYahoo(DownDataTask t);
	
	public Result savePriceFromYahoo(String code,ArrayList<String> lines);
	
	/**********************************************************************************/
	public String getSinaTdyUrl(DownDataTask task);
	
	public String downFromSinaTdy(DownDataTask t);
	
	public Result savePriceFromSinaTdy(String code,String line);
	
	/**********************************************************************************/
	public ArrayList<String> getSinaSeasonUrl(DownDataTask task);
	
	public ArrayList<String> downFromSinaSeason(DownDataTask t);

	public Result savePriceFromSinaSeason(String code,Date date, String a);
	/**********************************************************************************/
	public List<StockData> getPriceListByCode(String code);

	public List getEveryStockLatestTxnDate();

	public List getComputeGainTask();



}
