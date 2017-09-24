package hu.unideb.hospitalnet.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "medical_record")
public class MedicalRecord extends BaseEntity{
	

	private static final long serialVersionUID = 1L;

	@ManyToOne()
	private Patient patient;

	@Column
	private String diag;
	
	@Column
	private Date date;
	
	@Column(name = "mcr_name")
	private String name;
	

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public String getDiag() {
		return diag;
	}

	public void setDiag(String diag) {
		this.diag = diag;
	}
	
	
}
