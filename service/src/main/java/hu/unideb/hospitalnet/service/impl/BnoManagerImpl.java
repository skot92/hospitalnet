package hu.unideb.hospitalnet.service.impl;

import hu.unideb.hospitalnet.core.dao.BnoDao;
import hu.unideb.hospitalnet.core.entity.Bno;
import hu.unideb.hospitalnet.service.BnoManager;
import hu.unideb.hospitalnet.service.converter.impl.BnoConverter;
import hu.unideb.hospitalnet.vo.BnoVo;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("bnoManager")
@Transactional(propagation = Propagation.REQUIRED)
public class BnoManagerImpl implements BnoManager, Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private BnoDao bnoDao;
	
	private BnoConverter converter = new BnoConverter();
	
	@Override
	public List<BnoVo> getAll() {

		return converter.toVo(bnoDao.findAll());
	}

	@Override
	public List<String> getAllName() {
		List<String > asd =  bnoDao.findBnoName();
		asd.size();
		return asd;
	}

	@Override
	public List<String> getAllcode() {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public List<BnoVo> getBnos(int page, int size, String sortField,
			int sortOrder, String filter, String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC
				: Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, size, new Sort(
				new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Bno> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = bnoDao.findByNameContaining(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("kod10")) {
			entities = bnoDao.findByKod10Containing(filter, pageRequest);
		} else {
			entities = bnoDao.findAll(pageRequest);
		}
		List<BnoVo> ret = converter.toVo(entities.getContent());

		return ret;
	}

	@Override
	public int getRowNumber() {
		return (int) bnoDao.count();
	}
}
