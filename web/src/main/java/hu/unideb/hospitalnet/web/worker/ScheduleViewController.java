package hu.unideb.hospitalnet.web.worker;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.primefaces.event.ScheduleEntryMoveEvent;
import org.primefaces.event.ScheduleEntryResizeEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.ScheduleModel;

import hu.unideb.hospitalnet.service.TimeTableManager;
import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.TimeTableVo;
import hu.unideb.hospitalnet.vo.WorkerVo;
import hu.unideb.hospitalnet.web.worker.model.TimeTableEvent;
import hu.unideb.hospitalnet.web.worker.model.WorkerScheduleModel;

@ViewScoped
@ManagedBean(name = "scheduleView")
public class ScheduleViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{workerManager}")
	private WorkerManager workerManager;

	@ManagedProperty("#{timeTableManager}")
	private TimeTableManager timeTableManager;

	private WorkerVo worker;

	@ManagedProperty("#{workerScheduleModel}")
	private WorkerScheduleModel lazyEventModel;

	private TimeTableVo timeTable;

	private TimeTableEvent event;

	@PostConstruct
	public void init() {
		event = new TimeTableEvent();

		TimeTableVo tt = new TimeTableVo();
		tt.setFrom(new Date());
		tt.setTo(new Date());

		event.setTt(tt);
	}

	public List<WorkerVo> completeWorker(String query) {
		return workerManager.getWorkerByName(query);
	}

	public void onSelect(SelectEvent e) {
		worker = (WorkerVo) e.getObject();
		lazyEventModel.setWorkerId(worker.getId());
	}

	public void addEvent(ActionEvent actionEvent) {
		if (timeTableManager.hasCoveringTimeTable(worker.getId(), event.getTt())) {
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nem lehet beosztás átfedő!"));
		} else if (event.getId() == null) {
			timeTableManager.addTimeTableToWorker(worker.getId(), event.getTt());
		} else {
			timeTableManager.saveTimeTable(event.getTt());
		}
	}

	public void onEventSelect(SelectEvent selectEvent) {
		event = (TimeTableEvent) selectEvent.getObject();
	}

	public void onDateSelect(SelectEvent selectEvent) {
		event = new TimeTableEvent();
		TimeTableVo tvo = new TimeTableVo();
		tvo.setFrom((Date) selectEvent.getObject());
		tvo.setTo((Date) selectEvent.getObject());
		event.setTt(tvo);
	}

	public void onEventMove(ScheduleEntryMoveEvent moveEvent) {
		TimeTableEvent movedEvent = (TimeTableEvent) moveEvent.getScheduleEvent();
		if (timeTableManager.hasCoveringTimeTable(worker.getId(), movedEvent.getTt())) {
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nem lehet beosztás átfedő!"));

			return;
		}
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker!", "Beosztás módosítva");
		addMessage(message);

		timeTableManager.saveTimeTable(movedEvent.getTt());
	}

	public void onEventResize(ScheduleEntryResizeEvent resizeEvent) {
		TimeTableEvent movedEvent = (TimeTableEvent) resizeEvent.getScheduleEvent();
		if (timeTableManager.hasCoveringTimeTable(worker.getId(), movedEvent.getTt())) {
			addMessage(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nem lehet beosztás átfedő!"));

			return;
		}

		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Siker!", "Beosztás módosítva");
		addMessage(message);

		timeTableManager.saveTimeTable(movedEvent.getTt());
	}

	private void addMessage(FacesMessage message) {
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public WorkerManager getWorkerManager() {
		return workerManager;
	}

	public void setWorkerManager(WorkerManager workerManager) {
		this.workerManager = workerManager;
	}

	public TimeTableManager getTimeTableManager() {
		return timeTableManager;
	}

	public void setTimeTableManager(TimeTableManager timeTableManager) {
		this.timeTableManager = timeTableManager;
	}

	public WorkerVo getWorker() {
		return worker;
	}

	public void setWorker(WorkerVo worker) {
		this.worker = worker;
	}

	public ScheduleModel getLazyEventModel() {
		return lazyEventModel;
	}

	public void setLazyEventModel(ScheduleModel lazyEventModel) {
		this.lazyEventModel = (WorkerScheduleModel) lazyEventModel;
	}

	public TimeTableVo getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTableVo timeTable) {
		this.timeTable = timeTable;
	}

	public TimeTableEvent getEvent() {
		return event;
	}

	public void setEvent(TimeTableEvent event) {
		this.event = event;
	}

}
