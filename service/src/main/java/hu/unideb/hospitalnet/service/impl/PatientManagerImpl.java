package hu.unideb.hospitalnet.service.impl;

import hu.unideb.hospitalnet.core.dao.PatientDao;
import hu.unideb.hospitalnet.core.dao.WorkerDao;
import hu.unideb.hospitalnet.core.entity.Patient;
import hu.unideb.hospitalnet.service.PatientManager;
import hu.unideb.hospitalnet.service.converter.impl.PatientConverter;
import hu.unideb.hospitalnet.service.converter.impl.WorkerConverter;
import hu.unideb.hospitalnet.vo.PatientVo;
import hu.unideb.hospitalnet.vo.WorkerVo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("patientManager")
@Transactional(propagation = Propagation.REQUIRED)
public class PatientManagerImpl implements PatientManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private PatientDao patientDao;

	@Autowired
	private WorkerDao workerDao;

	private PatientConverter converter = new PatientConverter();
	private WorkerConverter workerConvert = new WorkerConverter();

	private String username;

	@Override
	public PatientVo savePatient(PatientVo patient) {
		return converter.toVo(patientDao.save(converter.toEntity(patient)));

	}

	@Override
	public List<PatientVo> getAll() {
		return converter.toVo(patientDao.findAll());
	}

	@Override
	public List<PatientVo> getPatients(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Patient> entities = null;

		WorkerVo worker = workerConvert
				.toVo(workerDao.findByUsername(username));
		String status = "";
		if (worker != null) {
			if (worker.getRole().getName().equals("ROLE_DOCTOR")) {
				status = "aktív";
			}
		}

		if (status.equals("aktív")) {

			if (filter.length() != 0 && filterColumnName.equals("name")) {
				entities = patientDao.findByNameStartsWithAndStatusLike(filter,
						pageRequest, status);
			} else if (filter.length() != 0 && filterColumnName.equals("ssn")) {
				entities = patientDao.findBySsnStartsWithAndStatusLike(filter,
						pageRequest, status);
			} else if (filter.length() != 0
					&& filterColumnName.equals("idNumber")) {
				entities = patientDao.findByIdNumberStartsWithAndStatusLike(
						filter, pageRequest, status);
			} else if (filter.length() != 0
					&& filterColumnName.equals("status")) {
				entities = patientDao.findByStatusStartsWithAndStatusLike(
						filter, pageRequest, status);
			} else {
				entities = patientDao.findByStatusLike(pageRequest, status);
			}

		} else {
			if (filter.length() != 0 && filterColumnName.equals("name")) {
				entities = patientDao.findByNameStartsWith(filter, pageRequest);
			} else if (filter.length() != 0 && filterColumnName.equals("ssn")) {
				entities = patientDao.findBySsnStartsWith(filter, pageRequest);
			} else if (filter.length() != 0
					&& filterColumnName.equals("idNumber")) {
				entities = patientDao.findByIdNumberStartsWith(filter,
						pageRequest);
			} else if (filter.length() != 0
					&& filterColumnName.equals("status")) {
				entities = patientDao.findByStatusStartsWith(filter,
						pageRequest);
			} else {
				entities = patientDao.findAll(pageRequest);
			}

		}
		List<PatientVo> ret = converter.toVo(entities.getContent());

		return ret;
	}

	@Override
	public int getRowNumber() {
		return (int) patientDao.count();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}