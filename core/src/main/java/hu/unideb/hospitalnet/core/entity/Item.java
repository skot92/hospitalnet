package hu.unideb.hospitalnet.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "items")
public class Item extends BaseEntity{
	
	private static final long serialVersionUID = 1L;
	
	@Column(name = "unit")
	private int numberOfUnit;
	
	@Column(name = "unit_n")
	private int numberOfUnitNow;
	
	@Column(name = "doe")
	private Date warranty;
	
	@Column(name = "status")
	private String status;

	
	public int getNumberOfUnitNow() {
		return numberOfUnitNow;
	}

	public void setNumberOfUnitNow(int numberOfUnitNow) {
		this.numberOfUnitNow = numberOfUnitNow;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getNumberOfUnit() {
		return numberOfUnit;
	}

	public void setNumberOfUnit(int numberOfUnit) {
		this.numberOfUnit = numberOfUnit;
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
	
	


	
}
