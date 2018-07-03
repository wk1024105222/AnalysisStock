package crawler.cases.stocknew.price.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import crawler.cases.stocknew.price.dao.PriceDao;
import crawler.cases.stocknew.price.service.PriceService;
import crawler.cases.stocknew.price.task.DownDataTask;
import crawler.cases.stocknew.vo.PriceDownLog;
import crawler.cases.stocknew.vo.StockData;
import crawler.util.FileUtil;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;
/**
 * 
 * @ClassName: PriceServiceImpl
 * @Description: 从yahoo  sina  下载日交易数据
 * @author yt
 * @date 2015-5-15 下午01:51:50
 *
 */
@Service
public class PriceServiceImpl implements PriceService {
	
	@Resource
	private PriceDao priceDao;

	public List<StockData> getPriceListByCode(String code) {

		return priceDao.getPriceListByCode(code);
	}
	
	/**
	 * 
	 * @Title: importDBFromFile
	 * @Description: 将下载的交易数据从文件内容导入到数据库
	 * @param path
	 * @return Result
	 * @throws
	 */
	public Result importDBFromFile(String path) {
		File file = new File(path);
		
		String code = file.getName().substring(0,6);
		
		if(!file.exists()) {
			return new Result(ResultFlag.Fail,code+"入库失败----文件不存在");
		}

		return savePriceFromYahoo(code, FileUtil.getListFromFile(file));
	}

	/**
	 * 
	 * <p>Title: getYahooUrl</p>
	 * <p>Description: 根据code 以及 开始时间 填补 today做结束时间  生成url从 yahoo 获取数据</p>
	 * @param task
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#getYahooUrl(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public String getYahooUrl(DownDataTask task) {
		Date beginDate = task.getBegindate();
		Calendar begin = Calendar.getInstance();//获得一个日历的实例 
		begin.setTime(beginDate);
		
		Date endDate = new Date();
		Calendar end = Calendar.getInstance();//获得一个日历的实例 
		end.setTime(endDate);
		 
		String code  = task.getCode();
		
		String url = null;
		if(code.startsWith("6")) {
			url = "http://table.finance.yahoo.com/table.csv?s="+code+".ss&" +
					"d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DATE)+"&f="+end.get(Calendar.YEAR)+"&" +
					"a="+begin.get(Calendar.MONTH)+"&b="+begin.get(Calendar.DATE)+"&c="+begin.get(Calendar.YEAR) ;
		} else {
			url = "http://table.finance.yahoo.com/table.csv?s="+code+".sz&" +
			"d="+end.get(Calendar.MONTH)+"&e="+end.get(Calendar.DATE)+"&f="+end.get(Calendar.YEAR)+"&" +
			"a="+begin.get(Calendar.MONTH)+"&b="+begin.get(Calendar.DATE)+"&c="+begin.get(Calendar.YEAR) ;
		}
		return url;
	}

	/**
	 * 
	 * <p>Title: downFromYahoo</p>
	 * <p>Description: 从yahho 下载交易数据</p>
	 * @param t
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#downFromYahoo(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public ArrayList<String> downFromYahoo(DownDataTask t) {
		ArrayList<String> lines = null;
		
		String url = getYahooUrl(t);
		if(url == null || url.equals("")) {
			LogUtil.downtofile.info(t.toString() + "url获取异常,取消下载");
			return lines;
		}

		for(int i=0; i!=10; i++) {
			lines = NetUtil.getListByUrl(url);
			
			if(lines == null || lines.size() == 0) {
				if(i == 9) {
					LogUtil.downtofile.info(t.toString() + "from:" + url + "10次下载均失败");
					return lines;
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			} else if(lines.size() == 1) {
				LogUtil.downtofile.info(t.toString() + "from:" + url + "第"+i+"次下载成功,但交易数据为空");
				break;
			} else {
				
				LogUtil.downtofile.info(t.toString() + "from:" + url + "第"+i+"下载成功");
				break;
			}
		}
		
		return lines;
	}
	
	/**
	 * 
	 * <p>Title: savePriceFromYahoo</p>
	 * <p>Description: ArrayList<String> 类型的交易数据 导入数据库</p>
	 * @param code
	 * @param lines
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#savePriceFromYahoo(java.lang.String, java.util.ArrayList)
	 */
	public Result savePriceFromYahoo(String code,ArrayList<String> lines) {
		String line = null;
		String[] a = null;
		StockData sd = null ; 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for(int i=lines.size()-1; i>0; i--) {
			try {
				line = lines.get(i);
				a = line.split(",");
				
				sd = new StockData(code,
						sdf.parse(a[0]),
						Double.parseDouble(a[1]),
						Double.parseDouble(a[2]),
						Double.parseDouble(a[3]),
						Double.parseDouble(a[4]),
						Long.parseLong(a[5]),
						Double.parseDouble(a[6])
						);
				priceDao.saveStockData(sd);
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
				return new Result(ResultFlag.Fail,code+line+"入库失败\r\n"+e.toString());
			} catch (ParseException e) {
				
				e.printStackTrace();
				return new Result(ResultFlag.Fail,code+line+"入库失败\r\n"+e.toString());
			}
		}
		return new Result(ResultFlag.Success,code+"入库成功"+lines.size()+"条记录");
	}
	
