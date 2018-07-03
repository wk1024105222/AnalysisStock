package crawler.stock.vo;

import java.util.Date;

/**
 * Cashflow entity. @author MyEclipse Persistence Tools
 */

public class CashFlow implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private Date releaseDate;
	private double jyxjlr;
	private double jyxjlc;
	private double jyxjje;
	private double tzxjlr;
	private double tzxjlc;
	private double tzxjje;
	private double czxjlr;
	private double czxjlc;
	private double czxjje;
	private double xjzje;
	private double qcxjye;
	private double qmxjye;

	// Constructors

	/** default constructor */
	public CashFlow() {
	}

	/** minimal constructor */
	public CashFlow(String code) {
		this.code = code;
	}

	/** full constructor */
	public CashFlow(String code, Date releaseDate, double jyxjlr, double jyxjlc, double jyxjje, double tzxjlr, double tzxjlc, double tzxjje, double czxjlr, double czxjlc, double czxjje, double xjzje,
			double qcxjye, double qmxjye) {
		this.code = code;
		this.releaseDate = releaseDate;
		this.jyxjlr = jyxjlr;
		this.jyxjlc = jyxjlc;
		this.jyxjje = jyxjje;
		this.tzxjlr = tzxjlr;
		this.tzxjlc = tzxjlc;
		this.tzxjje = tzxjje;
		this.czxjlr = czxjlr;
		this.czxjlc = czxjlc;
		this.czxjje = czxjje;
		this.xjzje = xjzje;
		this.qcxjye = qcxjye;
		this.qmxjye = qmxjye;
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

	public Date getReleaseDate() {
		return this.releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getJyxjlr() {
		return this.jyxjlr;
	}

	public void setJyxjlr(double jyxjlr) {
		this.jyxjlr = jyxjlr;
	}

	public double getJyxjlc() {
		return this.jyxjlc;
	}

	public void setJyxjlc(double jyxjlc) {
		this.jyxjlc = jyxjlc;
	}

	public double getJyxjje() {
		return this.jyxjje;
	}

	public void setJyxjje(double jyxjje) {
		this.jyxjje = jyxjje;
	}

	public double getTzxjlr() {
		return this.tzxjlr;
	}

	public void setTzxjlr(double tzxjlr) {
		this.tzxjlr = tzxjlr;
	}

	public double getTzxjlc() {
		return this.tzxjlc;
	}

	public void setTzxjlc(double tzxjlc) {
		this.tzxjlc = tzxjlc;
	}

	public double getTzxjje() {
		return this.tzxjje;
	}

	public void setTzxjje(double tzxjje) {
		this.tzxjje = tzxjje;
	}

	public double getCzxjlr() {
		return this.czxjlr;
	}

	public void setCzxjlr(double czxjlr) {
		this.czxjlr = czxjlr;
	}

	public double getCzxjlc() {
		return this.czxjlc;
	}

	public void setCzxjlc(double czxjlc) {
		this.czxjlc = czxjlc;
	}

	public double getCzxjje() {
		return this.czxjje;
	}

	public void setCzxjje(double czxjje) {
		this.czxjje = czxjje;
	}

	public double getXjzje() {
		return this.xjzje;
	}

	public void setXjzje(double xjzje) {
		this.xjzje = xjzje;
	}

	public double getQcxjye() {
		return this.qcxjye;
	}

	public void setQcxjye(double qcxjye) {
		this.qcxjye = qcxjye;
	}

	public double getQmxjye() {
		return this.qmxjye;
	}

	public void setQmxjye(double qmxjye) {
		this.qmxjye = qmxjye;
	}

}