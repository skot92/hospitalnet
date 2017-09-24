package hu.unideb.hospitalnet.web.patient;

import hu.unideb.hospitalnet.service.BnoManager;
import hu.unideb.hospitalnet.vo.BnoVo;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;


public class BnoLazyDataModel extends LazyDataModel<BnoVo> implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<BnoVo> datasource;
	@ManagedProperty("#{bnoManager}")
	private BnoManager service;

	public BnoLazyDataModel(BnoManager service) {
		this.service = service;
		// this.datasource = service.getAll();
	}

	@Override
	public BnoVo getRowData(String rowKey) {
		for (BnoVo patient : datasource) {
			if (patient.getId().toString().equals(rowKey))
				return patient;
		}

		return null;
	}

	@Override
	public Object getRowKey(BnoVo patient) {
		return patient.getId();
	}

	@Override
	public List<BnoVo> load(int first, int pageSize, String sortField,
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

		datasource = service.getBnos(first / pageSize, pageSize, sortField,
				dir, filter, filterColumnName);

		int dataSize = service.getRowNumber();

		this.setRowCount(dataSize);

		return datasource;
	}
}
