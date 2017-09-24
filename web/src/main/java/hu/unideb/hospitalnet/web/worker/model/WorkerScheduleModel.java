package hu.unideb.hospitalnet.web.worker.model;

import java.util.Date;
import java.util.List;

import org.primefaces.model.LazyScheduleModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.service.TimeTableManager;
import hu.unideb.hospitalnet.vo.TimeTableVo;

@Service("workerScheduleModel")
public class WorkerScheduleModel extends LazyScheduleModel {

	private static final long serialVersionUID = 1L;

	private Long workerId;

	@Autowired
	private TimeTableManager timeTableManager;

	@Override
	public void loadEvents(Date start, Date end) {
		if (workerId != null) {
			List<TimeTableVo> tts = timeTableManager.getTimeTablesBetweenDates(workerId, start, end);

			for (TimeTableVo timeTableVo : tts) {
				addEvent(timeTableVo);
			}
		}
	}

	public void addEvent(TimeTableVo timeTableVo) {
		TimeTableEvent event = new TimeTableEvent();
		event.setTt(timeTableVo);
		addEvent(event);
	}

	public Long getWorkerId() {
		return workerId;
	}

	public void setWorkerId(Long workerId) {
		this.workerId = workerId;
	}

	public TimeTableManager getTimeTableManager() {
		return timeTableManager;
	}

	public void setTimeTableManager(TimeTableManager timeTableManager) {
		this.timeTableManager = timeTableManager;
	}

}
