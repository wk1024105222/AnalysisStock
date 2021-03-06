package crawler.cases.stock.ppcc.service;

import java.util.Date;
import java.util.List;

import crawler.cases.stock.vo.Ppcc;
import crawler.util.Result;


public interface PpccService {



	boolean isNewComputePpccTask(Date begin, Date end);

	List<Ppcc> getNotFinishPpcc(Date begin, Date end);

	Result computePpcc(Ppcc t);

	Result savePpcc(Ppcc t);

	Result updatePpcc(Ppcc t);

}
