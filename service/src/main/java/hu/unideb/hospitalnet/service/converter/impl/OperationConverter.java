package hu.unideb.hospitalnet.service.converter.impl;

import hu.unideb.hospitalnet.core.entity.Operation;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.OperationVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class OperationConverter implements Converter<Operation, OperationVo>, Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Mapper mapper = new DozerBeanMapper();
	
	@Override
	public Operation toEntity(OperationVo vo) {
		return vo == null ? null : mapper.map(vo, Operation.class);
	}

	@Override
	public OperationVo toVo(Operation entity) {
		return entity == null ? null : mapper.map(entity, OperationVo.class);
	}

	@Override
	public List<Operation> toEntity(List<OperationVo> vos) {
		List<Operation> res = new ArrayList<>();
		for (OperationVo patient : vos) {
			res.add(toEntity(patient));
		}
		return res;
	}

	@Override
	public List<OperationVo> toVo(List<Operation> entities) {
		List<OperationVo> res = new ArrayList<>();
		for (Operation patient : entities) {
			res.add(toVo(patient));
		}
		return res;
	}



}
