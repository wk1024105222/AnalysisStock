package crawler.cases.stocknew.vo;

import java.util.Date;

/**
 * Income entity. @author MyEclipse Persistence Tools
 */

public class Income implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private Date releaseDate;
	private double yyzsr;
	private double yyzcb;
	private double tzsy;
	private double yylr;
	private double jlr;
	private double mgsgdjlr;
	private double ssgdsy;

	// Constructors

	/** default constructor */
	public Income() {
	}

	/** minimal constructor */
	public Income(String code) {
		this.code = code;
	}

	/** full constructor */
	public Income(String code, Date releaseDate, double yyzsr, double yyzcb, double tzsy, double yylr, double jlr, double mgsgdjlr, double ssgdsy) {
		this.code = code;
		this.releaseDate = releaseDate;
		this.yyzsr = yyzsr;
		this.yyzcb = yyzcb;
		this.tzsy = tzsy;
		this.yylr = yylr;
		this.jlr = jlr;
		this.mgsgdjlr = mgsgdjlr;
		this.ssgdsy = ssgdsy;
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

	public double getYyzsr() {
		return this.yyzsr;
	}

	public void setYyzsr(double yyzsr) {
		this.yyzsr = yyzsr;
	}

	public double getYyzcb() {
		return this.yyzcb;
	}

	public void setYyzcb(double yyzcb) {
		this.yyzcb = yyzcb;
	}

	public double getTzsy() {
		return this.tzsy;
	}

	public void setTzsy(double tzsy) {
		this.tzsy = tzsy;
	}

	public double getYylr() {
		return this.yylr;
	}

	public void setYylr(double yylr) {
		this.yylr = yylr;
	}

	public double getJlr() {
		return this.jlr;
	}

	public void setJlr(double jlr) {
		this.jlr = jlr;
	}

	public double getMgsgdjlr() {
		return this.mgsgdjlr;
	}

	public void setMgsgdjlr(double mgsgdjlr) {
		this.mgsgdjlr = mgsgdjlr;
	}

	public double getSsgdsy() {
		return this.ssgdsy;
	}

	public void setSsgdsy(double ssgdsy) {
		this.ssgdsy = ssgdsy;
	}

}