	/**
	 * 
	 * <p>Title: getSinaTdyUrl</p>
	 * <p>Description: 生成URL 从sina 获取当日收盘价</p>
	 * @param task
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#getSinaTdyUrl(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public String getSinaTdyUrl(DownDataTask task) {
		String code  = task.getCode();
		
		if(code.startsWith("6")) {
			return "http://hq.sinajs.cn/list=sh"+code;
		} else {
			return "http://hq.sinajs.cn/list=sz"+code;
		}
	}
	
	/**
	 * 
	 * <p>Title: downFromSinaTdy</p>
	 * <p>Description: 从sina 获取当日收盘价 本接口可用于实时查看价格 收盘价需要 15:00后查看</p>
	 * @param t
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#downFromSinaTdy(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public String downFromSinaTdy(DownDataTask t) {
		String html = "";
		String url = getSinaTdyUrl(t);
		if(url == null || url.equals("")) {
			LogUtil.downtofile.info(t.toString() + "url获取异常,取消下载");
			return html;
		}
		
		ArrayList<String> lines = null;
		
		for(int i=0; i!=10; i++) {
			lines = NetUtil.getListByUrl(url);
			
			if(lines == null || lines.size() == 0) {
				if(i == 9) {
					LogUtil.downtofile.info(t.toString() + "from:" + url + "10次下载均失败");
					return html;
				}
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				}

			} else if(lines.get(0).length()==23) {
				html = "";
				LogUtil.downtofile.info(t.toString() + "from:" + url + "第"+i+"次下载成功,但数据为空");
				break;
				
			} else {
				html = lines.get(0);
				LogUtil.downtofile.info(t.toString() + "from:" + url + "第"+i+"下载成功"+lines.get(0));
				break;
				
			}
		}
		return html;
	}
	
	/**
	 * 
	 * <p>Title: savePriceFromSinaTdy</p>
	 * <p>Description: 格式化从sina 获取的实时价格 存入数据库</p>
	 * @param code
	 * @param line
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#savePriceFromSinaTdy(java.lang.String, java.lang.String)
	 */
	public Result savePriceFromSinaTdy(String code,String line) {
		/*
		 *  var hq_str_sh601006="大秦铁路,13.82,13.82,13.97,14.56,13.61,13.95,13.96,227485986,3194455161,181485,13.95,586632,13.94,190300,13.93,101200,13.92,208700,13.91,2700,13.96,118145,13.97,360200,13.98,242671,13.99,354126,14.00,2015-04-30,15:03:02,00";
		 *  0：”大秦铁路”，股票名字；
			1：”27.55″，今日开盘价；
			2：”27.25″，昨日收盘价；
			3：”26.91″，当前价格；
			4：”27.55″，今日最高价；
			5：”26.20″，今日最低价；
			6：”26.91″，竞买价，即“买一”报价；
			7：”26.92″，竞卖价，即“卖一”报价；
			8：”22114263″，成交的股票数，由于股票交易以一百股为基本单位，所以在使用时，通常把该值除以一百；
			9：”589824680″，成交金额，单位为“元”，为了一目了然，通常以“万元”为成交金额的单位，所以通常把该值除以一万；
			10：”4695″，“买一”申请4695股，即47手；
			11：”26.91″，“买一”报价；
			12：”57590″，“买二”
			13：”26.90″，“买二”
			14：”14700″，“买三”
			15：”26.89″，“买三”
			16：”14300″，“买四”
			17：”26.88″，“买四”
			18：”15100″，“买五”
			19：”26.87″，“买五”
			20：”3100″，“卖一”申报3100股，即31手；
			21：”26.92″，“卖一”报价
			(22, 23), (24, 25), (26,27), (28, 29)分别为“卖二”至“卖四的情况”
			30：”2008-01-11″，日期；
			31：”15:05:32″，时间；
		 */
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		
		String[] a = line.split(",");
		
		try {
			StockData sd = new StockData(code,
					sdf.parse(a[30]),
					
					Double.parseDouble(a[1]),
					Double.parseDouble(a[4]),
					Double.parseDouble(a[5]),
					Double.parseDouble(a[3]),
					(Long.parseLong(a[8])/100)*100,
					null
					);
			priceDao.saveStockData(sd);
		} catch (NumberFormatException e) {
			
			//e.printStackTrace();
			System.out.println(line);
			return new Result(ResultFlag.Fail,code+"入库失败\r\n"+e.toString()+line);
		} catch (ParseException e) {
			
			//e.printStackTrace();
			System.out.println(line);
			return new Result(ResultFlag.Fail,code+"入库失败\r\n"+e.toString()+line);
		} catch (ArrayIndexOutOfBoundsException e) {
			
			//e.printStackTrace();
			System.out.println(line);
			return new Result(ResultFlag.Fail,code+"入库失败\r\n"+e.toString()+line);
		}
		
		return new Result(ResultFlag.Success,code+"from Sina入库成功");
	}
	
