package hu.unideb.hospitalnet.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.dao.RoleDao;
import hu.unideb.hospitalnet.core.entity.Role;
import hu.unideb.hospitalnet.service.RoleManager;
import hu.unideb.hospitalnet.service.converter.impl.RoleConverter;
import hu.unideb.hospitalnet.vo.RoleVo;

@Service("roleManager")
@Transactional(propagation = Propagation.REQUIRED)
public class RoleManagerImpl implements RoleManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private RoleDao roleDao;

	private RoleConverter converter = new RoleConverter();

	protected void createRole(String roleName) {
		Role role = new Role();
		role.setName(roleName);
		roleDao.save(role);
	}

	@Override
	public RoleVo getRoleByName(String roleName) {
		if (roleDao.findByName(roleName) == null) {
			createRole(roleName);
		}
		return converter.toVo(roleDao.findByName(roleName));
	}

	@Override
	public String nameOfRole(RoleVo role) {
		return role.getName().split("_", 2)[1];
	}

}
