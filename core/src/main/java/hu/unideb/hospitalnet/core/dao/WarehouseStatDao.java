package hu.unideb.hospitalnet.core.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.WarehouseStat;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface WarehouseStatDao extends JpaRepository<WarehouseStat, Long> {

	WarehouseStat findByDayOfOccurence(Date d);

	List<WarehouseStat> findByDayOfOccurenceBetweenOrderByDayOfOccurence(Date start, Date end);

}
