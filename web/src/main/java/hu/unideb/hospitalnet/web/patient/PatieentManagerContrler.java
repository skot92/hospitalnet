package hu.unideb.hospitalnet.web.patient;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
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

import hu.unideb.hospitalnet.service.BnoManager;
import hu.unideb.hospitalnet.service.ItemManager;
import hu.unideb.hospitalnet.service.MedicalRecordBnoTableManager;
import hu.unideb.hospitalnet.service.MedicalRecordManager;
import hu.unideb.hospitalnet.service.PatientManager;
import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.service.WarehouseStatManager;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;
import hu.unideb.hospitalnet.service.util.DateUtil;
import hu.unideb.hospitalnet.vo.BnoVo;
import hu.unideb.hospitalnet.vo.ItemVo;
import hu.unideb.hospitalnet.vo.MedicalRecordBnoTableVo;
import hu.unideb.hospitalnet.vo.MedicalRecordVo;
import hu.unideb.hospitalnet.vo.PatientVo;
import hu.unideb.hospitalnet.vo.ProductVo;

@ManagedBean(name = "patienmanager")
@ViewScoped
public class PatieentManagerContrler implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<PatientVo> patients;
	private LazyDataModel<PatientVo> lazyModel;
	private LazyDataModel<BnoVo> lazyBnoModel;
	private PatientVo selectedPatient;
	private MedicalRecordVo selectedMcr;

	private List<BnoVo> selectedBnos;
	private Collection<String> bnosNames;
	private List<ItemVo> selectedItems;

	private String name;
	private String ssn;
	private String idNumber;
	private Date dateOfBirth;
	private String diagnostic;
	private Long id;

	private List<MedicalRecordVo> medicalRecords;

	private ProductVo selectedProduct;
	private ItemVo selectedItem;

	private int unit;

	@ManagedProperty("#{patientManager}")
	private PatientManager service;

	@ManagedProperty("#{medicalRecordManager}")
	private MedicalRecordManager mcrService;

	@ManagedProperty("#{bnoManager}")
	private BnoManager bnoManager;

	@ManagedProperty("#{medicalRecordBnoTableManager}")
	private MedicalRecordBnoTableManager medicalRecordBnoTableManager;

	@ManagedProperty("#{productManager}")
	private ProductManager productManager;

	@ManagedProperty("#{itemManager}")
	private ItemManager itemManager;

	@ManagedProperty("#{lazyProductModel}")
	private LazyDataModel<ProductVo> lazyProductModel;

	@ManagedProperty("#{warehouseStatManager}")
	private WarehouseStatManager warehouseStatManager;

	@PostConstruct
	public void init() {
		diagnostic = " ";
		selectedItems = new ArrayList<ItemVo>();
		setLazyModel(new LazyPatientDataModel(service));
		setLazyBnoModel(new BnoLazyDataModel(bnoManager));
	}

	public void mcrUpdate() {
		diagnostic = selectedMcr.getDiag();
	}

	public void updateMcr() {
		selectedMcr.setDiag(diagnostic);
		mcrService.save(selectedMcr);

		MedicalRecordBnoTableVo mcbt = new MedicalRecordBnoTableVo();
		for (BnoVo bno : selectedBnos) {
			mcbt.setBno(bno);
			mcbt.setMcr(selectedMcr);
			medicalRecordBnoTableManager.save(mcbt);
		}

		for (ItemVo item : selectedItems) {
			itemManager.saveItem(item);
		}

	}

	public void onRowSelect(SelectEvent event) {
		selectedPatient = (PatientVo) event.getObject();
		medicalRecords = mcrService.findByPatientId(selectedPatient.getId());
		medicalRecords.size();

	}

	public void onRowSelectProduct(SelectEvent e) {
		ProductVo pvo = (ProductVo) e.getObject();
		List<ItemVo> activeItems = new ArrayList<>();

		if (!pvo.getItems().isEmpty()) {
			for (ItemVo item : pvo.getItems()) {
				if (item.getStatus().equals("aktív")) {
					activeItems.add(item);
				}
			}

			pvo.setItems(activeItems);
		}
		selectedProduct = pvo;
	}

	public void addBnoToMcr() {
		for (BnoVo string : selectedBnos) {
			diagnostic += " " + string.getKod10();
		}
	}

	public void dismiss() {
		try {
			selectedPatient.setStatus("elbocsajtva");
			service.savePatient(selectedPatient);
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Succes", "Sikser"));
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Sikertelen"));
			e.getMessage();
			e.printStackTrace();
		}
	}

	public void addMedicine() {
		int a = itemManager.getItemById(selectedItem.getId()).getNumberOfUnitNow();
		if (unit < a) {
			diagnostic += selectedProduct.getName() + " " + unit + " " + selectedProduct.getUnitName();
			selectedItem.setNumberOfUnitNow(a - unit);
			selectedItems.add(selectedItem);
			saveStat(unit);
		}
	}

	private void saveStat(int givenToRats) {
		WarehouseStatVo whStatVo = new WarehouseStatVo();
		whStatVo.setDayOfOccurence(DateUtil.getStartOfDay(DateUtil.now()));
		whStatVo.setOrdered(0L);
		whStatVo.setShippedAway(0L);
		whStatVo.setGivenToPatients(Long.valueOf(givenToRats));
		warehouseStatManager.addStat(whStatVo);
	}

	public int getUnit() {
		return unit;
	}

	public void setUnit(int unit) {
		this.unit = unit;
	}

	public ItemVo getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(ItemVo selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public ItemVo getSelectedItems() {
		return selectedItem;
	}

	public void setSelectedItems(ItemVo selectedItem) {
		this.selectedItem = selectedItem;
	}

	public ProductVo getSelectedProduct() {
		return selectedProduct;
	}

	public void setSelectedProduct(ProductVo selectedProduct) {
		this.selectedProduct = selectedProduct;
	}

	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public LazyDataModel<ProductVo> getLazyProductModel() {
		return lazyProductModel;
	}

	public void setLazyProductModel(LazyDataModel<ProductVo> lazyProductModel) {
		this.lazyProductModel = lazyProductModel;
	}

	public MedicalRecordBnoTableManager getMedicalRecordBnoTableManager() {
		return medicalRecordBnoTableManager;
	}

	public void setMedicalRecordBnoTableManager(MedicalRecordBnoTableManager medicalRecordBnoTableManager) {
		this.medicalRecordBnoTableManager = medicalRecordBnoTableManager;
	}

	public List<BnoVo> getSelectedBnos() {
		return selectedBnos;
	}

	public void setSelectedBnos(List<BnoVo> selectedBnos) {
		this.selectedBnos = selectedBnos;
	}

	public LazyDataModel<BnoVo> getLazyBnoModel() {
		return lazyBnoModel;
	}

	public void setLazyBnoModel(LazyDataModel<BnoVo> lazyBnoModel) {
		this.lazyBnoModel = lazyBnoModel;
	}

	public Collection<String> getBnosNames() {
		return bnosNames;
	}

	public void setBnosNames(Collection<String> bnosNames) {
		this.bnosNames = bnosNames;
	}

	public List<BnoVo> getBnos() {
		return selectedBnos;
	}

	public void setBnos(List<BnoVo> bnos) {
		this.selectedBnos = bnos;
	}

	public BnoManager getBnoManager() {
		return bnoManager;
	}

	public void setBnoManager(BnoManager bnoManager) {
		this.bnoManager = bnoManager;
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

	public MedicalRecordManager getMcrService() {
		return mcrService;
	}

	public void setMcrService(MedicalRecordManager mcrService) {
		this.mcrService = mcrService;
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

	public WarehouseStatManager getWarehouseStatManager() {
		return warehouseStatManager;
	}

	public void setWarehouseStatManager(WarehouseStatManager warehouseStatManager) {
		this.warehouseStatManager = warehouseStatManager;
	}

}
