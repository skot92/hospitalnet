package hu.unideb.hospitalnet.core.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "mcr_bno")
public class MedicalRecordBnoTable extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@ManyToOne
	private MedicalRecord mcr;
	
	@ManyToOne
	private Bno bno;

	public MedicalRecord getMcr() {
		return mcr;
	}

	public void setMcr(MedicalRecord mcr) {
		this.mcr = mcr;
	}

	public Bno getBno() {
		return bno;
	}

	public void setBno(Bno bno) {
		this.bno = bno;
	}
	
	
	
}
