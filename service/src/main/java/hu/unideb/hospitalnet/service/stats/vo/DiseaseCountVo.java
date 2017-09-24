package hu.unideb.hospitalnet.service.stats.vo;

import java.io.Serializable;

public class DiseaseCountVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private String diseaseName;

	private int count;

	public String getDiseaseName() {
		return diseaseName;
	}

	public void setDiseaseName(String diseaseName) {
		this.diseaseName = diseaseName;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

}
