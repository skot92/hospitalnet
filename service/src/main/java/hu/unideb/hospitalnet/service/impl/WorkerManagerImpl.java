package hu.unideb.hospitalnet.service.impl;

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

import hu.unideb.hospitalnet.core.dao.WorkerDao;
import hu.unideb.hospitalnet.core.entity.Worker;
import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.service.converter.impl.WorkerConverter;
import hu.unideb.hospitalnet.vo.RoleVo;
import hu.unideb.hospitalnet.vo.WorkerVo;

@Service("workerManager")
@Transactional(propagation = Propagation.REQUIRED)
public class WorkerManagerImpl implements WorkerManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private WorkerDao workerDao;

	WorkerConverter converter = new WorkerConverter();

	@Override
	public void saveWorker(WorkerVo worker) {
		workerDao.save(converter.toEntity(worker));
	}

	@Override
	public List<WorkerVo> getWorkers() {
		return converter.toVo(workerDao.findAll());
	}

	@Override
	public WorkerVo getWorkerByUsername(String username) {
		return converter.toVo(workerDao.findByUsername(username));
	}

	@Override
	public List<WorkerVo> getWorkers(int page, int pageSize, String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize,
				new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Worker> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = workerDao.findByNameContaining(filter, pageRequest);
		} else {
			entities = workerDao.findAll(pageRequest);
		}

		return converter.toVo(entities.getContent());
	}

	@Override
	public int getWorkersCount() {
		return (int) workerDao.count();
	}

	@Override
	public WorkerVo getWorkerById(Long id) {
		return converter.toVo(workerDao.findOne(id));
	}

	@Override
	public List<WorkerVo> getWorkerByName(String query) {
		return converter.toVo(workerDao.findByNameContaining(query));
	}

	@Override
	public List<WorkerVo> getWorkersByWorkingDates(Date start, Date end) {
		return converter.toVo(workerDao.findIfWorkingBetween(start, end));
	}

	@Override
	public int getWorkerCountByRole(RoleVo r) {
		return workerDao.countByRoleId(r.getId());
	}

	@Override
	public List<WorkerVo> getWorkersByRole(RoleVo r) {
		return converter.toVo(workerDao.findByRoleId(r.getId()));
	}

}
