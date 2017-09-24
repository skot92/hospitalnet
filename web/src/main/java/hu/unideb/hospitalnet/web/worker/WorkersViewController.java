package hu.unideb.hospitalnet.web.worker;

import java.io.Serializable;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import hu.unideb.hospitalnet.service.RoleManager;
import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.RoleVo;
import hu.unideb.hospitalnet.vo.WorkerVo;

@ViewScoped
@ManagedBean(name = "workersView")
public class WorkersViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{workerManager}")
	private WorkerManager workerManager;

	@ManagedProperty("#{roleManager}")
	private RoleManager roleManager;

	@ManagedProperty("#{lazyWorkerModel}")
	private LazyDataModel<WorkerVo> lazyWorkerModel;

	private WorkerVo selectedWorker;

	private String newPassword;
	private String newPassword2;

	private String newRole;

	private Boolean newWorker = null;

	public void onRowSelect(SelectEvent e) {
		selectedWorker = (WorkerVo) e.getObject();

		newRole = roleManager.nameOfRole(selectedWorker.getRole());
		newPassword = "";
		newPassword2 = "";
		newWorker = false;
	}

	public void onAddButtonClick() {
		selectedWorker = new WorkerVo();

		newRole = "";
		newPassword = "";
		newPassword2 = "";
		newWorker = true;

		RequestContext requestContext = RequestContext.getCurrentInstance();
		requestContext.update("updateWorkerForm:workerDetail");
		requestContext.update("form:updateWorkerButton");
		requestContext.execute("PF('workerDialogWidget').show();");
	}

	public String roleView(RoleVo role) {
		return roleManager.nameOfRole(role);
	}

	public void save() {
		if (validateInput()) {

			if (!newPassword.equals("")) {
				BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
				String encPassword = bCryptPasswordEncoder.encode(newPassword);

				selectedWorker.setPassword(encPassword);
			}

			RoleVo role = roleManager.getRoleByName("ROLE_" + newRole);
			selectedWorker.setRole(role);

			workerManager.saveWorker(selectedWorker);

			RequestContext requestContext = RequestContext.getCurrentInstance();
			requestContext.update("workerDialog");
			requestContext.update("form:workerTable");
		}
	}

	private boolean validateInput() {
		FacesContext context = FacesContext.getCurrentInstance();
		Boolean valid = true;
		if (!validateDate()) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Hibás dátum"));
			valid = false;
		}
		if (newRole.equals("")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "A munkakör megadása kötelező!"));
			valid = false;
		}
		if (!newPassword.equals(newPassword2)) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "A megadott jelszó nem egyezik!"));
			valid = false;
		}
		if (selectedWorker.getSsn().equals("")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "TAJ szám megadása kötelező!"));
			valid = false;
		}
		if (selectedWorker.getIdNumber().equals("")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Személyi szám megadása kötelező!"));
			valid = false;
		}
		if (selectedWorker.getName().equals("")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "A dolgozó nevének megadása kötelező!"));
			valid = false;
		}
		if (newWorker && (newPassword.equals("") || newPassword2.equals(""))) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Jelszó megadása kötelező!"));
			valid = false;
		}
		return valid;
	}

	private Boolean validateDate() {
		Date now = new Date();

		if (now.before(selectedWorker.getDateOfBirth())) {
			return false;
		}

		return true;
	}

	public WorkerManager getWorkerManager() {
		return workerManager;
	}

	public void setWorkerManager(WorkerManager workerManager) {
		this.workerManager = workerManager;
	}

	public RoleManager getRoleManager() {
		return roleManager;
	}

	public void setRoleManager(RoleManager roleManager) {
		this.roleManager = roleManager;
	}

	public WorkerVo getSelectedWorker() {
		return selectedWorker;
	}

	public void setSelectedWorker(WorkerVo selectedWorker) {
		this.selectedWorker = selectedWorker;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getNewPassword2() {
		return newPassword2;
	}

	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}

	public String getNewRole() {
		return newRole;
	}

	public void setNewRole(String newRole) {
		this.newRole = newRole;
	}

	public Boolean getNewWorker() {
		return newWorker;
	}

	public void setNewWorker(Boolean newWorker) {
		this.newWorker = newWorker;
	}

	public LazyDataModel<WorkerVo> getLazyWorkerModel() {
		return lazyWorkerModel;
	}

	public void setLazyWorkerModel(LazyDataModel<WorkerVo> lazyWorkerModel) {
		this.lazyWorkerModel = lazyWorkerModel;
	}

}
