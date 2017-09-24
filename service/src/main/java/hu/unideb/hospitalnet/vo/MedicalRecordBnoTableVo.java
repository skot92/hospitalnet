package hu.unideb.hospitalnet.vo;

import java.io.Serializable;

public class MedicalRecordBnoTableVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;
	
	private MedicalRecordVo mcr;
	
	private BnoVo bno;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public MedicalRecordVo getMcr() {
		return mcr;
	}

	public void setMcr(MedicalRecordVo mcr) {
		this.mcr = mcr;
	}

	public BnoVo getBno() {
		return bno;
	}

	public void setBno(BnoVo bno) {
		this.bno = bno;
	}
	
	
}
