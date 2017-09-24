package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.WarehouseStat;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;

public class WarehouseStatConverter implements Converter<WarehouseStat, WarehouseStatVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public WarehouseStat toEntity(WarehouseStatVo vo) {
		return vo == null ? null : mapper.map(vo, WarehouseStat.class);
	}

	@Override
	public WarehouseStatVo toVo(WarehouseStat entity) {
		return entity == null ? null : mapper.map(entity, WarehouseStatVo.class);
	}

	@Override
	public List<WarehouseStat> toEntity(List<WarehouseStatVo> vos) {
		List<WarehouseStat> res = new ArrayList<>();
		for (WarehouseStatVo worker : vos) {
			res.add(toEntity(worker));
		}
		return res;
	}

	@Override
	public List<WarehouseStatVo> toVo(List<WarehouseStat> entities) {
		List<WarehouseStatVo> res = new ArrayList<>();
		for (WarehouseStat worker : entities) {
			res.add(toVo(worker));
		}
		return res;
	}

}
