package hu.unideb.hospitalnet.core.dao;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.Product;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface ProductDao extends JpaRepository<Product, Long> {

	Product findByName(String name);
	
	List<Product> findByNameContaining(String query);
	
	Page<Product> findByNameContaining(String name, Pageable pageable);
	
	Page<Product> findByProducerContaining(String name, Pageable pageable);
	
	Page<Product> findByTypeContaining(String name, Pageable pageable);
	
	Page<Product> findByUnitTypeContaining(String name, Pageable pageable);
	
	@Query(value = "SELECT product_name FROM products p JOIN"
			+ " product_items pi on p.id = pi.product_id JOIN"
			+ " items i ON i.id = pi.item_id WHERE i.id = ?" , nativeQuery = true)
	String findById(Long id);
}
