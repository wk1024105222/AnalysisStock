package crawler.stock.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Stockinfo entity. @author MyEclipse Persistence Tools
 */

public class StockInfo implements java.io.Serializable {

	// Fields

	private String code;

	private Date listedDate;

	private String name;

	// Constructors

	/** default constructor */
	public StockInfo() {

	}

	public StockInfo(String code) {

		super();
		this.code = code;
	}

	/** full constructor */
	public StockInfo(Date listedDate) {

		this.listedDate = listedDate;
	}

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

	@Override
	public String toString() {

		String tmp = code;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(name != null && !name.equals("")) {
			tmp += name;
		}

		if(listedDate != null) {
			tmp += sdf.format(this.listedDate);
		}

		return tmp;
	}

	// Property accessors

}
