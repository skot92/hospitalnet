package hu.unideb.hospitalnet.service;

import java.util.Date;
import java.util.List;

import hu.unideb.hospitalnet.vo.TimeTableVo;

public interface TimeTableManager {

	Boolean hasCoveringTimeTable(Long workerId, TimeTableVo tt);

	Long addTimeTableToWorker(Long workerId, TimeTableVo newTimeTable);

	List<TimeTableVo> getTimeTablesBetweenDates(Long workerId, Date startDate, Date endDate);

	void saveTimeTable(TimeTableVo tt);

}
