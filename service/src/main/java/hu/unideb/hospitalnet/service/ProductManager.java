package hu.unideb.hospitalnet.service;

import java.util.List;

import hu.unideb.hospitalnet.vo.ProductVo;

public interface ProductManager {
	
	public List<ProductVo> getProducts();
	
	public List<ProductVo> getProducts(int page, int pageSize, String sortField, int sortOrder, String filter,
			String filterColumnName);
	
	public List<ProductVo> getProductByName(String query);
	
	public ProductVo getProductById(Long id);
	
	public void saveProduct(ProductVo product);

	public int getProductsCount();
}
