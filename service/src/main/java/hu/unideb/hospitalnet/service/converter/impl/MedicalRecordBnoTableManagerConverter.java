package hu.unideb.hospitalnet.service.converter.impl;

import hu.unideb.hospitalnet.core.entity.MedicalRecordBnoTable;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.MedicalRecordBnoTableVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MedicalRecordBnoTableManagerConverter implements
		Converter<MedicalRecordBnoTable, MedicalRecordBnoTableVo>,Serializable {
	
	private static final long serialVersionUID = 1L;
	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public MedicalRecordBnoTable toEntity(MedicalRecordBnoTableVo vo) {
		return vo == null ? null : mapper.map(vo, MedicalRecordBnoTable.class);
	}

	@Override
	public MedicalRecordBnoTableVo toVo(MedicalRecordBnoTable entity) {
		return entity == null ? null : mapper.map(entity, MedicalRecordBnoTableVo.class);
	}

	@Override
	public List<MedicalRecordBnoTable> toEntity(List<MedicalRecordBnoTableVo> vos) {
		List<MedicalRecordBnoTable> res = new ArrayList<>();
		for (MedicalRecordBnoTableVo patient : vos) {
			res.add(toEntity(patient));
		}
		return res;
	}

	@Override
	public List<MedicalRecordBnoTableVo> toVo(List<MedicalRecordBnoTable> entities) {
		List<MedicalRecordBnoTableVo> res = new ArrayList<>();
		for (MedicalRecordBnoTable patient : entities) {
			res.add(toVo(patient));
		}
		return res;
	}

}
