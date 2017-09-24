package hu.unideb.hospitalnet.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.Worker;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface WorkerDao extends JpaRepository<Worker, Long> {

	Worker findByUsername(String username);

	Page<Worker> findByNameContaining(String name, Pageable pageable);

	List<Worker> findByNameContaining(String query);

	@Query("SELECT w FROM Worker w JOIN w.timeTables t WHERE "
			+ "(t.from BETWEEN ?1 AND ?2) OR (t.to BETWEEN ?1 AND ?2)")
	List<Worker> findIfWorkingBetween(Date start, Date end);

	int countByRoleId(Long roleId);

	@Query("SELECT w FROM Worker w JOIN w.role r WHERE r.id=(?1)")
	List<Worker> findByRoleId(Long roleId);
}
