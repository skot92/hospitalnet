package hu.unideb.hospitalnet.web.worker.converter;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.WorkerVo;

@ManagedBean(name = "workerVoConverter")
@RequestScoped
public class WorkerVoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{workerManager}")
	private WorkerManager workerManager;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		if (value != null && value.trim().length() > 0) {
			try {
				return workerManager.getWorkerById(Long.valueOf(value));
			} catch (NumberFormatException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Érvénytelen dolgozó!"));
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return value == null ? null : String.valueOf(((WorkerVo) value).getId());
	}

	public WorkerManager getWorkerManager() {
		return workerManager;
	}

	public void setWorkerManager(WorkerManager workerManager) {
		this.workerManager = workerManager;
	}

}
