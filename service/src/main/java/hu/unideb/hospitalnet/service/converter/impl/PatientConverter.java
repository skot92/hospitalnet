package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.Patient;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.PatientVo;

public class PatientConverter implements Converter<Patient, PatientVo> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public Patient toEntity(PatientVo vo) {
		return vo == null ? null : mapper.map(vo, Patient.class);
	}

	@Override
	public PatientVo toVo(Patient entity) {
		return entity == null ? null : mapper.map(entity, PatientVo.class);
	}

	@Override
	public List<Patient> toEntity(List<PatientVo> vos) {
		List<Patient> res = new ArrayList<>();
		for (PatientVo patient : vos) {
			res.add(toEntity(patient));
		}
		return res;
	}

	@Override
	public List<PatientVo> toVo(List<Patient> entities) {
		List<PatientVo> res = new ArrayList<>();
		for (Patient patient : entities) {
			res.add(toVo(patient));
		}
		return res;
	}

}
