package hu.unideb.hospitalnet.service;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;

public interface WarehouseStatManager extends Serializable {

	WarehouseStatVo getStatById(Long id);

	WarehouseStatVo getStatByDate(Date d);

	void addStat(WarehouseStatVo vo);

	List<WarehouseStatVo> getStatsBetween(Date start, Date end);

}
