package hu.unideb.hospitalnet.service;

import hu.unideb.hospitalnet.vo.MedicalRecordVo;

import java.util.List;

public interface MedicalRecordManager {
	public List<MedicalRecordVo> findByPatientId(Long id);
	
	public MedicalRecordVo save(MedicalRecordVo mcr);
}
