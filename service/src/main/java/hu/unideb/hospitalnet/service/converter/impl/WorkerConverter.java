package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.Worker;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.WorkerVo;

public class WorkerConverter implements Converter<Worker, WorkerVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public Worker toEntity(WorkerVo vo) {
		return vo == null ? null : mapper.map(vo, Worker.class);
	}

	@Override
	public WorkerVo toVo(Worker entity) {
		return entity == null ? null : mapper.map(entity, WorkerVo.class);
	}

	@Override
	public List<Worker> toEntity(List<WorkerVo> vos) {
		List<Worker> res = new ArrayList<>();
		for (WorkerVo worker : vos) {
			res.add(toEntity(worker));
		}
		return res;
	}

	@Override
	public List<WorkerVo> toVo(List<Worker> entities) {
		List<WorkerVo> res = new ArrayList<>();
		for (Worker worker : entities) {
			res.add(toVo(worker));
		}
		return res;
	}

}
