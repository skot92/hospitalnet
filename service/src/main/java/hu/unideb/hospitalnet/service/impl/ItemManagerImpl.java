package hu.unideb.hospitalnet.service.impl;

import hu.unideb.hospitalnet.core.dao.ItemDao;
import hu.unideb.hospitalnet.core.entity.Item;
import hu.unideb.hospitalnet.service.ItemManager;
import hu.unideb.hospitalnet.service.converter.impl.ItemConverter;
import hu.unideb.hospitalnet.vo.ItemVo;

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

@Service("itemManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ItemManagerImpl implements ItemManager, Serializable{
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private ItemDao itemDao;
	
	ItemConverter itemConverter = new ItemConverter();
	
	@Override
	public void saveItem(ItemVo item) {
		itemDao.save(itemConverter.toEntity(item));
	}

	@Override
	public void deleteItem(long itemID) {
		itemDao.delete(itemID);
	}
	
	@Override
	public ItemVo getItemById(Long id) {
		return itemConverter.toVo(itemDao.findOne(id));
	}

	@Override
	public List<ItemVo> getItems(int page, int pageSize, String sortField, int sortOrder, String filter,
			String filterColumnName) {
		
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize,
				new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Item> entities;

		//if (filter.length() != 0 && filterColumnName.equals("name")) {
			//entities = itemDao.findByNameContaining(filter, pageRequest);
		//} else {
		entities = itemDao.findAll(pageRequest);
		//}
	
			
		return itemConverter.toVo(entities.getContent());
	}
	
	@Override
	public int getItemsCount() {
		return (int) itemDao.count();
	}
	
	@Override
	public void setStatus(List<ItemVo> itemsVo){
		List<Item> items = itemConverter.toEntity(itemsVo);
		itemDao.save(items);
	}
	
	@Override
	public void setItemsStatus(List<ItemVo> itemsVo){
		List<Item> items = itemConverter.toEntity(itemsVo);
		for(Item i : items)
			i.setStatus("leselejtezett");
		itemDao.save(items);
	}

	@Override
	public void updateItem(String status, Long id) {
		itemDao.updateItemStatus(id, status);
	}

	@Override
	public List<ItemVo> getItems() {
		return itemConverter.toVo(itemDao.findAll());
	}

	@Override
	public void setItemsStatus(List<ItemVo> itemsVo, String status) {
		List<Item> items = itemConverter.toEntity(itemsVo);
		
		for(Item i : items)
			i.setStatus(status);
		
		itemDao.save(items);
		
	}

}
