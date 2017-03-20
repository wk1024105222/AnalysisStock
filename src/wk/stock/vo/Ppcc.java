package wk.stock.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Ppcc entity. @author MyEclipse Persistence Tools
 */

public class Ppcc implements java.io.Serializable {

	// Fields

	private String id;

	private String codeA;

	private String codeB;

	private Date beginDate;

	private Date endDate;

	private Double close;

	private Double gain;

	private Double gainValue;

	private Integer num;

	// Constructors

	/** default constructor */
	public Ppcc() {

	}

	public Ppcc(String codeA, String codeB, Date beginDate, Date endDate) {

		super();
		this.codeA = codeA;
		this.codeB = codeB;
		this.beginDate = beginDate;
		this.endDate = endDate;
	}

	public String getId() {

		return id;
	}

	public void setId(String id) {

		this.id = id;
	}

	public String getCodeA() {

		return codeA;
	}

	public void setCodeA(String codeA) {

		this.codeA = codeA;
	}

	public String getCodeB() {

		return codeB;
	}

	public void setCodeB(String codeB) {

		this.codeB = codeB;
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

	public Double getClose() {

		return close;
	}

	public void setClose(Double close) {

		this.close = close;
	}

	public Double getGain() {

		return gain;
	}

	public void setGain(Double gain) {

		this.gain = gain;
	}

	public Double getGainValue() {

		return gainValue;
	}

	public void setGainValue(Double gainValue) {

		this.gainValue = gainValue;
	}

	public Integer getNum() {

		return num;
	}

	public void setNum(Integer num) {

		this.num = num;
	}

	@Override
	public String toString() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		// TODO Auto-generated method stub
		return this.codeA + ";" + this.codeB + ";" + sdf.format(this.beginDate) + ";" + sdf.format(this.endDate) + ";"
				+ this.close + ";" + this.gain + ";" + this.gainValue + ";" + this.num + ";";
	}

}
