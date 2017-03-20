package wk.stock.vo;

import java.util.Date;

/**
 * Balance entity. @author MyEclipse Persistence Tools
 */

public class Balance implements java.io.Serializable {

	// Fields

	private String id;
	private String code;
	private Date releaseDate;
	private double ldzchj;
	private double fldzchj;
	private double zchj;
	private double ldfzhj;
	private double fldfzhj;
	private double fzhj;
	private double syzqy;
	private double mgsgdqy;
	private double ssgdqy;

	// Constructors

	/** default constructor */
	public Balance() {
	}

	/** minimal constructor */
	public Balance(String code) {
		this.code = code;
	}

	/** full constructor */
	public Balance(String code, Date releaseDate, double ldzchj, double fldzchj, double zchj, double ldfzhj, double fldfzhj, double fzhj, double syzqy, double mgsgdqy, double ssgdqy) {
		this.code = code;
		this.releaseDate = releaseDate;
		this.ldzchj = ldzchj;
		this.fldzchj = fldzchj;
		this.zchj = zchj;
		this.ldfzhj = ldfzhj;
		this.fldfzhj = fldfzhj;
		this.fzhj = fzhj;
		this.syzqy = syzqy;
		this.mgsgdqy = mgsgdqy;
		this.ssgdqy = ssgdqy;
	}
	
	public Balance(String code, Date releaseDate,  double zchj,  double fzhj, double syzqy, double mgsgdqy, double ssgdqy) {
		this.code = code;
		this.releaseDate = releaseDate;
		this.zchj = zchj;
		this.fzhj = fzhj;
		this.syzqy = syzqy;
		this.mgsgdqy = mgsgdqy;
		this.ssgdqy = ssgdqy;
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

	public double getLdzchj() {
		return this.ldzchj;
	}

	public void setLdzchj(double ldzchj) {
		this.ldzchj = ldzchj;
	}

	public double getFldzchj() {
		return this.fldzchj;
	}

	public void setFldzchj(double fldzchj) {
		this.fldzchj = fldzchj;
	}

	public double getZchj() {
		return this.zchj;
	}

	public void setZchj(double zchj) {
		this.zchj = zchj;
	}

	public double getLdfzhj() {
		return this.ldfzhj;
	}

	public void setLdfzhj(double ldfzhj) {
		this.ldfzhj = ldfzhj;
	}

	public double getFldfzhj() {
		return this.fldfzhj;
	}

	public void setFldfzhj(double fldfzhj) {
		this.fldfzhj = fldfzhj;
	}

	public double getFzhj() {
		return this.fzhj;
	}

	public void setFzhj(double fzhj) {
		this.fzhj = fzhj;
	}

	public double getSyzqy() {
		return this.syzqy;
	}

	public void setSyzqy(double syzqy) {
		this.syzqy = syzqy;
	}

	public double getMgsgdqy() {
		return this.mgsgdqy;
	}

	public void setMgsgdqy(double mgsgdqy) {
		this.mgsgdqy = mgsgdqy;
	}

	public double getSsgdqy() {
		return this.ssgdqy;
	}

	public void setSsgdqy(double ssgdqy) {
		this.ssgdqy = ssgdqy;
	}

}