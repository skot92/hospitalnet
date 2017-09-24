package hu.unideb.hospitalnet.core.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.entity.Role;

@Repository
@Transactional(propagation = Propagation.SUPPORTS)
public interface RoleDao extends JpaRepository<Role, Long> {

	Role findByName(String roleName);

}
