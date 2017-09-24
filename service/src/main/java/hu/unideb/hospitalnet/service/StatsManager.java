package hu.unideb.hospitalnet.service;

import java.util.List;

import hu.unideb.hospitalnet.service.stats.vo.DiseaseCountVo;

public interface StatsManager {

	List<DiseaseCountVo> getTop10Diseases();
}
