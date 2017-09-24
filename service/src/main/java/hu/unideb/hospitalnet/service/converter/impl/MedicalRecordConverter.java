package hu.unideb.hospitalnet.service.converter.impl;

import hu.unideb.hospitalnet.core.entity.MedicalRecord;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.MedicalRecordVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class MedicalRecordConverter  implements Converter<MedicalRecord, MedicalRecordVo>, Serializable {

	private static final long serialVersionUID = 1L;
	
	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public MedicalRecord toEntity(MedicalRecordVo vo) {
		return vo == null ? null : mapper.map(vo, MedicalRecord.class);
	}

	@Override
	public MedicalRecordVo toVo(MedicalRecord entity) {
		return entity == null ? null : mapper.map(entity, MedicalRecordVo.class);
	}

	@Override
	public List<MedicalRecord> toEntity(List<MedicalRecordVo> vos) {
		List<MedicalRecord> res = new ArrayList<>();
		for (MedicalRecordVo patient : vos) {
			res.add(toEntity(patient));
		}
		return res;
	}

	@Override
	public List<MedicalRecordVo> toVo(List<MedicalRecord> entities) {
		List<MedicalRecordVo> res = new ArrayList<>();
		for (MedicalRecord patient : entities) {
			res.add(toVo(patient));
		}
		return res;
	}
}
