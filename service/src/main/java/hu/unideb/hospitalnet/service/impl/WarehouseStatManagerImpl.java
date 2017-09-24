package hu.unideb.hospitalnet.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.core.dao.WarehouseStatDao;
import hu.unideb.hospitalnet.core.entity.WarehouseStat;
import hu.unideb.hospitalnet.service.WarehouseStatManager;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.service.converter.impl.WarehouseStatConverter;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;

@Service("warehouseStatManager")
public class WarehouseStatManagerImpl implements WarehouseStatManager {

	private static final long serialVersionUID = 1L;

	@Autowired
	private WarehouseStatDao warehouseStatDao;

	private Converter<WarehouseStat, WarehouseStatVo> converter = new WarehouseStatConverter();

	@Override
	public WarehouseStatVo getStatById(Long id) {
		return converter.toVo(warehouseStatDao.findOne(id));
	}

	@Override
	public WarehouseStatVo getStatByDate(Date d) {
		return converter.toVo(warehouseStatDao.findByDayOfOccurence(d));
	}

	@Override
	public void addStat(WarehouseStatVo vo) {
		WarehouseStatVo stat = getStatByDate(vo.getDayOfOccurence());
		if (stat == null) {
			warehouseStatDao.save(converter.toEntity(vo));
		} else {
			stat.setOrdered(stat.getOrdered() + vo.getOrdered());
			stat.setShippedAway(stat.getShippedAway() + vo.getShippedAway());
			stat.setGivenToPatients(stat.getGivenToPatients() + vo.getGivenToPatients());
			warehouseStatDao.save(converter.toEntity(stat));
		}
	}

	@Override
	public List<WarehouseStatVo> getStatsBetween(Date start, Date end) {
		return converter.toVo(warehouseStatDao.findByDayOfOccurenceBetweenOrderByDayOfOccurence(start, end));
	}

}
