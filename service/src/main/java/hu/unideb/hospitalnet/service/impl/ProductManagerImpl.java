package hu.unideb.hospitalnet.service.impl;

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

import hu.unideb.hospitalnet.core.dao.ProductDao;
import hu.unideb.hospitalnet.core.entity.Product;
import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.service.converter.impl.ProductConverter;
import hu.unideb.hospitalnet.vo.ProductVo;

@Service("productManager")
@Transactional(propagation = Propagation.REQUIRED)
public class ProductManagerImpl implements ProductManager, Serializable{

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ProductDao productDao;
	
	ProductConverter converter = new ProductConverter();

	@Override
	public List<ProductVo> getProducts(int page, int pageSize, String sortField, int sortOrder, String filter,
			String filterColumnName) {
		Direction dir = sortOrder == 1 ? Sort.Direction.ASC : Sort.Direction.DESC;
		PageRequest pageRequest = new PageRequest(page, pageSize,
				new Sort(new org.springframework.data.domain.Sort.Order(dir, sortField)));
		Page<Product> entities;

		if (filter.length() != 0 && filterColumnName.equals("name")) {
			entities = productDao.findByNameContaining(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("producer")) {
			entities = productDao.findByProducerContaining(filter, pageRequest);
		} else if (filter.length() != 0 && filterColumnName.equals("type")) {
			entities = productDao.findByTypeContaining(filter, pageRequest);
		}  else if (filter.length() != 0 && filterColumnName.equals("unitType")) {
			entities = productDao.findByUnitTypeContaining(filter, pageRequest);
		}else {
			entities = productDao.findAll(pageRequest);
		}
		
		return converter.toVo(entities.getContent());
	}

	@Override
	public List<ProductVo> getProducts() {
		return converter.toVo(productDao.findAll());
	}
	
	@Override
	public ProductVo getProductById(Long id) {
		return converter.toVo(productDao.findOne(id));
	}

	@Override
	public void saveProduct(ProductVo product) {
		productDao.save(converter.toEntity(product));
	}

	@Override
	public int getProductsCount() {
		return (int) productDao.count();
	}

	@Override
	public List<ProductVo> getProductByName(String query) {
		return converter.toVo(productDao.findByNameContaining(query));
	}

}
