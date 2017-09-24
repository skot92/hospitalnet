package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.TimeTable;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.TimeTableVo;

public class TimeTableConverter implements Converter<TimeTable, TimeTableVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public TimeTable toEntity(TimeTableVo vo) {
		return vo == null ? null : mapper.map(vo, TimeTable.class);
	}

	@Override
	public TimeTableVo toVo(TimeTable entity) {
		return entity == null ? null : mapper.map(entity, TimeTableVo.class);
	}

	@Override
	public List<TimeTable> toEntity(List<TimeTableVo> vos) {
		List<TimeTable> res = new ArrayList<>();
		for (TimeTableVo TimeTable : vos) {
			res.add(toEntity(TimeTable));
		}
		return res;
	}

	@Override
	public List<TimeTableVo> toVo(List<TimeTable> entities) {
		List<TimeTableVo> res = new ArrayList<>();
		for (TimeTable TimeTable : entities) {
			res.add(toVo(TimeTable));
		}
		return res;
	}

}
