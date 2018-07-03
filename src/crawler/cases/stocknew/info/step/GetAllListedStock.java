package crawler.cases.stocknew.info.step;

import java.util.Collection;

import crawler.base.BaseStep;
import crawler.util.NetUtil;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.XPath;
import org.dom4j.io.SAXReader;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class GetAllListedStock extends BaseStep<String> {

	public GetAllListedStock(String inputQueueName, String outputQueueName, String dbQueueName, int emptyWait,
			int requestWait) {

		super(inputQueueName, outputQueueName, dbQueueName, emptyWait, requestWait);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getTask() {

		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void handle(String t) {

		// TODO Auto-generated method stub

	}

	@Override
	public boolean isDone(String t) {

		// TODO Auto-generated method stub
		return false;
	}

	public static void fillInputQueue() {

	}

	public static Collection fillDoneList() {

		return null;

	}

	public static InputStream getStringStream(String sInputString) {

		if(sInputString != null && !sInputString.trim().equals("")) {
			try {
				ByteArrayInputStream tInputStringStream = new ByteArrayInputStream(sInputString.getBytes());
				return tInputStringStream;
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {

		String html = NetUtil.getHtmlSourceByUrl("http://quote.eastmoney.com/stocklist.html").replace("&", "&amp;");

		try {

			InputStream is = getStringStream(html);

			SAXReader reader = new SAXReader();
			reader.setEncoding("GB2312");
			Document doc = reader.read(is);

			String xpath = "//*[@id='quotesearch']/ul/li/a[@target='_blank']/text()";

			List<Element> list = doc.selectNodes(xpath);
			for(Object o : list) {
				Element e = (Element) o;
				String show = e.getStringValue();

			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} 

	}

}
