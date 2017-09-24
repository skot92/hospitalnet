package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.Item;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.ItemVo;

public class ItemConverter implements Converter<Item, ItemVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public Item toEntity(ItemVo vo) {
		return vo == null ? null : mapper.map(vo, Item.class);
	}

	@Override
	public ItemVo toVo(Item entity) {
		return entity == null ? null : mapper.map(entity, ItemVo.class);
	}

	@Override
	public List<Item> toEntity(List<ItemVo> vos) {
		List<Item> res = new ArrayList<>();
		for (ItemVo item : vos) {
			res.add(toEntity(item));
		}
		return res;
	}

	@Override
	public List<ItemVo> toVo(List<Item> entities) {
		List<ItemVo> res = new ArrayList<>();
		for (Item item : entities) {
			res.add(toVo(item));
		}
		return res;
	}

}
