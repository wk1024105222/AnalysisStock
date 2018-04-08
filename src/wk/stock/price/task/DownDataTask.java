package wk.stock.price.task;

import java.text.SimpleDateFormat;
import java.util.Date;

/***
 * 股价下载模块的任务entity
 * @ClassName: DownDataTask
 * @Description: 
 * @author wkai
 * @date 2018年4月8日 上午10:49:46
 *
 */
public class DownDataTask {

	private String code;

	private Date begindate;

	private String year;

	private String season;

	public DownDataTask(String code, Date begindate) {

		this.code = code;
		this.begindate = begindate;
	}

	public DownDataTask(String code) {

		this.code = code;
	}

	public String getCode() {

		return code;
	}

	public void setCode(String code) {

		this.code = code;
	}

	public Date getBegindate() {

		return begindate;
	}

	public void setBegindate(Date begindate) {

		this.begindate = begindate;
	}

	public String getYear() {

		return year;
	}

	public void setYear(String year) {

		this.year = year;
	}

	public String getSeason() {

		return season;
	}

	public void setSeason(String season) {

		this.season = season;
	}

	@Override
	public String toString() {

		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String today = sdf.format(new Date());
		String tmp = code;
		if(this.begindate != null ) {
			tmp += sdf.format(this.begindate)+today;
		}

		return tmp;
	}

}
