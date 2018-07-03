package crawler.cases.fund.vo;

import java.util.Date;

/**
 * ManageHistory entity. @author MyEclipse Persistence Tools
 */

public class ManageHistory implements java.io.Serializable {

	// Fields

	private String id;

	private String code;

	private Date begin;

	private Date end;

	private String names;

	private long days;

	private double yields;

	// Constructors

	/** default constructor */
	public ManageHistory() {

	}

	/** full constructor */
	public ManageHistory(String code, Date begin, Date end, String names, long days, double yields) {

		super();
		this.code = code;
		this.begin = begin;
		this.end = end;
		this.names = names;
		this.days = days;
		this.yields = yields;
	}

	// Property accessors

	public String getId() {

		return this.id;
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

	public Date getBegin() {

		return this.begin;
	}

	public void setBegin(Date begin) {

		this.begin = begin;
	}

	public Date getEnd() {

		return this.end;
	}

	public void setEnd(Date end) {

		this.end = end;
	}

	public String getNames() {

		return this.names;
	}

	public void setNames(String names) {

		this.names = names;
	}

	public long getDays() {

		return this.days;
	}

	public void setDays(long days) {

		this.days = days;
	}

	public double getYields() {

		return this.yields;
	}

	public void setYields(double yields) {

		this.yields = yields;
	}

}
