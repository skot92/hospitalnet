package hu.unideb.hospitalnet.service.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import hu.unideb.hospitalnet.core.dao.BnoDao;
import hu.unideb.hospitalnet.service.StatsManager;
import hu.unideb.hospitalnet.service.stats.vo.DiseaseCountVo;

@Service("statsManager")
@Transactional(propagation = Propagation.REQUIRED)
public class StatsManagerImpl implements StatsManager {

	@Autowired
	private BnoDao bnoDao;

	@Override
	public List<DiseaseCountVo> getTop10Diseases() {
		List<Object[]> top10 = bnoDao.findTop10Diseases();
		List<DiseaseCountVo> res = new ArrayList<>(10);
		for (Object[] obj : top10) {
			DiseaseCountVo vo = new DiseaseCountVo();

			String bnoName = bnoDao.findOne(((BigInteger) obj[0]).longValue()).getName();

			vo.setDiseaseName(bnoName);
			vo.setCount(((BigInteger) obj[1]).intValue());

			res.add(vo);
		}

		return res;
	}

}
