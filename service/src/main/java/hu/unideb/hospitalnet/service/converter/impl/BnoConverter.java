package hu.unideb.hospitalnet.service.converter.impl;

import hu.unideb.hospitalnet.core.entity.Bno;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.BnoVo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

public class BnoConverter  implements Converter<Bno, BnoVo>, Serializable{
	
	
	private static final long serialVersionUID = 1L;
	private static Mapper mapper = new DozerBeanMapper();
	
	@Override
	public Bno toEntity(BnoVo vo) {
		return vo == null ? null : mapper.map(vo, Bno.class);
	}

	@Override
	public BnoVo toVo(Bno entity) {
		return entity == null ? null : mapper.map(entity, BnoVo.class);
	}

	@Override
	public List<Bno> toEntity(List<BnoVo> vos) {
		List<Bno> res = new ArrayList<>();
		for (BnoVo item : vos) {
			res.add(toEntity(item));
		}
		return res;
	}

	@Override
	public List<BnoVo> toVo(List<Bno> entities) {
		List<BnoVo> res = new ArrayList<>();
		for (Bno item : entities) {
			res.add(toVo(item));
		}
		return res;
	}

}
