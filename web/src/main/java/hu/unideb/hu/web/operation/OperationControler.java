package hu.unideb.hu.web.operation;

import hu.unideb.hospitalnet.service.MedicalRecordManager;
import hu.unideb.hospitalnet.service.OperationManager;
import hu.unideb.hospitalnet.service.PatientManager;
import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.MedicalRecordVo;
import hu.unideb.hospitalnet.vo.OperationVo;
import hu.unideb.hospitalnet.vo.PatientVo;
import hu.unideb.hospitalnet.vo.WorkerVo;
import hu.unideb.hospitalnet.web.patient.LazyPatientDataModel;

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
import org.springframework.security.core.context.SecurityContextHolder;

@ManagedBean(name = "operationmanager")
@ViewScoped
public class OperationControler implements Serializable {

	private static final long serialVersionUID = 1L;

	private LazyDataModel<PatientVo> patientLazyModel;
	private LazyDataModel<OperationVo> opLazyModel;

	@ManagedProperty("#{operationManager}")
	private OperationManager opService;

	@ManagedProperty("#{patientManager}")
	private PatientManager patientService;

	@ManagedProperty("#{workerManager}")
	private WorkerManager workerManager;

	@ManagedProperty("#{medicalRecordManager}")
	private MedicalRecordManager mcrService;

	private Date startOp;
	private Date endOp;
	private PatientVo selectedpatient;
	private String opName;
	private List<OperationVo> operations;

	private MedicalRecordVo selectedMcr;
	private List<MedicalRecordVo> medicalRecords;

	private String diagnostic;
	
	private Date fromOpDate = new Date();

	@PostConstruct
	public void init() {
		setPatientLazyModel(new LazyPatientDataModel(patientService));
		setOpLazyModel(new OperationLazyDataModel(opService, fromOpDate));
		startOp = null;
		endOp = null;
		opService.setUsername(SecurityContextHolder.getContext()
				.getAuthentication().getName());

	}

	public void saveOperation() {
		try {
			OperationVo op = new OperationVo();
			String name = SecurityContextHolder.getContext()
					.getAuthentication().getName();
			WorkerVo doctor = workerManager.getWorkerByUsername(name);
			op.setWorker(doctor);
			op.setFromDate(startOp);
			op.setToDate(endOp);
			op.setPatient(selectedpatient);
			op.setOperationName(opName);
			opService.save(op);
			opService.setUsername(SecurityContextHolder.getContext()
					.getAuthentication().getName());
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes",
							"Sikeres kiírás "));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
							"Sikertelen kiírás"));
		}
	}

	public void onRowSelect(SelectEvent event) {
		medicalRecords = mcrService.findByPatientId(selectedpatient.getId());
		medicalRecords.size();

	}

	public void mcrUpdate() {
		diagnostic = selectedMcr.getDiag();
	}

	public void updateMcr() {
		selectedMcr.setDiag(diagnostic);
		mcrService.save(selectedMcr);
	}
	
	
	

	public Date getFromOpDate() {
		return fromOpDate;
	}

	public void setFromOpDate(Date fromOpDate) {
		this.fromOpDate = fromOpDate;
	}

	public LazyDataModel<OperationVo> getOpLazyModel() {
		return opLazyModel;
	}

	public void setOpLazyModel(LazyDataModel<OperationVo> opLazyModel) {
		this.opLazyModel = opLazyModel;
	}

	public String getDiagnostic() {
		return diagnostic;
	}

	public void setDiagnostic(String diagnostic) {
		this.diagnostic = diagnostic;
	}

	public MedicalRecordManager getMcrService() {
		return mcrService;
	}

	public void setMcrService(MedicalRecordManager mcrService) {
		this.mcrService = mcrService;
	}

	public MedicalRecordVo getSelectedMcr() {
		return selectedMcr;
	}

	public void setSelectedMcr(MedicalRecordVo selectedMcr) {
		this.selectedMcr = selectedMcr;
	}

	public List<MedicalRecordVo> getMedicalRecords() {
		return medicalRecords;
	}

	public void setMedicalRecords(List<MedicalRecordVo> medicalRecords) {
		this.medicalRecords = medicalRecords;
	}

	public List<OperationVo> getOperations() {
		return operations;
	}

	public void setOperations(List<OperationVo> operations) {
		this.operations = operations;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public PatientVo getSelectedpatient() {
		return selectedpatient;
	}

	public void setSelectedpatient(PatientVo selectedpatient) {
		this.selectedpatient = selectedpatient;
	}

	public LazyDataModel<PatientVo> getPatientLazyModel() {
		return patientLazyModel;
	}

	public void setPatientLazyModel(LazyDataModel<PatientVo> patientLazyModel) {
		this.patientLazyModel = patientLazyModel;
	}

	public PatientManager getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientManager patientService) {
		this.patientService = patientService;
	}

	public WorkerManager getWorkerManager() {
		return workerManager;
	}

	public void setWorkerManager(WorkerManager workerManager) {
		this.workerManager = workerManager;
	}

	public Date getStartOp() {
		return startOp;
	}

	public void setStartOp(Date startOp) {
		this.startOp = startOp;
	}

	public Date getEndOp() {
		return endOp;
	}

	public void setEndOp(Date endOp) {
		this.endOp = endOp;
	}

	public OperationManager getOpService() {
		return opService;
	}

	public void setOpService(OperationManager opService) {
		this.opService = opService;
	}

}
