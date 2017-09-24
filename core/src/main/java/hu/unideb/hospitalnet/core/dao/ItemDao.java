package hu.unideb.hospitalnet.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.Item;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ItemDao extends JpaRepository<Item, Long> {
	@Modifying
	@Query(value = "UPDATE items SET status=?2 WHERE id=?1", nativeQuery = true)
	void updateItemStatus(Long id, String status);
}
