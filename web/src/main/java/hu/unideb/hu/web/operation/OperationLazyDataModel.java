package hu.unideb.hu.web.operation;

import hu.unideb.hospitalnet.service.OperationManager;
import hu.unideb.hospitalnet.vo.OperationVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.faces.bean.ManagedProperty;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

public class OperationLazyDataModel extends LazyDataModel<OperationVo>
		implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<OperationVo> datasource;
	@ManagedProperty("#{operationmanager}")
	private OperationManager service;
	
	private Date fromOpDate;

	public OperationLazyDataModel(OperationManager service, Date fromOpDate) {
		this.service = service;
		this.fromOpDate = fromOpDate;
	}

	@Override
	public OperationVo getRowData(String rowKey) {
		for (OperationVo patient : datasource) {
			if (patient.getId().toString().equals(rowKey))
				return patient;
		}

		return null;
	}

	@Override
	public Object getRowKey(OperationVo patient) {
		return patient.getId();
	}

	@Override
	public List<OperationVo> load(int first, int pageSize, String sortField,
			SortOrder sortOrder, Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "fromDate";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;
		
		datasource = service.getOperations(first / pageSize, pageSize,
				sortField, dir, filter, filterColumnName, fromOpDate);

		int dataSize = service.getRowNumber();

		this.setRowCount(dataSize);

		return datasource;
	}
	
	

	public Date getFromOpDate() {
		return fromOpDate;
	}

	public void setFromOpDate(Date fromOpDate) {
		this.fromOpDate = fromOpDate;
	}

	public List<OperationVo> getDatasource() {
		return datasource;
	}

	public void setDatasource(List<OperationVo> datasource) {
		this.datasource = datasource;
	}

	public OperationManager getService() {
		return service;
	}

	public void setService(OperationManager service) {
		this.service = service;
	}

}
