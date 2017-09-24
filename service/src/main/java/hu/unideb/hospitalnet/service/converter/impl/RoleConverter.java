package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.Role;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.RoleVo;

public class RoleConverter implements Converter<Role, RoleVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public Role toEntity(RoleVo vo) {
		return vo == null ? null : mapper.map(vo, Role.class);
	}

	@Override
	public RoleVo toVo(Role entity) {
		return entity == null ? null : mapper.map(entity, RoleVo.class);
	}

	@Override
	public List<Role> toEntity(List<RoleVo> vos) {
		List<Role> res = new ArrayList<>();
		for (RoleVo Role : vos) {
			res.add(toEntity(Role));
		}
		return res;
	}

	@Override
	public List<RoleVo> toVo(List<Role> entities) {
		List<RoleVo> res = new ArrayList<>();
		for (Role Role : entities) {
			res.add(toVo(Role));
		}
		return res;
	}

}
