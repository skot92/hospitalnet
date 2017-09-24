package hu.unideb.hospitalnet.service;

import hu.unideb.hospitalnet.vo.RoleVo;

public interface RoleManager {

	RoleVo getRoleByName(String roleName);

	String nameOfRole(RoleVo role);
}
