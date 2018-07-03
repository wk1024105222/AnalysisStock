package crawler.cases.stock.bis.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import crawler.cases.stock.bis.dao.StockBisDao;
import crawler.cases.stock.bis.service.StockBisService;
import crawler.cases.stock.vo.Balance;
import crawler.cases.stock.vo.CashFlow;
import crawler.cases.stock.vo.Income;
import crawler.cases.stock.vo.StockInfo;
import crawler.util.FileUtil;
import crawler.util.LogUtil;
import crawler.util.NetUtil;
import crawler.util.Result;
import crawler.util.ResultFlag;

/**
 * 
 * @ClassName: BisServiceImpl
 * @Description: 从sina 下载三张财报 并存入数据库
 * @author yt
 * @date 2015-5-14 下午03:39:05
 *
 */
@Service
public class StockBisServiceImpl implements StockBisService {
	
	@Resource
	private StockBisDao stockBisDao;
	
	/**
	 * 利润表（Income Statement）
	 * @param code
	 * @return
	 */
	public String getISUrl(String code) {

		return "http://money.finance.sina.com.cn/corp/go.php/vDOWN_ProfitStatement/displaytype/4/stockid/" 
				+ code 
				+ "/ctrl/all.phtml";
	}

	/**
	 * 资产负债表（Balance Sheet）
	 * @param code
	 * @return
	 */
	public String getBSUrl(String code) {
		return "http://money.finance.sina.com.cn/corp/go.php/vDOWN_BalanceSheet/displaytype/4/stockid/" 
		+ code 
		+ "/ctrl/all.phtml";
	}

	/**
	 * 现金流量表（Cash Flow Statements）
	 * @param code
	 * @return
	 */
	public String getCSUrl(String code) {
		return "http://money.finance.sina.com.cn/corp/go.php/vDOWN_CashFlow/displaytype/4/stockid/" 
		+ code 
		+ "/ctrl/all.phtml";
	}
	
	public String getISFileName(String code) {
		
		return code+"_利润表.xls";
	}
	
	public String getBSFileName(String code) {
		return code+"_资产负债表.xls";
	}
	
	public String getCSFileName(String code) {
		return code + "_现金流量表.xls";
	}
	
	/**
	 * 
	 * @Title: getAmountUnit
	 * @Description: 将报表中的单位转换成 元
	 * @param @param chinese
	 * @param @return
	 * @return int
	 * @throws
	 */
	public int getAmountUnit(String chinese) {
		int unit = 1;
		if(chinese == null ) {
			unit = 1;
		} else if(chinese.equals("元")) {
			unit = 1;
		} else if(chinese.equals("千元")) {
			unit = 1000;
		} else if(chinese.equals("万元")) {
			unit = 10000;
		} else if(chinese.equals("百万")) {
			unit = 1000000;
		}
		return unit;
	}
	
