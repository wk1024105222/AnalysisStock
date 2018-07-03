package crawler.cases.stocknew.ppcc.dao;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import crawler.cases.stocknew.vo.Ppcc;
import crawler.cases.stocknew.vo.StockData;

public interface PpccDao {

	int getComputePpccNum(Date beginDate, Date endDate);

	List<Ppcc> getNotFinishPpcc(Date beginDate, Date endDate);

	Serializable savePpcc(Ppcc t);

	void updatePpcc(Ppcc t);

	List<StockData> getRangeDataByCode(String a, Date beginDate, Date endDate);

}
