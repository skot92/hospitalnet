package hu.unideb.hospitalnet.core.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "wh_stat")
public class WarehouseStat extends BaseEntity {

	private static final long serialVersionUID = 1L;

	@Column(nullable = false, unique = true)
	private Date dayOfOccurence;

	@Column(nullable = false)
	private Long ordered;

	@Column(nullable = false)
	private Long shippedAway;

	@Column(nullable = false)
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

	public Long getGivenToPatients() {
		return givenToPatients;
	}

	public void setGivenToPatients(Long givenToPatients) {
		this.givenToPatients = givenToPatients;
	}

}