	/**
	 * 
	 * <p>Title: getSinaSeasonUrl</p>
	 * <p>Description: 根据交易码、begindate生成 URL数组 一个季度一个</p>
	 * @param task
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#getSinaSeasonUrl(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public ArrayList<String> getSinaSeasonUrl(DownDataTask task) {
		ArrayList<String> rlt = new ArrayList<String>();
		
		Calendar begin = Calendar.getInstance();//获得一个日历的实例
		begin.setTime(task.getBegindate());
		
		Calendar end = Calendar.getInstance();//获得一个日历的实例 
		end.setTime(new Date());
		
		int begin_year = begin.get(Calendar.YEAR);
		int begin_month = begin.get(Calendar.MONTH)+1;
		int begin_season = 0;
		if(begin_month>=1 && begin_month<=3) {
			begin_season = 1;
		} else if(begin_month>=4 && begin_month<=6) {
			begin_season = 2;
		} else if(begin_month>=7 && begin_month<=9) {
			begin_season = 3;
		} else if(begin_month>=10 && begin_month<=12) {
			begin_season = 4;
		}
		
		int end_year = end.get(Calendar.YEAR);
		int end_month = end.get(Calendar.MONTH)+1;
		int end_season = 0;
		if(end_month>=1 && end_month<=3) {
			end_season = 1;
		} else if(end_month>=4 && end_month<=6) {
			end_season = 2;
		} else if(end_month>=7 && end_month<=9) {
			end_season = 3;
		} else if(end_month>=10 && end_month<=12) {
			end_season = 4;
		}
		
		while((begin_year*10+begin_season) <= (end_year*10+end_season)) {
			rlt.add("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/"+task.getCode()+".phtml?year="+begin_year+"&jidu="+begin_season);
			begin_season++;
			if(begin_season == 5) {
				begin_year++;
				begin_season=1;
			}
		}
		
		return rlt;
	}
	
	/**
	 * 
	 * <p>Title: downFromSinaSeason</p>
	 * <p>Description: 从sina 下载一只股票 各季度数据的html</p>
	 * @param t
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#downFromSinaSeason(crawler.cases.stocknew.price.task.DownDataTask)
	 */
	public ArrayList<String> downFromSinaSeason(DownDataTask t) {
		ArrayList<String> allHtmls = new ArrayList<String>();
		if(t.getBegindate() == null) {
			LogUtil.downtofile.info(t.toString() + "begindate为空无法下载");
			return allHtmls;
		}
		//获取待下载数据 所有url
		ArrayList<String> allUrls = getSinaSeasonUrl(t);
		String html = "";

		String url = "";
		//i=0  时间递增
		for(int i=0; i!=allUrls.size(); i++) {
			url = allUrls.get(i);
			String year = url.substring(89, 93);
			String season = url.substring(99);
//			if(priceDao.getSuccessLogBySeason(t.getCode(),year, season).size() > 0) {
//				LogUtil.downtofile.info(url + "数据已入库,取消再次下载");
//				continue;
//			}
			
			for(int j=0; j!=10; j++) {
				html = NetUtil.getHtmlSourceByUrl(url);

				if(html == null || html.length() == 0) {
					if(j == 9) {
						LogUtil.downtofile.info(t.toString() + "from:" + url + "10次下载均失败");
						break;
					}
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				} else {
//					PriceDownLog pdl = new PriceDownLog(t.getCode(), null, null, year, season, null,"0");
					allHtmls.add(html);
					LogUtil.downtofile.info(t.toString() + "from:" + url + "第"+j+"下载成功");
					break;
				}
			}
		}
		return allHtmls;
	}

