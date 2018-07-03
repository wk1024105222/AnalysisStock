package crawler.cases.stock.ppcc.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import crawler.cases.stock.ppcc.dao.PpccDao;
import crawler.cases.stock.ppcc.service.PpccService;
import crawler.cases.stock.vo.Ppcc;
import crawler.cases.stock.vo.StockData;
import crawler.util.LogUtil;
import crawler.util.MathUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

@Service
public class PpccServiceImpl implements PpccService {

	@Resource
	private PpccDao ppccDao;

	/**
	 * 
	 * <p>Title: isNewComputePpccTask</p>
	 * <p>Description: ����beginDate endDate ����ppcc ���� num>0 δ�ϴ�δ������� num=0 ������</p>
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @see crawler.cases.stock.ppcc.service.PpccService#isNewComputePpccTask(java.util.Date, java.util.Date)
	 */
	public boolean isNewComputePpccTask(Date beginDate, Date endDate) {

		int num = ppccDao.getComputePpccNum(beginDate, endDate);

		return num == 0;
	}
	
	/**
	 * 
	 * <p>Title: getNotFinishPpcc</p>
	 * <p>Description: ��ȡδ��ɼ����ppcc��¼</p>
	 * @param beginDate
	 * @param endDate
	 * @return
	 * @see crawler.cases.stock.ppcc.service.PpccService#getNotFinishPpcc(java.util.Date, java.util.Date)
	 */
	public List<Ppcc> getNotFinishPpcc(Date beginDate, Date endDate) {

		return ppccDao.getNotFinishPpcc(beginDate, endDate);
	}
	
	public Result savePpcc(Ppcc t) {

		ppccDao.savePpcc(t);
		return new Result(ResultFlag.Success, t.toString() + "�ɼ�������������ɹ�");
	}
	
	public Result updatePpcc(Ppcc t) {

		ppccDao.updatePpcc(t);
		return new Result(ResultFlag.Success, t.toString() + "�ɼ�����Լ��������ɹ�");
	}
	
	/**
	 * 
	 * <p>Title: comptePpcc</p>
	 * <p>Description: ����2ֻ��Ʊ [begin, end]��3�����ϵ�� 1:���̼� 2:�Ƿ����� 3:�Ƿ���ֵ</p>
	 * @param p
	 * @return
	 * @see crawler.cases.stock.ppcc.service.PpccService#computePpcc(crawler.cases.stock.vo.Ppcc)
	 */
	public Result computePpcc(Ppcc p) {
		
		List<StockData> ap = new ArrayList<StockData>();

		List<StockData> bp = new ArrayList<StockData>();

		Result rlt = clearInvalidData(p, ap, bp);

		if(rlt.getFlag() == ResultFlag.Fail) {
			return rlt;
		} 
		if(ap.size() == 0 ) {
			return new Result(ResultFlag.Fail,p.toString()+"��������Ϊ��,ȡ������");
		}
		
		double close = computePpccByPrice(ap, bp);
		double gain = computePpccByGainPlusMinus(ap, bp);
		double gainValue = computePpccByGainValue(ap, bp);
		
		p.setClose(close);
		p.setGain(gain);
		p.setGainValue(gainValue);
		p.setNum(ap.size());
		return new Result(ResultFlag.Success,p.toString()+"�������ϵ������ɹ�");
	}

	/**
	 * 
	 * @Title: computePpccByGainPlusMinus
	 * @Description: �����ǵ��������������
	 * @param p
	 * @return Result
	 * @throws
	 */
	private double computePpccByGainPlusMinus(List<StockData> ap, List<StockData> bp) {

		StockData ad = null;
		StockData bd = null;

		int size = ap.size();
		int sameNum = 0;
		for(int c = 0; c != ap.size(); c++) {
			ad = ap.get(c);
			bd = bp.get(c);

			if(ad.getGain() != null && bd.getGain() != null) {
				if(ad.getGain() * bd.getGain() > 0) {
					sameNum += 1;
				} else if(ad.getGain() == 0.0D && bd.getGain() == 0.0D) {
					sameNum += 1;
				}
			}
		}
		
		double ppcc = MathUtil.sswr(sameNum * 100.0 / size, 4);

		return ppcc;
	}

