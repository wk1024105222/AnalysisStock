package crawler.cases.stock.vo;

import java.util.Date;

/**
 * Stockbonus entity. @author MyEclipse Persistence Tools
 */

public class StockBonus implements java.io.Serializable {

	// Fields

	private String id;

	private String code;

	private double bonus;

	private double zhuanzeng;

	private double dividend;

	private Date exDate;

	private Date rdDate;

	private Date deDate;

	// Constructors

	/** default constructor */
	public StockBonus() {

	}

	/** full constructor */
	public StockBonus(String code, double bonus, double zhuanzeng, double dividend, Date exDate, Date rdDate,
			Date deDate) {

		this.code = code;
		this.bonus = bonus;
		this.zhuanzeng = zhuanzeng;
		this.dividend = dividend;
		this.exDate = exDate;
		this.rdDate = rdDate;
		this.deDate = deDate;
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

	public double getBonus() {

		return this.bonus;
	}

	public void setBonus(double bonus) {

		this.bonus = bonus;
	}

	public double getZhuanzeng() {

		return this.zhuanzeng;
	}

	public void setZhuanzeng(double zhuanzeng) {

		this.zhuanzeng = zhuanzeng;
	}

	public double getDividend() {

		return this.dividend;
	}

	public void setDividend(double dividend) {

		this.dividend = dividend;
	}

	public Date getExDate() {

		return this.exDate;
	}

	public void setExDate(Date exDate) {

		this.exDate = exDate;
	}

	public Date getRdDate() {

		return this.rdDate;
	}

	public void setRdDate(Date rdDate) {

		this.rdDate = rdDate;
	}

	public Date getDeDate() {

		return this.deDate;
	}

	public void setDeDate(Date deDate) {

		this.deDate = deDate;
	}

}
