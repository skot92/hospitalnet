package hu.unideb.hospitalnet.web.patient;

import hu.unideb.hospitalnet.service.PatientManager;
import hu.unideb.hospitalnet.vo.PatientVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.security.core.context.SecurityContextHolder;

public class LazyPatientDataModel extends LazyDataModel<PatientVo> implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<PatientVo> datasource;
	@ManagedProperty("#{patientManager}")
	private PatientManager service;

	public LazyPatientDataModel(PatientManager service) {
		this.service = service;
		service.setUsername(SecurityContextHolder.getContext().getAuthentication().getName());
		// this.datasource = service.getAll();
	}

	@Override
	public PatientVo getRowData(String rowKey) {
		for (PatientVo patient : datasource) {
			if (patient.getId().toString().equals(rowKey))
				return patient;
		}

		return null;
	}

	@Override
	public Object getRowKey(PatientVo patient) {
		return patient.getId();
	}

	@Override
	public List<PatientVo> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;

		datasource = service.getPatients(first / pageSize, pageSize, sortField,
				dir, filter, filterColumnName);

		int dataSize = service.getRowNumber();

		this.setRowCount(dataSize);

		return datasource;
	}

}
