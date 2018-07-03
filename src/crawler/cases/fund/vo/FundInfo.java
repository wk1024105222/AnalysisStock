package crawler.cases.fund.vo;

import java.util.Date;

/**
 * Fundinfo entity. @author MyEclipse Persistence Tools
 */

public class FundInfo implements java.io.Serializable {

	// Fields

	private String code;

	private Date listedDate;

	private String name;

	// Constructors

	/** default constructor */
	public FundInfo() {

	}

	/** full constructor */
	public FundInfo(Date listedDate, String name) {

		this.listedDate = listedDate;
		this.name = name;
	}

	// Property accessors
	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public Date getListedDate() {

		return listedDate;
	}

	public void setListedDate(Date listedDate) {

		this.listedDate = listedDate;
	}

	public String getName() {

		return name;
	}

	public void setName(String name) {

		this.name = name;
	}

	

}
