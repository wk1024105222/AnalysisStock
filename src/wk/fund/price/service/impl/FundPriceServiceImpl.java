package wk.fund.price.service.impl;

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

import wk.fund.price.dao.FundPriceDao;
import wk.fund.price.service.FundPriceService;
import wk.fund.vo.FundData;
import wk.util.LogUtil;
import wk.util.Result;
import wk.util.ResultFlag;

@Service
public class FundPriceServiceImpl implements FundPriceService {
	
	@Resource
	private FundPriceDao fundPriceDao;

	public String getHexunPriceUrl(String code) {

		return "http://jingzhi.funds.hexun.com/DataBase/jzzs.aspx?fundcode=" + code;
		
	}

	public Result saveFundPriceFromHexun(String code, String html) {

		/*  <tr>
			<td style="text-align: center;">2015-05-19</td>
			<td style="text-align: center;">1.8100</td>
			<td style="text-align: center;" class="end">5.0340</td>
			<td style="text-align: center;" class="f_red">3.19%</td>
			</tr>
		 */
		String reg =
		"<tr>\\s*" +
		"<td style=\"text-align: center;\">\\d{4}-\\d{2}-\\d{2}</td>\\s*" +
 		"<td style=\"text-align: center;\">-{0,1}\\d?\\.{0,1}\\d*</td>\\s*" +
 		"<td style=\"text-align: center;\" class=\"end\">-{0,1}\\d?\\.{0,1}\\d*</td>\\s*" +
 		"<td style=\"text-align: center;\" class=\"(f_red|f_green)\">-{0,1}\\d?\\.{0,1}\\d*%</td>\\s*</tr>" 
 		;
		
		ArrayList<FundData> fds = getFundPriceListFromHexunHtml(code, html, reg );

		LogUtil.downFunPrice.info(code+":"+fds.size()+"条记录");
		
		try {
			for(int i=fds.size()-1; i>=0; i--) {
				fundPriceDao.saveFundData(fds.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, code.toString()+"入库失败");
		}

		return new Result(ResultFlag.Success,code+"入库成功");
	}

	private ArrayList<FundData> getFundPriceListFromHexunHtml(String code, String html, String reg) {
		
		List a = fundPriceDao.getMaxDateByCode(code);
	
		Date maxDate=(Date) a.get(0);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(html);
		String tmp = "";
		FundData fd = null;

		ArrayList<FundData> fds = new ArrayList<FundData>();

		SAXReader saxReader = new SAXReader();
		Document document = null;
		Element tr = null;

		while(matcher.find()) {

			try {
				tmp = matcher.group();

				document = saxReader.read(new InputSource(new StringReader(tmp)));
				tr = document.getRootElement();
				List<Element> tds = tr.elements();

				String date = tds.get(0).getTextTrim();
				String nuv = tds.get(1).getTextTrim();
				String auv = tds.get(2).getTextTrim();
				String gain = tds.get(3).getTextTrim();

				if(sdf.parse(date).after(maxDate)) {
					fd = new FundData(code, sdf.parse(date), Double.parseDouble(nuv), Double.parseDouble(auv), Double
							.parseDouble(gain.substring(0, gain.length() - 1)) / 100);
					fds.add(fd);
				}
				
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (DocumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return fds;
	}

}
