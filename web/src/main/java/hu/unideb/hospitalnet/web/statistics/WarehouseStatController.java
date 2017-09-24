package hu.unideb.hospitalnet.web.statistics;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import hu.unideb.hospitalnet.service.WarehouseStatManager;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;
import hu.unideb.hospitalnet.service.util.DateUtil;

@ManagedBean(name = "whStatView")
@ViewScoped
public class WarehouseStatController implements Serializable {

	private static final long serialVersionUID = 1L;

	private BarChartModel barModel;

	@ManagedProperty("#{warehouseStatManager}")
	private WarehouseStatManager warehouseStatManager;

	@PostConstruct
	public void init() {
		Date start = DateUtil.getStartOfDay(aWeekBefore());
		Date end = DateUtil.getEndOfDay(DateUtil.now());
		List<WarehouseStatVo> stats = warehouseStatManager.getStatsBetween(start, end);

		barModel = new BarChartModel();

		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Dátum");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Mennyiség");

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");

		ChartSeries ordered = new ChartSeries("Rendelt");
		ChartSeries givenAway = new ChartSeries("Kiírt");
		ChartSeries trash = new ChartSeries("Elszállítva");

		for (WarehouseStatVo whs : stats) {
			String dateStr = sdf.format(whs.getDayOfOccurence());
			ordered.set(dateStr, whs.getOrdered());
			givenAway.set(dateStr, whs.getGivenToPatients());
			trash.set(dateStr, whs.getShippedAway());
		}

		barModel.addSeries(ordered);
		barModel.addSeries(givenAway);
		barModel.addSeries(trash);
	}

	private Date aWeekBefore() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(DateUtil.now());
		cal.set(Calendar.DAY_OF_YEAR, -7);

		return cal.getTime();
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

	public WarehouseStatManager getWarehouseStatManager() {
		return warehouseStatManager;
	}

	public void setWarehouseStatManager(WarehouseStatManager warehouseStatManager) {
		this.warehouseStatManager = warehouseStatManager;
	}

}