	/**
	 * 
	 * <p>Title: savePriceFromSinaSeason</p>
	 * <p>Description: 从html中获取季交易明细存入数据库</p>
	 * @param code
	 * @param line
	 * @param pdl
	 * @return
	 * @see crawler.cases.stocknew.price.service.PriceService#savePriceFromSinaSeason(java.lang.String, java.lang.String, crawler.cases.stocknew.vo.PriceDownLog)
	 */
	public Result savePriceFromSinaSeason(String code,Date beginDate, String html) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 *  
		 *  <td><div align="center"><strong>日期</strong></div></td>
		 * 	<td><div align="center"><strong>开盘价</strong></div></td>
	        <td><div align="center"><strong>最高价</strong></div></td>
	        <td><div align="center"><strong>收盘价</strong></div></td>
	        <td class="tdr"><div align="center"><strong>最低价</strong></div></td>
			<td class="tdr"><div align="center"><strong>交易量(股)</strong></div></td>
			<td class="tdr"><div align="center"><strong>交易金额(元)</strong></div></td>
		 */
		
		String reg = "<td><div align=\"center\">\\s+" +
				 		"<a target='_blank' href='http://vip.stock.finance.sina.com.cn/quotes_service/view/vMS_tradehistory.php\\?" +
				 		"symbol=s[zh]\\d{6}&date=\\d{4}-\\d{2}-\\d{2}'>\\s*([^\\s]+)\\s+</a>\\s*</div></td>\\s*" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" ;
		String reg1 = "<td><div align=\"center\">\\s+" +
				 		"\\d{4}-\\d{2}-\\d{2}\\s*</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" +
				 		"<td[^\\d]*([^<]*)</div></td>\\s+" ;
		
		ArrayList<StockData> sds = getPriceListFromSinaSeasonHtml(code,beginDate, html, reg);

		if(sds.size() == 0) {
			sds = getPriceListFromSinaSeasonHtml(code,beginDate, html, reg1);
		}
		LogUtil.downtofile.info(code+":"+sds.size()+"条记录");
		
		try {
			for(int i=sds.size()-1; i>=0; i--) {
				priceDao.saveStockData(sds.get(i));
				//HibernateUtil.save(sds.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, code+"_"+sdf.format(beginDate)+"入库失败");
		}

		return new Result(ResultFlag.Success,code+"_"+sdf.format(beginDate)+"入库成功");
	}

	/**
	 * 
	 * @Title: getPriceListFromSinaSeasonHtml
	 * @Description: 从html中格式化出季交易数据
	 * @param code
	 * @param line
	 * @param reg
	 * @return ArrayList<StockData>
	 * @throws
	 */
	private ArrayList<StockData> getPriceListFromSinaSeasonHtml(String code, Date beginDate, String html, String reg) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(html);
		String tmp = "";
		StockData sd = null;
		Elements tds = null;
		Document doc = null;
		
		ArrayList<StockData> sds = new ArrayList<StockData>();

		while (matcher.find()) {
			try {
				tmp = matcher.group();
				doc = Jsoup.parse(tmp);
				tds = doc.getElementsByTag("div");
				if(sdf.parse(tds.get(0).text()).after(beginDate)) {
					sd = new StockData(code,
							sdf.parse(tds.get(0).text()),
							Double.parseDouble(tds.get(1).text()),
							Double.parseDouble(tds.get(2).text()),
							Double.parseDouble(tds.get(4).text()),
							Double.parseDouble(tds.get(3).text()),
							Long.parseLong(tds.get(5).text()),
							null
							);
					sds.add(sd);
				}
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LogUtil.downtofile.info(code+tds.get(0).text()+"入库失败"+"\r\n"+e.toString());
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				LogUtil.downtofile.info(code+tds.get(0).text()+"入库失败"+"\r\n"+e.toString());
				continue;
			}
		}
		return sds;
	}
	
	public List getEveryStockLatestTxnDate() {

		return priceDao.getEveryStockLatestTxnDate();
	}

	public PriceDao getPriceDao() {
		return priceDao;
	}

	public void setPriceDao(PriceDao priceDao) {
		this.priceDao = priceDao;
	}

	public List getComputeGainTask() {

		// TODO Auto-generated method stub
		return this.priceDao.getComputeGainTask();
	}


	
}
