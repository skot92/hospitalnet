package hu.unideb.hospitalnet.core.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "bno")
public class Bno extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(name = "kod10", unique = true)
	private String kod10;

	@Column(name = "bno_name")
	private String name;

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
