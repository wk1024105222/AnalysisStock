package crawler.stock.share.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import crawler.stock.share.dao.ShareDao;
import crawler.stock.share.service.ShareService;
import crawler.stock.vo.StockBonus;
import crawler.stock.vo.StockInfo;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

@Service
public class ShareServiceImpl implements ShareService {
	
	@Resource
	private ShareDao shareDao;

	/**
	 * 
	 * <p>Title: saveShareInfo</p>
	 * <p>Description: 从sina下载 分红配送信息</p>
	 * @param si
	 * @return
	 * @see crawler.stock.share.service.ShareService#saveShareInfo(crawler.stock.vo.StockInfo)
	 */
	public Result saveShareInfo(StockInfo si) {

		String url = getShareInfoUrlFromSina(si);
		
		if(url == null || url.equals("")) {
			return new Result(ResultFlag.Fail,si + "获取上市公司分红派送信息url获取异常,取消下载"); 
		}

		String line = null;
		
		Result rlt = null;

		for(int i = 0; i != 10; i++) {

			line = NetUtil.getHtmlSourceByUrl(url);

			if(line == null || line.length() == 0) {
				if(i == 9) {
					return new Result(ResultFlag.Fail,si + "from:" + url + "10次下载均失败"); 
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {

					e.printStackTrace();
				}
			} else {

				LogUtil.share.info(si + "from:" + url + "第" + i + "次下载成功");

				rlt = updateShareInfoFromSina(si, line);
				
				break;

			}
		}
		
		return rlt;
	}

	/**
	 * 
	 * @Title: updateShareInfoFromSina
	 * @Description: 从html中解析分红配送信息 并入库
	 * @param si
	 * @param line
	 * @return Result
	 * @throws
	 */
	private Result updateShareInfoFromSina(StockInfo si, String line) {
		
		String code = si.getCode();

		String reg ="<td>\\d{4}-\\d{2}-\\d{2}</td>\\s+" +
			 		"<td>\\d?\\.{0,1}\\d*</td>\\s+" +
			 		"<td>\\d?\\.{0,1}\\d*</td>\\s+" +
			 		"<td>\\d?\\.{0,1}\\d*</td>\\s+" +
			 		"<td>实施</td>\\s+" +
			 		"<td>\\d{4}-\\d{2}-\\d{2}</td>\\s+" +
			 		"<td>\\d{4}-\\d{2}-\\d{2}</td>\\s+" ;

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Pattern pattern = Pattern.compile(reg);
		Matcher matcher = pattern.matcher(line);
		String tmp = "";
		StockBonus sb = null;
		Elements tds = null;
		Document doc = null;
		String context = "";
		String[] typs = null;
		
		ArrayList<StockBonus> sbs = new ArrayList<StockBonus>();

		 /*
		  <body>
		  2015-04-30 1 15 0.6 实施 2015-05-07 2015-05-06 
		  </body>
		 */
		while (matcher.find()) {
			try {
				tmp = matcher.group();
				doc = Jsoup.parse(tmp);
				tds = doc.getElementsByTag("body");
				if(tds.size() == 0) {
					continue;
				}
				context = tds.get(0).text();
				typs = context.split(" ");
				sb = new StockBonus(code,
						Double.parseDouble(typs[1]),
						Double.parseDouble(typs[2]),
						Double.parseDouble(typs[3]),
						sdf.parse(typs[5]),
						sdf.parse(typs[6]),
						sdf.parse(typs[0])
						);
				sbs.add(sb);
				
			} catch (NumberFormatException e) {
				e.printStackTrace();
				LogUtil.share.info(code+"\t"+context+"入库失败"+"\r\n"+e.toString());
				continue;
			} catch (ParseException e) {
				e.printStackTrace();
				LogUtil.share.info(code+"\t"+context+"入库失败"+"\r\n"+e.toString());
				continue;
			}
		}

		try {
			for(int i = sbs.size() - 1; i >= 0; i--) {
				shareDao.saveShareInfo(sbs.get(i));
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result(ResultFlag.Fail, code + "分红送配信息入库失败");
		}

		return new Result(ResultFlag.Success, code + "\t"+sbs.size() + "条分红送配信息入库成功");

	}

	/**
	 * 
	 * @Title: getShareInfoUrlFromSina
	 * @Description: 生成 sina 分红配送信息页面 的 url 
	 * @param si
	 * @return String
	 * @throws
	 */
	private String getShareInfoUrlFromSina(StockInfo si) {

		return "http://vip.stock.finance.sina.com.cn/corp/go.php/vISSUE_ShareBonus/stockid/"+si.getCode()+".phtml";
	}

	public ShareDao getShareDao() {
	
		return shareDao;
	}

	public void setShareDao(ShareDao shareDao) {
	
		this.shareDao = shareDao;
	}

}
