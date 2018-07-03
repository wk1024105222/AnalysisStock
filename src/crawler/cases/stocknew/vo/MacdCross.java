package crawler.cases.stocknew.vo;

import java.util.Date;

/**
 * Macdcross entity. @author MyEclipse Persistence Tools
 */

public class MacdCross implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private Date txnDate;
	private String type;

	// Constructors

	/** default constructor */
	public MacdCross() {
	}

	public MacdCross(String id, String code, Date txnDate, String type) {
		super();
		this.id = id;
		this.code = code;
		this.txnDate = txnDate;
		this.type = type;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}