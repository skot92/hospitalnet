package hu.unideb.hospitalnet.core.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import hu.unideb.hospitalnet.core.entity.Bno;

public interface BnoDao extends JpaRepository<Bno, Long> {

	@Query("SELECT name FROM Bno")
	public List<String> findBnoName();

	public Page<Bno> findByNameContaining(String filter, Pageable pageable);

	public Page<Bno> findByKod10Containing(String filter, Pageable pageable);

	public Bno findByKod10(String kod10);

	@Query(nativeQuery = true, value = "select b.id, count(b.id) as darab FROM mcr_bno t JOIN bno b on t.bno_id = b.id group by bno_id order by darab desc limit 10")
	public List<Object[]> findTop10Diseases();
}
