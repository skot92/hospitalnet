package hu.unideb.hospitalnet.web.warehouse;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.service.ItemManager;
import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.vo.ItemVo;
import hu.unideb.hospitalnet.vo.ProductVo;

@Service("lazyThrowedItemModel")
public class LazyThrowedItemModel  extends LazyDataModel<ThrowedItem>{
	
	private static Logger logger = LoggerFactory.getLogger(LazyThrowedItemModel.class);
	
	private static final long serialVersionUID = 1L;
	
	@Autowired
	ItemManager itemManager;
	
	@Autowired
	ProductManager productManager;
	
	@Override
	public ThrowedItem getRowData(String rowKey) {
		Long id;

		try {
			id = Long.valueOf(rowKey);
		} catch (NumberFormatException e) {
			logger.warn("Could not parse row key to long!");
			return null;
		}
		
		ItemVo ivo = itemManager.getItemById(id);
		ThrowedItem throwedItem = new ThrowedItem();
		throwedItem.setId(ivo.getId());
		throwedItem.setNumberOfUnit(ivo.getNumberOfUnit());
		throwedItem.setNumberOfUnitNow(ivo.getNumberOfUnitNow());
		throwedItem.setWarranty(ivo.getWarranty());
		return throwedItem;
	}


	@Override
	public Object getRowKey(ThrowedItem item) {
		return item.getId();
	}
	
	@Override
	public List<ThrowedItem> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {

		List<ProductVo> products = productManager.getProducts();
		
		List<ThrowedItem> requestedList = new ArrayList<>();
		
		for(ProductVo pvo : products)
			for(ItemVo ivo : pvo.getItems()){
				if(ivo.getStatus().equals("leselejtezett")){
					ThrowedItem throwedItem = new ThrowedItem();
					throwedItem.setId(ivo.getId());
					throwedItem.setNumberOfUnit(ivo.getNumberOfUnit());
					throwedItem.setNumberOfUnitNow(ivo.getNumberOfUnitNow());
					throwedItem.setWarranty(ivo.getWarranty());
					throwedItem.setStatus("leselejtezett");
					throwedItem.setUnitName(pvo.getUnitName());
					throwedItem.setProductName(pvo.getName());
					
					requestedList.add(throwedItem);
				}
			}
		
		int dataSize = requestedList.size();
		setRowCount(dataSize);

		return requestedList;
	}
}
