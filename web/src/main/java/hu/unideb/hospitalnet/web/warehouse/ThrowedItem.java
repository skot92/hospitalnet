package hu.unideb.hospitalnet.web.warehouse;

import java.io.Serializable;
import java.util.Date;

public class ThrowedItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String productName;
	private String unitName;
	
	private Long id;
	private int numberOfUnit;
	private int numberOfUnitNow;
	private Date warranty;
	private String status;
	
	
	public ThrowedItem() {}
	
	public ThrowedItem(String productName, String unitName, Long id, int numberOfUnit, int numberOfUnitNow,
			Date warranty, String status) {
		super();
		this.productName = productName;
		this.unitName = unitName;
		this.id = id;
		this.numberOfUnit = numberOfUnit;
		this.numberOfUnitNow = numberOfUnitNow;
		this.warranty = warranty;
		this.status = status;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public int getNumberOfUnit() {
		return numberOfUnit;
	}
	public void setNumberOfUnit(int numberOfUnit) {
		this.numberOfUnit = numberOfUnit;
	}
	public int getNumberOfUnitNow() {
		return numberOfUnitNow;
	}
	public void setNumberOfUnitNow(int numberOfUnitNow) {
		this.numberOfUnitNow = numberOfUnitNow;
	}
	public Date getWarranty() {
		return warranty;
	}
	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
