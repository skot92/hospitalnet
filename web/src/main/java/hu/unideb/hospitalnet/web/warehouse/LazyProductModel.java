package hu.unideb.hospitalnet.web.warehouse;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.vo.ProductVo;

@Service("lazyProductModel")
public class LazyProductModel extends LazyDataModel<ProductVo>{
	private static Logger logger = LoggerFactory.getLogger(LazyProductModel.class);

	private static final long serialVersionUID = 1L;
	
	@Autowired
	ProductManager productManager;
	
	@Override
	public ProductVo getRowData(String rowKey) {
		Long id;

		try {
			id = Long.valueOf(rowKey);
		} catch (NumberFormatException e) {
			logger.warn("Could not parse row key to long!");
			return null;
		}

		return productManager.getProductById(id);
	}


	@Override
	public Object getRowKey(ProductVo product) {
		return product.getId();
	}

	@Override
	public List<ProductVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;

		List<ProductVo> products = productManager.getProducts(first / pageSize, pageSize, sortField, dir, filter,
				filterColumnName);
		
		int dataSize = productManager.getProductsCount();
		setRowCount(dataSize);

		return products;
	}

}
