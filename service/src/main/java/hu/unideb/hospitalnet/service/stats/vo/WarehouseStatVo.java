package hu.unideb.hospitalnet.service.stats.vo;

import java.io.Serializable;
import java.util.Date;

public class WarehouseStatVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private Date dayOfOccurence;

	private Long ordered;

	private Long shippedAway;

	private Long givenToPatients;

	public Date getDayOfOccurence() {
		return dayOfOccurence;
	}

	public void setDayOfOccurence(Date dayOfOccurence) {
		this.dayOfOccurence = dayOfOccurence;
	}

	public Long getOrdered() {
		return ordered;
	}

	public void setOrdered(Long ordered) {
		this.ordered = ordered;
	}

	public Long getShippedAway() {
		return shippedAway;
	}

	public void setShippedAway(Long shippedAway) {
		this.shippedAway = shippedAway;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getGivenToPatients() {
		return givenToPatients;
	}

	public void setGivenToPatients(Long givenToPatients) {
		this.givenToPatients = givenToPatients;
	}

}
