package hu.unideb.hospitalnet.web.worker.model;

import java.util.List;
import java.util.Map;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import hu.unideb.hospitalnet.service.WorkerManager;
import hu.unideb.hospitalnet.vo.WorkerVo;

@Service("lazyWorkerModel")
public class LazyWorkerModel extends LazyDataModel<WorkerVo> {

	private static Logger logger = LoggerFactory.getLogger(LazyWorkerModel.class);

	private static final long serialVersionUID = 1L;

	@Autowired
	WorkerManager workerManager;

	@Override
	public WorkerVo getRowData(String rowKey) {
		Long id;

		try {
			id = Long.valueOf(rowKey);
		} catch (NumberFormatException e) {
			logger.warn("Could not parse row key to long!");
			return null;
		}

		return workerManager.getWorkerById(id);
	}

	@Override
	public Object getRowKey(WorkerVo worker) {
		return worker.getId();
	}

	@Override
	public List<WorkerVo> load(int first, int pageSize, String sortField, SortOrder sortOrder,
			Map<String, Object> filters) {
		String filter = "";
		String filterColumnName = "";
		if (filters.keySet().size() > 0) {
			filter = (String) filters.values().toArray()[0];
			filterColumnName = filters.keySet().iterator().next();
		}
		if (sortField == null) {
			sortField = "name";
		}

		int dir = sortOrder.equals(SortOrder.ASCENDING) ? 1 : 2;

		List<WorkerVo> workers = workerManager.getWorkers(first / pageSize, pageSize, sortField, dir, filter,
				filterColumnName);

		int dataSize = workerManager.getWorkersCount();

		setRowCount(dataSize);

		return workers;
	}

}
