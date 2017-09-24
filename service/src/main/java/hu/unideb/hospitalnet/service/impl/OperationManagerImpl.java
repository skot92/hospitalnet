package hu.unideb.hospitalnet.service.impl;

import hu.unideb.hospitalnet.core.dao.OperationDao;
import hu.unideb.hospitalnet.core.dao.WorkerDao;
import hu.unideb.hospitalnet.core.entity.Operation;
import hu.unideb.hospitalnet.service.OperationManager;
import hu.unideb.hospitalnet.service.converter.impl.OperationConverter;
import hu.unideb.hospitalnet.service.converter.impl.WorkerConverter;
import hu.unideb.hospitalnet.vo.OperationVo;
import hu.unideb.hospitalnet.vo.WorkerVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("operationManager")
@Transactional(propagation = Propagation.REQUIRED)
public class OperationManagerImpl implements OperationManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private OperationDao operationDao;

	@Autowired
	private WorkerDao workerDao;
	

	private OperationConverter converter = new OperationConverter();
	private WorkerConverter workerConvert = new WorkerConverter();

	private String username;

	@Override
	public OperationVo save(OperationVo operation) {
		return converter.toVo(operationDao.save(converter.toEntity(operation)));
	}



	@Override
	public List<OperationVo> getOperations(int page, int size,
			String sortField, int sortOrder, String filter,
			String filterColumnName, Date fromOpDate) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Operation> entities;
		WorkerVo worker = workerConvert
				.toVo(workerDao.findByUsername(username));

		entities = operationDao.findByWorkerAndFromDateGreaterThanEqual(
				workerConvert.toEntity(worker), fromOpDate, pageRequest);

		List<OperationVo> ret = converter.toVo(entities.getContent());

		return ret;
	}

	@Override
	public int getRowNumber() {
		return (int) operationDao.count();
	}
	



	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
	}



}
