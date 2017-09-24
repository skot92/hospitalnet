package hu.unideb.hospitalnet.web.worker.model;

import java.io.Serializable;
import java.util.Date;

import org.primefaces.model.DefaultScheduleEvent;

import hu.unideb.hospitalnet.vo.TimeTableVo;

public class TimeTableEvent extends DefaultScheduleEvent implements Serializable {

	private static final long serialVersionUID = 1L;

	private TimeTableVo tt;

	@Override
	public Object getData() {
		return tt;
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public Date getEndDate() {
		return tt.getTo();
	}

	@Override
	public String getId() {
		return (tt.getId() == null) ? null : String.valueOf(tt.getId());
	}

	@Override
	public Date getStartDate() {
		return tt.getFrom();
	}

	@Override
	public String getTitle() {
		return "";
	}

	@Override
	public boolean isAllDay() {
		return false;
	}

	@Override
	public boolean isEditable() {
		return true;
	}

	@Override
	public void setId(String arg0) {
	}

	@Override
	public void setEndDate(Date endDate) {
		tt.setTo(endDate);
	}

	@Override
	public void setStartDate(Date startDate) {
		tt.setFrom(startDate);
	}

	public TimeTableVo getTt() {
		return tt;
	}

	public void setTt(TimeTableVo tt) {
		this.tt = tt;
	}

}
