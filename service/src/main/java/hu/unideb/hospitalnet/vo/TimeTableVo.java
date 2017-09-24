package hu.unideb.hospitalnet.vo;

import java.io.Serializable;
import java.util.Date;

public class TimeTableVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date from;

	private Date to;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

}
