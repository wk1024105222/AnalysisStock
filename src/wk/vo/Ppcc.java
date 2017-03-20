package wk.vo;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * Ppcc entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "PPCC", schema = "WKAI")
public class Ppcc implements java.io.Serializable {

	// Fields

	private String id;
	private String codea;
	private String codeb;
	private Date begindate;
	private Date enddate;
	private double ppcc;
	private Integer num;
	private String type;

	// Constructors

	/** default constructor */
	public Ppcc() {
	}

	/** minimal constructor */
	public Ppcc(String codea, String codeb, Date begindate, Date enddate) {
		this.codea = codea;
		this.codeb = codeb;
		this.begindate = begindate;
		this.enddate = enddate;
	}

	/** full constructor */
	public Ppcc(String codea, String codeb, Date begindate, Date enddate, double ppcc, Integer num, String type) {
		this.codea = codea;
		this.codeb = codeb;
		this.begindate = begindate;
		this.enddate = enddate;
		this.ppcc = ppcc;
		this.num = num;
		this.type = type;
	}

	// Property accessors
	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "ID", unique = true, nullable = false, length = 32)
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Column(name = "CODEA", nullable = false, length = 6)
	public String getCodea() {
		return this.codea;
	}

	public void setCodea(String codea) {
		this.codea = codea;
	}

	@Column(name = "CODEB", nullable = false, length = 6)
	public String getCodeb() {
		return this.codeb;
	}

	public void setCodeb(String codeb) {
		this.codeb = codeb;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "BEGINDATE", nullable = false, length = 7)
	public Date getBegindate() {
		return this.begindate;
	}

	public void setBegindate(Date begindate) {
		this.begindate = begindate;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "ENDDATE", nullable = false, length = 7)
	public Date getEnddate() {
		return this.enddate;
	}

	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	@Column(name = "PPCC", precision = 20, scale = 18)
	public double getPpcc() {
		return this.ppcc;
	}

	public void setPpcc(double ppcc) {
		this.ppcc = ppcc;
	}

	@Column(name = "NUM", precision = 8, scale = 0)
	public Integer getNum() {
		return this.num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	@Column(name = "TYPE", length = 1)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

}