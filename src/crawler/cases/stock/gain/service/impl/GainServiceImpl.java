package crawler.cases.stock.gain.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import crawler.cases.stock.gain.service.GainService;
import crawler.cases.stock.price.dao.PriceDao;
import crawler.cases.stock.vo.StockData;
import crawler.cases.stock.vo.StockInfo;
import crawler.util.MathUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

@Service
public class GainServiceImpl implements GainService {

	@Resource
	private PriceDao priceDao;

	public Result savecalculationGain(StockInfo si) {

		String code = si.getCode();

		List<StockData> allData = priceDao.getPriceListByCode(code);

		StockData sd;
		StockData ytd_sd;
		Double gain = null;
		int success = 0;
		int fail = 0;
		int notNeed = 0;
		if(allData.size() == 0) {
			return new Result(ResultFlag.Fail, si.getCode() + "无数据待计算");
		}

		for(int i = 1; i != allData.size(); i++) {
			try {
				sd = allData.get(i);
				if(sd.getGain() != null) {
					notNeed++;
					continue;
				}

				ytd_sd = allData.get(i - 1);
				if(ytd_sd.getClose() == 0) {
					continue;
				}
				gain = MathUtil.sswr((sd.getClose() - ytd_sd.getClose()) * 100 / ytd_sd.getClose(), 2);
				sd.setGain(gain);
				priceDao.updateStockData(sd);
				success++;
			} catch (Exception e) {
				fail++;
				e.printStackTrace();
			}
		}
		return new Result(ResultFlag.Fail, si.getCode() + "共有交易:" + allData.size() + ",入库成功:" + success + ",入库失败:"
				+ fail + ",无需计算:" + notNeed);
	}

	public String createGainStr(StockInfo si) {
		
		List<StockData> allData = priceDao.getPriceListByCode(si.getCode());
		StringBuffer str = new StringBuffer(si.getCode()+",");
		for(int i=0; i!=allData.size(); i++) {
			str.append((int)MathUtil.sswr(allData.get(i).getGain(), 0)).append(";");
		}
		
		return str.toString();
	}

}
