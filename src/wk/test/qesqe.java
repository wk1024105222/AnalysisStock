package wk.test;

import java.io.IOException;
import java.io.StringBufferInputStream;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;





import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;


import wk.fund.vo.FundData;
import wk.fund.vo.FundInfo;
import wk.stock.vo.StockData;
import wk.util.FileUtil;

public class qesqe {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws DOMException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) {
		
		//String str = NetUtil.getHtmlSourceByUrl("http://money.finance.sina.com.cn/corp/go.php/vMS_MarketHistory/stockid/601006.phtml");
		String str = FileUtil.getFromFile("manager398001.html");
		//System.out.println(str);
		String reg = 
			//"<tbody>\\s*"+
		"<td class=\"bl_none\" rowspan=\"\\d+\">.{1,5}</td>\\s*" +
		"(<tr (class=\"even\")?>\\s*"+
		"<td class=\"links_td\"><a title=\".{3,13}\" href=\"http://fund.10jqka.com.cn/\\d{6}\">.{3,13}</a></td>\\s*"+
		"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
		"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
		"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
		"<td>\\d{4}-\\d{2}-\\d{2}~(\\d{4}-\\d{2}-\\d{2}|жа╫Я)</td>\\s*"+
		"</tr>\\s*)+"
		//+"</tbody>"
		;

		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(str);
		String tmp = "";
		int num = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		StockData sd = null;
		Elements tds = null;
		Document doc = null;

		while(matcher.find()) {

		
				tmp = matcher.group();
				doc = Jsoup.parse(tmp);
				tds = doc.getElementsByTag("body");
				System.out.println(tds.get(0));
				
				

		}
		System.out.println("num=" + num);
		System.out.println("===============================================");
	}

}
