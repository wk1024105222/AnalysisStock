package crawler.fund.manager.sevice.impl;

import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.xml.sax.InputSource;

import crawler.fund.manager.dao.FundManagerDao;
import crawler.fund.manager.sevice.FundManagerService;
import crawler.fund.vo.FundManager;
import crawler.fund.vo.ManageHistory;
import crawler.util.LogUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

@Service
public class FundManagerServiceImpl implements FundManagerService {
	
	@Resource
	private FundManagerDao fundManagerDao;
	
	public String getTiantianManagerUrl(String code) {

		// 从天天基金拿基金经理数据
		return "http://fund.eastmoney.com/f10/jjjl_" + code + ".html";
	}

	public Result saveManageHistoryFromTianTian(String code, String html) {

		String reg = "<tr>" +
		"<td>\\d{4}-\\d{2}-\\d{2}</td>" +
		"<td>(\\d{4}-\\d{2}-\\d{2}|至今)</td>" +
		"<td>(<a href='http://fund.eastmoney.com/manager/\\d{8}.html'>.{1,5}</a>\\s*)+</td>" +
		"<td>(\\d+年又)?\\d{1,3}天</td>" +
		"<td class=\"tor  (red|grn)\">-{0,1}\\d+\\.{0,1}\\d*%</td></tr>";
		
		ArrayList<ManageHistory> fms = getManageHistoryListFromTiantianHtml(code, html, reg );


		LogUtil.downFunPrice.info(code+":"+fms.size()+"条记录");
		
		try {
			for(int i=fms.size()-1; i>=0; i--) {
				//System.out.println(fms.get(i).getCode()+"\t"+fms.get(i).getNames()+"\t"+fms.get(i).getNames().length());
				fundManagerDao.saveManageHistory(fms.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, code.toString()+"入库失败");
		}
		
		return new Result(ResultFlag.Success,code+"入库成功");
	}
	
	private ArrayList<ManageHistory> getManageHistoryListFromTiantianHtml(String code, String html, String reg) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(html);
		String tmp = "";

		ArrayList<ManageHistory> fms = new ArrayList<ManageHistory>();

		SAXReader saxReader = new SAXReader();
		Document document = null;
		Element tr = null;

		ManageHistory manageHistory = null;

		while(matcher.find()) {

			try {
				tmp = matcher.group();
				document = saxReader.read(new InputSource(new StringReader(tmp)));
				tr = document.getRootElement();
				List<Element> tds = tr.elements();

				String beginStr = tds.get(0).getTextTrim();
				Date begin = sdf.parse(beginStr);

				String endStr = tds.get(1).getTextTrim();
				Date end = null;
				if(endStr.endsWith("至今")) {
					end = new Date();
				} else {
					end = sdf.parse(endStr);
				}

				StringBuffer names = new StringBuffer();
				List<Element> namesNode = tds.get(2).elements();
				for(Element i : namesNode) {
					names.append(i.getTextTrim()+";");
				}
				names.deleteCharAt(names.length()-1);
				
				long days = (end.getTime() - begin.getTime()) / (1000 * 60 * 60 * 24);
				
				String yieldsStr = tds.get(4).getTextTrim();
				double yields = Double.parseDouble(yieldsStr.substring(0, yieldsStr.length() - 1));

				manageHistory = new ManageHistory(code, begin, end, names.toString(), days, yields);

				fms.add(manageHistory);
			} catch (NumberFormatException e) {
				
				e.printStackTrace();
			} catch (DocumentException e) {
				
				e.printStackTrace();
			} catch (ParseException e) {
				
				e.printStackTrace();
			}

		}
		return fms;
	}

	public Result saveFundManagerFromTiantian(String code, String html) {

		String reg = "<div class=\"text\">" +
		"<p><strong>姓名：</strong><a style='color:#333; text-decoration:none;' href=\"http://fund.eastmoney.com/manager/\\d{8}.html\">.{1,10}?</a></p>" +
		"<p><strong>上任日期：</strong>\\d{4}-\\d{2}-\\d{2}</p>" +
		"<p>.*?</p>" +
		"<p class=\"tor\">.*?</p>" +
		"</div>";
		
		ArrayList<FundManager> fms = getFundManagerListFromTiantianHtml(code, html, reg );


		LogUtil.downFunPrice.info(code+":"+fms.size()+"条记录");
		
		try {
			for(int i=fms.size()-1; i>=0; i--) {
				fundManagerDao.saveFundManager(fms.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, code.toString()+"入库失败");
		}
		
		return new Result(ResultFlag.Success,code+"入库成功");
	}

	private ArrayList<FundManager> getFundManagerListFromTiantianHtml(String code, String html, String reg) {

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(html);
		String tmp = "";

		ArrayList<FundManager> fms = new ArrayList<FundManager>();

		SAXReader saxReader = new SAXReader();
		Document document = null;
		Element div = null;

		FundManager fm = null;

		while(matcher.find()) {

			try {
				tmp = matcher.group();
				document = saxReader.read(new InputSource(new StringReader(tmp)));
				div = document.getRootElement();
				List<Element> ps = div.elements();

				String name = ((List<Element>) ps.get(0).elements()).get(1).getTextTrim();

				String profile = ps.get(2).getTextTrim();

				fm = new FundManager(name, profile);

				fms.add(fm);
			} catch (DocumentException e) {
				
				e.printStackTrace();
			}
		}
		return fms;
	}

}
