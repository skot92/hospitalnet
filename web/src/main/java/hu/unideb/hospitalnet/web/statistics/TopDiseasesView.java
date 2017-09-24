package hu.unideb.hospitalnet.web.statistics;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.PieChartModel;

import hu.unideb.hospitalnet.service.StatsManager;
import hu.unideb.hospitalnet.service.stats.vo.DiseaseCountVo;

@ViewScoped
@ManagedBean(name = "statView")
public class TopDiseasesView implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{statsManager}")
	private StatsManager statsManager;

	private PieChartModel pieModel;

	@PostConstruct
	public void init() {
		pieModel = new PieChartModel();
		for (DiseaseCountVo vo : statsManager.getTop10Diseases()) {
			pieModel.set(vo.getDiseaseName(), vo.getCount());
		}
		pieModel.setTitle("Leggyakoribb betegségek");
		pieModel.setLegendPosition("e");
		pieModel.setExtender("pieExtender");
	}

	public StatsManager getStatsManager() {
		return statsManager;
	}

	public void setStatsManager(StatsManager statsManager) {
		this.statsManager = statsManager;
	}

	public PieChartModel getPieModel() {
		return pieModel;
	}

	public void setPieModel(PieChartModel pieModel) {
		this.pieModel = pieModel;
	}

}
