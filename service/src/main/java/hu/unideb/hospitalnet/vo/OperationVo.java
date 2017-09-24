package hu.unideb.hospitalnet.vo;

import java.io.Serializable;
import java.util.Date;

public class OperationVo implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private WorkerVo worker;

	private PatientVo patient;

	private String operationName;

	private Date fromDate;

	private Date toDate;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public WorkerVo getWorker() {
		return worker;
	}

	public void setWorker(WorkerVo worker) {
		this.worker = worker;
	}

	public PatientVo getPatient() {
		return patient;
	}

	public void setPatient(PatientVo patient) {
		this.patient = patient;
	}

	public String getOperationName() {
		return operationName;
	}

	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	
}
