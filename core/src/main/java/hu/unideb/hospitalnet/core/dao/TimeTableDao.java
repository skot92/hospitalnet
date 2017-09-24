package hu.unideb.hospitalnet.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.TimeTable;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface TimeTableDao extends JpaRepository<TimeTable, Long> {

	@Query("select t from Worker w join w.timeTables t where w.id=(?1) and not t.id=(?2) "
			+ " and ((t.from between ?3 and ?4) or (t.to between ?3 and ?4)"
			+ " or (?3 between t.from and t.to) or (?4 between t.from and t.to) " + " or (t.from=?3) or (t.to=?4))")
	List<TimeTable> findByWorkerTimeTableAndDate(Long workerId, Long thisId, Date dateStart, Date dateEnd);

	@Query("select t from Worker w join w.timeTables t where w.id=(?1) "
			+ " and ((t.from between ?2 and ?3) or (t.to between ?2 and ?3)"
			+ " or (?2 between t.from and t.to) or (?3 between t.from and t.to) " + " or (t.from=?2) or (t.to=?3))")
	List<TimeTable> findByWorkerAndDate(Long workerId, Date dateStart, Date dateEnd);

	@Modifying
	@Query(value = "INSERT INTO worker_times (worker_id, timetable_id) VALUES (?1,?2)", nativeQuery = true)
	void addTimeTableToWorker(Long workerId, Long id);

}
