package hu.unideb.hospitalnet.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.dao.TimeTableDao;
import hu.unideb.hospitalnet.core.entity.TimeTable;
import hu.unideb.hospitalnet.service.TimeTableManager;
import hu.unideb.hospitalnet.service.converter.impl.TimeTableConverter;
import hu.unideb.hospitalnet.vo.TimeTableVo;

@Service("timeTableManager")
@Transactional(propagation = Propagation.REQUIRED)
public class TimeTableManagerImpl implements TimeTableManager {

	@Autowired
	private TimeTableDao timeTableDao;

	private TimeTableConverter converter = new TimeTableConverter();

	@Override
	public Boolean hasCoveringTimeTable(Long workerId, TimeTableVo tt) {
		List<TimeTable> tte;
		if (tt.getId() != null) {
			tte = timeTableDao.findByWorkerTimeTableAndDate(workerId, tt.getId(), tt.getFrom(), tt.getTo());
		} else {
			tte = timeTableDao.findByWorkerAndDate(workerId, tt.getFrom(), tt.getTo());
		}
		return !tte.isEmpty();
	}

	@Override
	public List<TimeTableVo> getTimeTablesBetweenDates(Long workerId, Date startDate, Date endDate) {
		List<TimeTable> entities = timeTableDao.findByWorkerAndDate(workerId, startDate, endDate);
		return converter.toVo(entities);
	}

	@Override
	public Long addTimeTableToWorker(Long workerId, TimeTableVo newTimeTable) {

		TimeTable tt = converter.toEntity(newTimeTable);
		tt = timeTableDao.save(tt);

		try {
			timeTableDao.addTimeTableToWorker(workerId, tt.getId());
		} catch (Exception e) {
			return null;
		}

		return tt.getId();
	}

	@Override
	public void saveTimeTable(TimeTableVo tt) {
		timeTableDao.save(converter.toEntity(tt));
	}

}
