package wk.test;

import java.io.IOException;
import java.io.StringReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//
//import org.w3c.dom.DOMException;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
//import org.xml.sax.SAXException;

import wk.fund.vo.ManageHistory;
import wk.util.FileUtil;

public class sssss {

	/**
	 * @param args
	 * @throws ParseException 
	 * @throws DOMException 
	 * @throws NumberFormatException 
	 * @throws IOException 
	 * @throws SAXException 
	 */
	public static void main(String[] args) {
//		StringBuffer  rlt = new StringBuffer("<body><table>");
//		for(File a : new File("d:\\fund\\1").listFiles()) {
			String str = FileUtil.getFromFile("testfile\\man398001.html");
		
			String reg1 = 
				//"<tbody>\\s*"+
			"<td class=\"bl_none\" rowspan=\"\\d+\">.{1,5}</td>\\s*" +
			"(<tr (class=\"even\")?>\\s*"+
			"<td class=\"links_td\"><a title=\".{3,13}\" href=\"http://fund.10jqka.com.cn/\\d{6}\">.{3,13}</a></td>\\s*"+
			"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
			"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
			"<td class=\"(plus tr|minus tr)\">-{0,1}\\d+\\.{0,1}\\d*%</td>\\s*"+
			"<td>\\d{4}-\\d{2}-\\d{2}~(\\d{4}-\\d{2}-\\d{2}|至今)</td>\\s*"+
			"</tr>\\s*)+"
			//+"</tbody>"
			;
			
			//基金经理管理历史
			String reg = "<tr>" +
					"<td>\\d{4}-\\d{2}-\\d{2}</td>" +
					"<td>(\\d{4}-\\d{2}-\\d{2}|至今)</td>" +
					"<td>(<a href='http://fund.eastmoney.com/manager/\\d{8}.html'>.{1,5}</a>\\s*)+</td>" +
					"<td>(\\d+年又)?\\d{1,3}天</td>" +
					"<td class=\"tor  (red|grn)\">-{0,1}\\d+\\.{0,1}\\d*%</td></tr>";
			
			String reg2 = "<div class=\"text\">" +
			"<p><strong>姓名：</strong><a style='color:#333; text-decoration:none;' href=\"http://fund.eastmoney.com/manager/\\d{8}.html\">.{1,10}?</a></p>" +
			"<p><strong>上任日期：</strong>\\d{4}-\\d{2}-\\d{2}</p>" +
			"<p>.*?</p>" +
			"<p class=\"tor\">.*?</p>" +
			"</div>";

			Pattern pattern = Pattern.compile(reg2);
			Matcher matcher = pattern.matcher(str);
			String tmp = "";
			int num = 0;
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			
			/**
			 * <tr><td>2015-04-17</td>
			 * <td>至今</td>
			 * <td><a href='http://fund.eastmoney.com/manager/30065310.html'>夏春晖</a> 
			 * <a href='http://fund.eastmoney.com/manager/30339882.html'>于航</a> </td>
			 * <td>39天</td>
			 * <td class="tor  red">46.77%</td>
			 * </tr>
			 */
			SAXReader saxReader = new SAXReader();
			Document document = null;
		Element tr = null;
		while(matcher.find()) {

			try {
				tmp = matcher.group();
				document = saxReader.read(new InputSource(new StringReader(tmp)));
				tr = document.getRootElement();
				List<Element> ps = tr.elements();

				String name = ((List<Element>)ps.get(0).elements()).get(1).getTextTrim();
				
				String profile = ps.get(2).getTextTrim();

				System.out.println(name+"\t"+profile+"\t");
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 

		}
//		}
//		rlt.append("</table></body>");
//		FileUtil.writeToFile(rlt.toString(), "d:\\manager.txt", false);

		
		
	}

}
