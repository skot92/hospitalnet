package hu.unideb.hospitalnet.web.patient;

import hu.unideb.hospitalnet.service.MedicalRecordManager;
import hu.unideb.hospitalnet.service.PatientManager;
import hu.unideb.hospitalnet.vo.MedicalRecordVo;
import hu.unideb.hospitalnet.vo.PatientVo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "patientreg")
@ViewScoped
public class PatientREgistationControler implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PatientVo> patients;
	private LazyDataModel<PatientVo> lazyModel;
	private PatientVo selectedPatient;

	private String name;
	private String ssn;
	private String idNumber;
	private Date dateOfBirth;
	private String diagnostic;
	

	private String upname;
	private String upssn;
	private String upidNumber;
	private Date updateOfBirth;
	private String updiagnostic;
	private Long upId;

	@ManagedProperty("#{patientManager}")
	private PatientManager service;
	
	@ManagedProperty("#{medicalRecordManager}")
	private MedicalRecordManager mcrService;

	@PostConstruct
	public void init() {
		diagnostic = " ";
		setLazyModel(new LazyPatientDataModel(service));
	}

	public void save() {
		try {
			PatientVo patientVo = new PatientVo();
			patientVo.setName(name);
			patientVo.setDateOfBirth(dateOfBirth);
			patientVo.setIdNumber(idNumber);
			patientVo.setSsn(ssn);
			patientVo.setStatus("aktív");
			patientVo = service.savePatient(patientVo);
			MedicalRecordVo mcr = new MedicalRecordVo();
			mcr.setDiag(diagnostic);
			mcr.setPatient(patientVo);
			mcr.setDate(new Date());
			mcr.setName(patientVo.getName());
			mcrService.save(mcr);
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres",
							"felvétel: " + patientVo.getName()));
			name = "";
			dateOfBirth = null;
			ssn = "";
			idNumber = "";
			diagnostic = "";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Succes",
							"Sikertelen felvétel"));
		}
	}

	public void update() {
		try{
		selectedPatient.setName(upname);
		selectedPatient.setDateOfBirth(updateOfBirth);
		selectedPatient.setIdNumber(upidNumber);
		selectedPatient.setSsn(upssn);
		selectedPatient.setStatus("aktív");
		selectedPatient.setId(upId);
		service.savePatient(selectedPatient);
		MedicalRecordVo mcr = new MedicalRecordVo();
		mcr.setDiag(updiagnostic);
		mcr.setPatient(selectedPatient);
		mcr.setDate(new Date());
		mcr.setName(selectedPatient.getName() + "" + mcr.getDate());
		mcrService.save(mcr);
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes",
						"Sikeres felvétel: " + selectedPatient.getName()));
		}catch(Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Succes",
							"Sikertelen felvétel: " + selectedPatient.getName()));
		}
	}
	
	

	public void onRowSelect(SelectEvent event) {
		selectedPatient = (PatientVo) event.getObject();
		upname = selectedPatient.getName();
		updateOfBirth = selectedPatient.getDateOfBirth();
		upssn = selectedPatient.getSsn();
		upidNumber = selectedPatient.getIdNumber();
		upId = selectedPatient.getId();

	}
	
	

	public MedicalRecordManager getMcrService() {
		return mcrService;
	}

	public void setMcrService(MedicalRecordManager mcrService) {
		this.mcrService = mcrService;
	}

	public Long getUpId() {
		return upId;
	}

	public void setUpId(Long upId) {
		this.upId = upId;
	}

	public LazyDataModel<PatientVo> getLazyModel() {
		return lazyModel;
	}

	public void setLazyModel(LazyDataModel<PatientVo> lazyModel) {
		this.lazyModel = lazyModel;
	}

	public List<PatientVo> getPatients() {
		return patients;
	}

	public void setPatients(List<PatientVo> patients) {
		this.patients = patients;
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

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public PatientManager getService() {
		return service;
	}

	public void setService(PatientManager service) {
		this.service = service;
	}

	public PatientVo getSelectedPatient() {
		return selectedPatient;
	}

	public void setSelectedPatient(PatientVo selectedPatient) {
		this.selectedPatient = selectedPatient;
	}

	public String getUpname() {
		return upname;
	}

	public void setUpname(String upname) {
		this.upname = upname;
	}

	public String getUpssn() {
		return upssn;
	}

	public void setUpssn(String upssn) {
		this.upssn = upssn;
	}

	public String getUpidNumber() {
		return upidNumber;
	}

	public void setUpidNumber(String upidNumber) {
		this.upidNumber = upidNumber;
	}

	public Date getUpdateOfBirth() {
		return updateOfBirth;
	}

	public void setUpdateOfBirth(Date updateOfBirth) {
		this.updateOfBirth = updateOfBirth;
	}

	public String getUpdiagnostic() {
		return updiagnostic;
	}

	public void setUpdiagnostic(String updiagnostic) {
		this.updiagnostic = updiagnostic;
	}
	
	

}
