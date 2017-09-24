package hu.unideb.hospitalnet.web.warehouse;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;

import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.service.WarehouseStatManager;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;
import hu.unideb.hospitalnet.service.util.DateUtil;
import hu.unideb.hospitalnet.vo.ItemVo;
import hu.unideb.hospitalnet.vo.ProductVo;

@ViewScoped
@ManagedBean(name = "addItemView")
public class AddItemViewController implements Serializable {

	private static final long serialVersionUID = 1L;

	@ManagedProperty("#{productManager}")
	private ProductManager productManager;

	@ManagedProperty("#{warehouseStatManager}")
	private WarehouseStatManager warehouseStatManager;

	private ProductVo product;

	private String unit;
	private Date warranty;

	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ProductVo getProduct() {
		return product;
	}

	public void setProduct(ProductVo product) {
		this.product = product;
	}

	public void onSelect(SelectEvent e) {
		product = (ProductVo) e.getObject();
	}

	public List<ProductVo> completeProduct(String query) {
		return productManager.getProductByName(query);
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Date getWarranty() {
		return warranty;
	}

	public void setWarranty(Date warranty) {
		this.warranty = warranty;
	}

	public void save() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			if (validateItem()) {
				addSelectedProduct();
				productManager.saveProduct(product);
				saveStat(Integer.parseInt(unit));
				String message = unit + " "+ product.getUnitName()+ " ("+product.getName()+ ")";
				product = null;
				unit = "";
				warranty = null;
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_INFO, "Tétel sikeresen hozzáadva!", message));
			}
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Nem sikerült a tétel mentése!", e.getMessage()));
		}

	}

	private Boolean validateItem() {
		FacesContext context = FacesContext.getCurrentInstance();
		Boolean isValid = true;

		if (product == null) {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nincs megadva a termék!"));
			isValid = false;
		}
		if (unit.equals("")) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nincs megadva a tétel mérete!"));
			isValid = false;
		} else {
			if (!isInt(unit)) {
				context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "A méret egész szám!"));
				isValid = false;
			}
		}

		if (warranty == null) {
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nincs megadva a szavatosság!"));
			isValid = false;
		} else {
			if (validateDate()) {
				context.addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "A szavatosság már lejárt!"));
				isValid = false;
			}
		}
		if (!isValid) {
			return false;
		}

		return true;

	}

	private Boolean validateDate() {
		Date now = new Date();

		if (now.before(warranty)) {
			return false;
		}

		return true;
	}

	public Boolean isInt(String number) {
		try {
			Integer.parseInt(number);
		} catch (NumberFormatException | NullPointerException e) {
			return false;
		}
		return true;
	}

	public WarehouseStatManager getWarehouseStatManager() {
		return warehouseStatManager;
	}

	public void setWarehouseStatManager(WarehouseStatManager warehouseStatManager) {
		this.warehouseStatManager = warehouseStatManager;
	}

	private ItemVo createItemVo() {
		ItemVo item = new ItemVo();
		int unitSize = Integer.parseInt(unit);
		item.setNumberOfUnit(unitSize);
		item.setNumberOfUnitNow(unitSize);
		item.setStatus("aktív");
		item.setWarranty(warranty);
		return item;
	}

	public void addSelectedProduct() {
		product.getItems().add(createItemVo());
	}

	/**
	 * Elmenti a statisztikát a kimutatáshoz
	 * 
	 * @param ordered
	 *            mennyit rendeltünk
	 */
	private void saveStat(int ordered) {
		WarehouseStatVo whStatVo = new WarehouseStatVo();
		whStatVo.setDayOfOccurence(DateUtil.getStartOfDay(DateUtil.now()));
		whStatVo.setOrdered(Long.valueOf(ordered));
		whStatVo.setShippedAway(0L);
		whStatVo.setGivenToPatients(0L);
		warehouseStatManager.addStat(whStatVo);
	}
}
