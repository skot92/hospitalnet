package hu.unideb.hospitalnet.service;

import java.util.List;

import hu.unideb.hospitalnet.vo.ItemVo;


public interface ItemManager {
	
	public List<ItemVo> getItems(int page, int pageSize, String sortField, int sortOrder, String filter,
			String filterColumnName);
	
	public List<ItemVo> getItems();
	
	public void saveItem(ItemVo item);

	public void deleteItem(long itemID);
	
	public ItemVo getItemById(Long id);

	public int getItemsCount();
	
	void updateItem(String status, Long id);
	
	public void setStatus(List<ItemVo> itemsVo);
	
	public void setItemsStatus(List<ItemVo> itemsVo);
	
	public void setItemsStatus(List<ItemVo> itemsVo, String status);
}
