package crawler.cases.stocknew.info.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import crawler.cases.stocknew.info.dao.StockInfoDao;
import crawler.cases.stocknew.info.service.StockInfoService;
import crawler.cases.stocknew.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

@Service
public class StockInfoServiceImpl implements StockInfoService {

	@Resource
	private StockInfoDao stockInfoDao;

	public List<StockInfo> getAllStockInfo() {

		return stockInfoDao.getAllStockInfo();
	}

	public Result updateStockInfoFromSina(StockInfo si, String line) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

		Document doc = Jsoup.parse(line);

		Element h1s = doc.getElementById("stockName");
		if(h1s != null) {
			String stockName = h1s.text();
			// String type = stockName.substring(stockName.indexOf('.'));// 暂时不用
			si.setName(stockName.substring(0, stockName.indexOf('(')));
		} else {
			LogUtil.info.info(si.getCode() + "HTML获取股票名称失败,继续获取上市时间");
		}

		Elements links = doc.getElementsByTag("a");

		try {
			for(int a = links.size() - 1; a >= 0; a--) {
				Element link = links.get(a);
				if(link.attr("href").indexOf("InMarketDate") != -1) {
					si.setListedDate(sdf.parse(link.text()));
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
			LogUtil.info.info(si.getCode() + "HTML中格式化获取上市时间失败");
		}

		try {
			stockInfoDao.updateStockInfo(si);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, si.toString() + "上市日期 股票名称 更新失败\r\n" + e.toString());
		}
		return new Result(ResultFlag.Success, si.toString() + "上市日期 股票名称 更新成功");
	}

	public String getSinaStockInfoUrl(String code) {

		return "http://vip.stock.finance.sina.com.cn/corp/go.php/vCI_CorpInfo/stockid/" + code + ".phtml";
	}


}
