package crawler.fund.vo;

import java.util.Date;

/**
 * FundData entity. @author MyEclipse Persistence Tools
 */

public class FundData implements java.io.Serializable {

	// Fields

	private String id;

	private String code;

	private Date txnDate;

	private double nuv;

	private double auv;

	private double gain;

	// Constructors

	/** default constructor */
	public FundData() {

	}

	/** minimal constructor */
	public FundData(String code) {

		this.code = code;
	}

	public FundData(String code, Date txnDate) {

		this.code = code;
		this.txnDate = txnDate;
	}

	/** full constructor */
	public FundData(String code, Date txnDate, double nuv, double auv, double gain) {

		this.code = code;
		this.txnDate = txnDate;
		this.nuv = nuv;
		this.auv = auv;
		this.gain = gain;
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

	public double getNuv() {

		return nuv;
	}

	public void setNuv(double nuv) {

		this.nuv = nuv;
	}

	public double getAuv() {

		return auv;
	}

	public void setAuv(double auv) {

		this.auv = auv;
	}

	public double getGain() {

		return gain;
	}

	public void setGain(double gain) {

		this.gain = gain;
	}

	// Property accessors

}
