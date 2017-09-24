package hu.unideb.hospitalnet.service.converter.impl;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

import hu.unideb.hospitalnet.core.entity.Product;
import hu.unideb.hospitalnet.service.converter.Converter;
import hu.unideb.hospitalnet.vo.ProductVo;

public class ProductConverter implements Converter<Product, ProductVo> {

	private static final long serialVersionUID = 1L;

	private static Mapper mapper = new DozerBeanMapper();

	@Override
	public Product toEntity(ProductVo vo) {
		return vo == null ? null : mapper.map(vo, Product.class);
	}

	@Override
	public ProductVo toVo(Product entity) {
		return entity == null ? null : mapper.map(entity, ProductVo.class);
	}

	@Override
	public List<Product> toEntity(List<ProductVo> vos) {
		List<Product> res = new ArrayList<>();
		for (ProductVo product : vos) {
			res.add(toEntity(product));
		}
		return res;
	}

	@Override
	public List<ProductVo> toVo(List<Product> entities) {
		List<ProductVo> res = new ArrayList<>();
		for (Product product : entities) {
			res.add(toVo(product));
		}
		return res;
	}

}
