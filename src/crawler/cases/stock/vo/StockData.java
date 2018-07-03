package crawler.cases.stock.vo;

import java.io.Serializable;
import java.util.Date;

import crawler.util.ReflectUtil;

public class StockData implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String code;
	private Date txnDate;
	private Double open;
	private Double high;
	private Double low;
	private Double close;
	private Long volume;
	private Double adjClose;
	private Date listedDate;
	private Double ema12;
	private Double ema26;
	private Double diff;
	private Double dea;
	private Double bar;
	private Double gain;

	public StockData() {
		super();
	}

	public StockData(String code, Date txnDate, Double open, Double high, Double low, Double close, Long volume, Double adjClose) {
		super();
		this.code = code;
		this.txnDate = txnDate;
		this.open = open;
		this.high = high;
		this.low = low;
		this.close = close;
		this.volume = volume;
		this.adjClose = adjClose;
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

	public Double getOpen() {
		return open;
	}

	public void setOpen(Double open) {
		this.open = open;
	}

	public Double getHigh() {
		return high;
	}

	public void setHigh(Double high) {
		this.high = high;
	}

	public Double getLow() {
		return low;
	}

	public void setLow(Double low) {
		this.low = low;
	}

	public Double getClose() {
		return close;
	}

	public void setClose(Double close) {
		this.close = close;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public Double getAdjClose() {
		return adjClose;
	}

	public void setAdjClose(Double adjClose) {
		this.adjClose = adjClose;
	}

	public Date getListedDate() {
		return listedDate;
	}

	public void setListedDate(Date listedDate) {
		this.listedDate = listedDate;
	}

	public Double getEma12() {
		return ema12;
	}

	public void setEma12(Double ema12) {
		this.ema12 = ema12;
	}

	public Double getEma26() {
		return ema26;
	}

	public void setEma26(Double ema26) {
		this.ema26 = ema26;
	}

	public Double getDiff() {
		return diff;
	}

	public void setDiff(Double diff) {
		this.diff = diff;
	}

	public Double getDea() {
		return dea;
	}

	public void setDea(Double dea) {
		this.dea = dea;
	}

	public Double getBar() {
		return bar;
	}

	public void setBar(Double bar) {
		this.bar = bar;
	}

	public Double getGain() {
		return gain;
	}

	public void setGain(Double gain) {
		this.gain = gain;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ReflectUtil.toString(this);
	}

}
