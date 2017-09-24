package hu.unideb.hospitalnet.vo;

import java.io.Serializable;

public class BnoVo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long id;
	
	private String kod10;
	
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getKod10() {
		return kod10;
	}

	public void setKod10(String kod10) {
		this.kod10 = kod10;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}
