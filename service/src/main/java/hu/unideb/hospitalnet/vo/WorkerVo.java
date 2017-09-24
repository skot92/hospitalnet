package hu.unideb.hospitalnet.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class WorkerVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private String name;

	private String ssn;

	private String idNumber;

	private Date dateOfBirth;

	private String username;

	private String password;

	private RoleVo role;

	private List<TimeTableVo> timeTables;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSsn() {
		return ssn;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getIdNumber() {
		return idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public RoleVo getRole() {
		return role;
	}

	public void setRole(RoleVo role) {
		this.role = role;
	}

	public List<TimeTableVo> getTimeTables() {
		return timeTables;
	}

	public void setTimeTables(List<TimeTableVo> timeTables) {
		this.timeTables = timeTables;
	}

}
