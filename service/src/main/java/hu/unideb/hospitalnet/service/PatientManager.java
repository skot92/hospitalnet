package hu.unideb.hospitalnet.service;

import java.util.List;

import hu.unideb.hospitalnet.vo.PatientVo;

public interface PatientManager {
	public PatientVo savePatient(PatientVo patient);

	public List<PatientVo> getAll();

	public List<PatientVo> getPatients(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName);

	public int getRowNumber();
	
	public String getUsername();

	public void setUsername(String username);
}