	private double computePpccByGainValue(List<StockData> ap, List<StockData> bp) {
		StockData aData = null;
		StockData bData = null;

		double sum_xy = 0;
		double sum_x = 0;
		double sum_y = 0;
		double sum_x2 = 0;
		double sum_y2 = 0;
		long num = ap.size();

		for(int i = 0; i != ap.size(); i++) {
			aData = ap.get(i);
			bData = bp.get(i);

			sum_xy += aData.getGain() * bData.getGain();
			sum_x += aData.getGain();
			sum_y += bData.getGain();
			sum_x2 += aData.getGain() * aData.getGain();
			sum_y2 += bData.getGain() * bData.getGain();
		}

		double ppcc = ((num * sum_xy) - (sum_x * sum_y))
				/ (Math.sqrt(num * sum_x2 - sum_x * sum_x) * Math.sqrt(num * sum_y2 - sum_y * sum_y));

		return  MathUtil.sswr(ppcc, 4);
		
	}

	/**
	 * 
	 * @Title: computePpccByPrice
	 * @Description: �������̼ۼ��������
	 * @param p
	 * @return Result
	 * @throws
	 */
	private double computePpccByPrice(List<StockData> ap, List<StockData> bp) {
		StockData aData = null;
		StockData bData = null;

		double sum_xy = 0;
		double sum_x = 0;
		double sum_y = 0;
		double sum_x2 = 0;
		double sum_y2 = 0;
		long num = ap.size();

		for(int i = 0; i != ap.size(); i++) {
			aData = ap.get(i);
			bData = bp.get(i);

			sum_xy += aData.getClose() * bData.getClose();
			sum_x += aData.getClose();
			sum_y += bData.getClose();
			sum_x2 += aData.getClose() * aData.getClose();
			sum_y2 += bData.getClose() * bData.getClose();
		}

		double ppcc = ((num * sum_xy) - (sum_x * sum_y))
				/ (Math.sqrt(num * sum_x2 - sum_x * sum_x) * Math.sqrt(num * sum_y2 - sum_y * sum_y));

		return  MathUtil.sswr(ppcc, 4);
	}
	
	public Result clearInvalidData(final Ppcc p, List<StockData> arlt, List<StockData> brlt) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String a = p.getCodeA();
		String b = p.getCodeB();

		Date begin = p.getBeginDate();
		Date end = p.getEndDate();
		
		List<StockData> aDatas = ppccDao.getRangeDataByCode(a, begin, end);

		List<StockData> bDatas = ppccDao.getRangeDataByCode(b, begin, end);
		
		if(aDatas==null || bDatas==null || aDatas.size()==0 || bDatas.size()==0) {
			return new Result(ResultFlag.Fail,p.toString()+"һ������Ϊ��,ȡ������Լ���");
		}

		int asize = aDatas.size();
		int bsize = bDatas.size();

		StockData ad = null;
		StockData bd = null;
		
		for (int i=asize-1,j=bsize-1; i >= 0 && j>=0; i--,j--) {
			ad = aDatas.get(i);
			bd = bDatas.get(j);
			while(!(ad.getTxnDate().getTime() == bd.getTxnDate().getTime()) ){
				if(ad.getTxnDate().before(bd.getTxnDate())) {
					j--;
					bd = bDatas.get(j);
				} else {
					i--;
					ad = aDatas.get(i);
				}
			}
			if(ad.getVolume()>0 && bd.getVolume()>0) {
				arlt.add(ad);
				brlt.add(bd);	
				LogUtil.ppcc.info(ad.getCode()+","+sdf.format(ad.getTxnDate())+","+ad.getClose()+
							 	  bd.getCode()+","+sdf.format(bd.getTxnDate())+","+bd.getClose());
			} else {
				continue;
			}				
		}
		
		if(arlt.size() == brlt.size()) {
			return new Result(ResultFlag.Success,p.toString()+"˫����������ɹ�,��������"+arlt.size());
		} else {
			return new Result(ResultFlag.Fail,p.toString()+"˫�����������쳣, �����˫����������������һ��"+a+":"+arlt.size()+"\t"+b+":"+brlt.size());
		}

	}
	
	public PpccDao getPpccDao() {

		return ppccDao;
	}

	public void setPpccDao(PpccDao ppccDao) {

		this.ppccDao = ppccDao;
	}

	

	

	



	


	

}
