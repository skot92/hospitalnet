package hu.unideb.hospitalnet.service.converter;

import java.io.Serializable;
import java.util.List;

public interface Converter<E extends Serializable, V extends Serializable> extends Serializable {
	E toEntity(V vo);

	V toVo(E entity);

	List<E> toEntity(List<V> vos);

	List<V> toVo(List<E> entities);
}
