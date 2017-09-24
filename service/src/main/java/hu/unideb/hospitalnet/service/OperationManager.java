package hu.unideb.hospitalnet.service;

import hu.unideb.hospitalnet.vo.OperationVo;

import java.util.Date;
import java.util.List;

public interface OperationManager {

	public OperationVo save(OperationVo operation);

	public List<OperationVo> getOperations(int i, int pageSize, String sortField,
			int dir, String filter, String filterColumnName, Date fromOpDate);

	public int getRowNumber();

	public String getUsername();


	public void setUsername(String username);
	
	

}
