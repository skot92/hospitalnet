package hu.unideb.hospitalnet.core.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "products")
public class Product  extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "product_name")
	private String name;
	
	@Column(name = "producer")
	private String producer;
	
	@Column(name = "description", columnDefinition="TEXT")
	private String description;
	
	@Column(name = "product_type")
	private String type;
	
	@Column(name = "unit_name")
	private String unitName;
	
	@Column(name = "unit_type")
	private String unitType;

	@OneToMany
	@JoinTable(name = "product_items", joinColumns = { @JoinColumn(name = "product_id") }, inverseJoinColumns = {
			@JoinColumn(name = "item_id") })
	@Cascade({CascadeType.ALL})
	private List<Item> items;

	public String getUnitName() {
		return unitName;
	}

	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getUnitType() {
		return unitType;
	}

	public void setUnitType(String unitType) {
		this.unitType = unitType;
	}
	
	public List<Item> getItems() {
		return items;
	}

	public void setItems(List<Item> items) {
		this.items = items;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
