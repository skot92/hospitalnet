package hu.unideb.hospitalnet.vo;

import java.io.Serializable;
import java.util.Date;

public class ItemVo implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private int numberOfUnit;
	
	private int numberOfUnitNow;
	
	private Date warranty;
	
	private String status;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getNumberOfUnitNow() {
		return numberOfUnitNow;
	}

	public void setNumberOfUnitNow(int numberOfUnitNow) {
		this.numberOfUnitNow = numberOfUnitNow;
	}

	
}
