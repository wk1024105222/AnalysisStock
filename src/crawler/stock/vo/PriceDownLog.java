package crawler.stock.vo;

import java.util.Date;

/**
 * PriceDownLog entity. @author MyEclipse Persistence Tools
 */

public class PriceDownLog implements java.io.Serializable {

	// Fields

	private String id;

	private String code;

	private Date beginDate;

	private Date endDate;

	private String year;

	private String season;

	private Date txnDate;

	private String flag;

	private Integer num;

	private Date createDate;

	// Constructors

	/** default constructor */
	public PriceDownLog() {

	}

	/** minimal constructor */
	public PriceDownLog(String code, String flag) {

		this.code = code;
		this.flag = flag;
	}

	/** full constructor */
	public PriceDownLog(String code, Date beginDate, Date endDate, String year, String season, Date txnDate, String flag) {

		super();
		this.code = code;
		this.beginDate = beginDate;
		this.endDate = endDate;
		this.year = year;
		this.season = season;
		this.txnDate = txnDate;
		this.flag = flag;
	}

	// Property accessors

	public String getId() {

		return this.id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getCode() {

		return this.code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public String getYear() {

		return this.year;
	}

	public void setYear(String year) {

		this.year = year;
	}

	public String getSeason() {

		return this.season;
	}

	public void setSeason(String season) {

		this.season = season;
	}

	public Date getBeginDate() {

		return beginDate;
	}

	public void setBeginDate(Date beginDate) {

		this.beginDate = beginDate;
	}

	public Date getEndDate() {

		return endDate;
	}

	public void setEndDate(Date endDate) {

		this.endDate = endDate;
	}

	public Date getTxnDate() {

		return txnDate;
	}

	public void setTxnDate(Date txnDate) {

		this.txnDate = txnDate;
	}

	public String getFlag() {

		return this.flag;
	}

	public void setFlag(String flag) {

		this.flag = flag;
	}

	public Integer getNum() {

		return num;
	}

	public void setNum(Integer num) {

		this.num = num;
	}

	public Date getCreateDate() {

		return createDate;
	}

	public void setCreateDate(Date createDate) {

		this.createDate = createDate;
	}

}
