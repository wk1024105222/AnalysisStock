package wk.stock.macd.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import wk.stock.macd.dao.MacdDao;
import wk.stock.macd.service.MacdService;
import wk.stock.price.dao.PriceDao;
import wk.stock.vo.MacdCross;
import wk.stock.vo.StockData;
import wk.stock.vo.StockInfo;
import wk.util.MathUtil;
import wk.util.Result;
import wk.util.ResultFlag;

@Service
public class MacdServiceImpl implements MacdService {

	public static String GOLDCROSS = "1";

	public static String DEATHCROSS = "0";

	@Resource
	private PriceDao priceDao;
	
	@Resource
	private MacdDao macdDao;

	/**
	 * 计算一只股票所有交易日期的MACD
	 * 
	 * @param si
	 */
	public Result saveMacd(StockInfo si) {

		String code = si.getCode();

		List<StockData> allData = priceDao.getPriceListByCode(code);

		StockData sd;
		StockData ytd_sd;
		double ema12;
		double ema26;
		double diff;
		double dea;
		double bar;
		
		int a = 0;
		int b=0 ;

		for(int i = 0; i != allData.size(); i++) {
			sd = allData.get(i);
			if(sd.getEma12() != null || 
					sd.getEma26() != null || 
					sd.getDiff() != null || 
					sd.getDea() != null || 
					sd.getBar() != null) {
				a++;
				continue;
			}
			if(i == 0) {
				sd.setEma12(0D);
				sd.setEma26(0D);
				sd.setDea(0D);
				sd.setDiff(0D);
				sd.setBar(0D);

			} else if(i == 1) {
				ytd_sd = allData.get(i - 1);

				ema12 = MathUtil.sswr(ytd_sd.getClose() * 11.0D / 13 + sd.getClose() * 2.0D / 13);
				ema26 = MathUtil.sswr(ytd_sd.getClose() * 25.0D / 27 + sd.getClose() * 2.0D / 27);
				diff = ema12 - ema26;
				dea = MathUtil.sswr(ytd_sd.getDea() * 8.0D / 10 + diff * 2.0D / 10);
				bar = MathUtil.sswr(2.0D * (diff - dea));

				sd.setEma12(ema12);
				sd.setEma26(ema26);
				sd.setDiff(diff);
				sd.setDea(dea);
				sd.setBar(bar);
			} else {
				ytd_sd = allData.get(i - 1);

				ema12 = MathUtil.sswr(ytd_sd.getEma12() * 11.0D / 13 + sd.getClose() * 2.0D / 13);
				ema26 = MathUtil.sswr(ytd_sd.getEma26() * 25.0D / 27 + sd.getClose() * 2.0D / 27);
				diff = ema12 - ema26;
				dea = MathUtil.sswr(ytd_sd.getDea() * 8.0D / 10 + diff * 2.0D / 10);
				bar = MathUtil.sswr(2.0D * (diff - dea));

				sd.setEma12(ema12);
				sd.setEma26(ema26);
				sd.setDiff(diff);
				sd.setDea(dea);
				sd.setBar(bar);
			}
			b++;
			priceDao.updateStockData(sd);

		}
		return new Result(ResultFlag.Success, si.getCode() + "Macd计算完成 共有记录"+allData.size()+"\t待计算"+b+"\t已计算"+a);
	}

	/**
	 * 计算指定交易日的MACD
	 * 
	 * @param ytd
	 * @param td
	 */
	public void calculationMACD(StockData td) {

		this.calculationMACD(priceDao.getPreTxnData(td), td);
	}

	/**
	 * 根据上一个开盘交易计算当前交易日的MACD
	 * 
	 * @param ytd
	 * @param td
	 */
	public void calculationMACD(StockData ytd, StockData td) {

		double ema12;
		double ema26;
		double diff;
		double dea;
		double bar;

		if(ytd == null) {// 上市第一天
			td.setEma12(0D);
			td.setEma26(0D);
			td.setDea(0D);
			td.setDiff(0D);
			td.setBar(0D);

		} else if(priceDao.isListedSecondDay(td)) {// 上市第二天
			ema12 = MathUtil.sswr(ytd.getClose() * 11.0D / 13 + td.getClose() * 2.0D / 13);
			ema26 = MathUtil.sswr(ytd.getClose() * 25.0D / 27 + td.getClose() * 2.0D / 27);
			diff = ema12 - ema26;
			dea = MathUtil.sswr(ytd.getDea() * 8.0D / 10 + diff * 2.0D / 10);
			bar = MathUtil.sswr(2.0D * (diff - dea));

			td.setEma12(ema12);
			td.setEma26(ema26);
			td.setDiff(diff);
			td.setDea(dea);
			td.setBar(bar);
		} else {
			ema12 = MathUtil.sswr(ytd.getEma12() * 11.0D / 13 + td.getClose() * 2.0D / 13);
			ema26 = MathUtil.sswr(ytd.getEma26() * 25.0D / 27 + td.getClose() * 2.0D / 27);
			diff = ema12 - ema26;
			dea = MathUtil.sswr(ytd.getDea() * 8.0D / 10 + diff * 2.0D / 10);
			bar = MathUtil.sswr(2.0D * (diff - dea));

			td.setEma12(ema12);
			td.setEma26(ema26);
			td.setDiff(diff);
			td.setDea(dea);
			td.setBar(bar);
		}
		
	}

	public Result saveMacdCross(String code) {

		List<StockData> allData = priceDao.getPriceListByCode(code);

		StockData pre;
		StockData cur;
		//StockData next;
		String type = null;
		MacdCross ms;
		int glod = 0;
		int death = 0;
		for(int i = 1; i != allData.size(); i++) {
			pre = allData.get(i - 1);
			cur = allData.get(i);

			if(cur.getDiff() > cur.getDea()) {
				if(pre.getDiff() <= pre.getDea()) {
					type = MacdServiceImpl.GOLDCROSS;
					glod++;
				} else {
					continue;
				}
			} else if(cur.getDiff() == cur.getDea()) {
				/*
				if(i + 1 < allData.size()) {
					next = allData.get(i + 1);

					if(next.getDiff() < next.getDea()) {
						type = MacdServiceImpl.DEATHCROSS;
					} else if(next.getDiff() > next.getDea()) {
						type = MacdServiceImpl.GOLDCROSS;
					} else {
						continue;
					}
				}
				*/
			} else {
				if(pre.getDiff() >= pre.getDea()) {
					type = MacdServiceImpl.DEATHCROSS;
					death++;
				} else {
					continue;
				}
			}

			ms = new MacdCross();
			ms.setCode(code);
			ms.setTxnDate(cur.getTxnDate());
			ms.setType(type);
			macdDao.saveMacdCross(ms);
		}
		return new Result(ResultFlag.Success, code + "Macd交叉查找完成 金叉"+glod+"\t死叉"+death);
	}

	public PriceDao getPriceDao() {

		return priceDao;
	}

	public void setPriceDao(PriceDao priceDao) {

		this.priceDao = priceDao;
	}

	public MacdDao getMacdDao() {

		return macdDao;
	}

	public void setMacdDao(MacdDao macdDao) {

		this.macdDao = macdDao;
	}

	public List<String> getLsitWithputMacd() {

		// TODO Auto-generated method stub
		return macdDao.getLsitWithputMacd();
	}

}
