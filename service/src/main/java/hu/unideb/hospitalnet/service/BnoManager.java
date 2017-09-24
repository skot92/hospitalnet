package hu.unideb.hospitalnet.service;

import hu.unideb.hospitalnet.vo.BnoVo;

import java.util.List;

public interface BnoManager {
	
	public List<BnoVo> getAll();
	
	public List<String> getAllName();
	
	public List<String> getAllcode();

	public List<BnoVo> getBnos(int i, int pageSize, String sortField, int dir,
			String filter, String filterColumnName);

	public int getRowNumber();

}