	/**
	 * 
	 * <p>Title: saveIncome</p>
	 * <p>Description: 将利润表.xls数据导入数据库</p>
	 * @param fileName
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#saveIncome(java.lang.String)
	 */
	public Result saveIncome(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		
		File is = new File(fileName);
		ArrayList<String> allLines = FileUtil.getListFromFile(is);
		
		int size = allLines.get(0).split("\t").length;
		String[][] max = new String[allLines.size()][size];
		String[] tmp = null;
		LogUtil.bis.info(fileName+""+allLines.size()+":"+allLines.get(1));
		
		for(int i=0; i!=allLines.size(); i++) {
			tmp = allLines.get(i).split("\t");
			if(tmp.length == size) {
				max[i] = tmp;
			} else {
				max[i] = new String[size];
				for(int j=0; j!=tmp.length; j++) {
					max[i][j]=tmp[j];
				}
			}
		}
		
		Income income = null;
		int unit = 1;
		if(allLines.size() == 51) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					income = new Income(is.getName().substring(0, 6),
										sdf.parse(max[0][x]),
										Double.parseDouble(max[2][x])*unit,
										Double.parseDouble(max[9][x])*unit,
										Double.parseDouble(max[27][x])*unit,
										Double.parseDouble(max[34][x])*unit,
										Double.parseDouble(max[41][x])*unit,
										Double.parseDouble(max[42][x])*unit,
										Double.parseDouble(max[43][x])*unit
										);
					stockBisDao.saveIncome(income);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				}
			}
		} else if(allLines.size() == 47) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					income = new Income(is.getName().substring(0, 6),
										sdf.parse(max[0][x]),
										Double.parseDouble(max[2][x])*unit,
										Double.parseDouble(max[19][x])*unit,
										Double.parseDouble(max[15][x])*unit,
										Double.parseDouble(max[26][x])*unit,
										Double.parseDouble(max[32][x])*unit+Double.parseDouble(max[31][x])*unit,
										Double.parseDouble(max[32][x])*unit,
										Double.parseDouble(max[31][x])*unit
										);
					stockBisDao.saveIncome(income);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,is.getName()+"入库失败\r\n"+e.toString());
				}
			}
		}
		
		return new Result(ResultFlag.Success,is.getName()+"入库成功");
	} 
	
	/**
	 * 
	 * <p>Title: saveBalance</p>
	 * <p>Description: 将资产负债表.xls数据导入数据库</p>
	 * @param fileName
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#saveBalance(java.lang.String)
	 */
	public Result saveBalance(String fileName) {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		File bs = new File(fileName);
		ArrayList<String> allLines = FileUtil.getListFromFile(bs);
		int size = allLines.get(0).split("\t").length;
		String[][] max = new String[allLines.size()][size];
		String[] tmp = null;
		LogUtil.bis.info(fileName+""+allLines.size()+":"+allLines.get(1));
		
		for(int i=0; i!=allLines.size(); i++) {
			tmp = allLines.get(i).split("\t");
			if(tmp.length == size) {
				max[i] = tmp;
			} else {
				max[i] = new String[size];
				for(int j=0; j!=tmp.length; j++) {
					max[i][j]=tmp[j];
				}
			}
		}
		Balance balance = null;
		int unit = 1;
		if(allLines.size() == 114) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					balance = new Balance(bs.getName().substring(0, 6),
							sdf.parse(max[0][x]),
							Double.parseDouble(max[27][x])*unit,
							Double.parseDouble(max[54][x])*unit,
							Double.parseDouble(max[55][x])*unit,
							Double.parseDouble(max[88][x])*unit,
							Double.parseDouble(max[97][x])*unit,
							Double.parseDouble(max[98][x])*unit,
							Double.parseDouble(max[112][x])*unit,
							Double.parseDouble(max[110][x])*unit,
							Double.parseDouble(max[111][x])*unit
							);
					
					stockBisDao.saveBalance(balance);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				}
			}
		} else if(allLines.size() == 98) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					balance = new Balance(bs.getName().substring(0, 6),
										sdf.parse(max[0][x]),
										Double.parseDouble(max[46][x])*unit,
										Double.parseDouble(max[80][x])*unit,
										Double.parseDouble(max[96][x])*unit,
										Double.parseDouble(max[94][x])*unit,
										Double.parseDouble(max[95][x])*unit
										);
					stockBisDao.saveBalance(balance);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,bs.getName()+"入库失败\r\n"+e.toString());
				}
			}
		}
		return new Result(ResultFlag.Success,bs.getName()+"入库成功");
	} 
	
	/**
	 * 
	 * <p>Title: saveCashFlow</p>
	 * <p>Description: 将现金流量表.xls数据导入数据库</p>
	 * @param fileName
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#saveCashFlow(java.lang.String)
	 */
	public Result saveCashFlow(String fileName) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		File cs = new File(fileName);
		ArrayList<String> allLines = FileUtil.getListFromFile(cs);
		int size = allLines.get(0).split("\t").length;
		String[][] max = new String[allLines.size()][size];
		String[] tmp = null;
		LogUtil.bis.info(fileName+""+allLines.size()+":"+allLines.get(1));
		
		for(int i=0; i!=allLines.size(); i++) {
			tmp = allLines.get(i).split("\t");
			if(tmp.length == size) {
				max[i] = tmp;
			} else {
				max[i] = new String[size];
				for(int j=0; j!=tmp.length; j++) {
					max[i][j]=tmp[j];
				}
			}
		}
		CashFlow cashFlow = null;
		int unit = 1;
		if(allLines.size() == 95) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					cashFlow = new CashFlow(cs.getName().substring(0, 6),
							sdf.parse(max[0][x]),
							Double.parseDouble(max[16][x])*unit,
							Double.parseDouble(max[26][x])*unit,
							Double.parseDouble(max[27][x])*unit,
							
							Double.parseDouble(max[35][x])*unit,
							Double.parseDouble(max[42][x])*unit,
							Double.parseDouble(max[43][x])*unit,
							
							Double.parseDouble(max[50][x])*unit,
							Double.parseDouble(max[55][x])*unit,
							Double.parseDouble(max[56][x])*unit,
							
							Double.parseDouble(max[59][x])*unit,
							Double.parseDouble(max[60][x])*unit,
							Double.parseDouble(max[61][x])*unit
							);
					
					stockBisDao.saveCashFlow(cashFlow);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				}
			}
		} else if(allLines.size() == 152) {
			for(int x=1; x!=size-1; x++) {
				try {
					unit = getAmountUnit(max[1][x]);
					
					cashFlow = new CashFlow(cs.getName().substring(0, 6),
							sdf.parse(max[0][x]),
							Double.parseDouble(max[32][x])*unit,
							Double.parseDouble(max[62][x])*unit,
							Double.parseDouble(max[63][x])*unit,
							
							Double.parseDouble(max[73][x])*unit,
							Double.parseDouble(max[82][x])*unit,
							Double.parseDouble(max[83][x])*unit,
							
							Double.parseDouble(max[91][x])*unit,
							Double.parseDouble(max[97][x])*unit,
							Double.parseDouble(max[98][x])*unit,
							
							Double.parseDouble(max[100][x])*unit,
							Double.parseDouble(max[101][x])*unit,
							Double.parseDouble(max[102][x])*unit
							);
					stockBisDao.saveCashFlow(cashFlow);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				} catch (ParseException e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				} catch (Exception e) {
					e.printStackTrace();
					return new Result(ResultFlag.Fail,cs.getName()+"入库失败\r\n"+e.toString());
				}
			}
		}
		return new Result(ResultFlag.Success,cs.getName()+"入库成功");
	}

	/**
	 * 
	 * <p>Title: downBalance</p>
	 * <p>Description: 从sina下载资产负债表</p>
	 * @param si
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#downBalance(crawler.cases.stock.vo.StockInfo)
	 */
	public String downBalance(StockInfo si) {

		String code = si.getCode();

		String bsUrl = getBSUrl(code);

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		File loc = new File("d:\\stock\\Bis" + today);
		if(!loc.exists()) {
			loc.mkdir();
			LogUtil.bis.info("创建目录" + loc.getAbsolutePath());
		}

		String bsName = loc.getAbsolutePath() + "\\" + getBSFileName(code);

		Result rlt = null;

		// 下载资产负债表
		if(new File(bsName).exists()) {
			LogUtil.bis.info(bsName + "已下载");

		} else {
			LogUtil.bis.info(NetUtil.downFile(bsUrl, bsName).getDes().toString());

		}

		return bsName;
	}
	
	/**
	 * 
	 * <p>Title: downCashFlow</p>
	 * <p>Description: 从sina下载现金流量表</p>
	 * @param si
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#downCashFlow(crawler.cases.stock.vo.StockInfo)
	 */
	public String downCashFlow(StockInfo si) {

		String code = si.getCode();

		String csUrl = getCSUrl(si.getCode());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		File loc = new File("d:\\stock\\Bis" + today);
		if(!loc.exists()) {
			loc.mkdir();
			LogUtil.bis.info("创建目录" + loc.getAbsolutePath());
		}

		String csName = loc.getAbsolutePath() + "\\" + getCSFileName(code);

		Result rlt = null;

		// 下载现金流量表
		if(new File(csName).exists()) {
			LogUtil.bis.info(csName + "已下载");
			
		} else {
			LogUtil.bis.info(NetUtil.downFile(csUrl, csName).getDes().toString());
			
		}

		return csName;
	}
	
	/**
	 * 
	 * <p>Title: downIncome</p>
	 * <p>Description: 从sina下载利润表</p>
	 * @param si
	 * @return
	 * @see crawler.stock.LogUtil.bis.service.BisService#downIncome(crawler.cases.stock.vo.StockInfo)
	 */
	public String downIncome(StockInfo si) {

		String code = si.getCode();

		String isUrl = getISUrl(si.getCode());

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		File loc = new File("d:\\stock\\Bis" + today);
		if(!loc.exists()) {
			loc.mkdir();
			LogUtil.bis.info("创建目录" + loc.getAbsolutePath());
		}

		String isName = loc.getAbsolutePath() + "\\" + getISFileName(code);

		Result rlt = null;

		// 下载利润表
		if(new File(isName).exists()) {
			LogUtil.bis.info(isName + "已下载");
			
		} else {
			LogUtil.bis.info(NetUtil.downFile(isUrl, isName).getDes().toString());
			
		}
		
		return isName;
	} 

}
