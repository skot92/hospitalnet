package hu.unideb.hospitalnet.web.warehouse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

import hu.unideb.hospitalnet.service.ItemManager;
import hu.unideb.hospitalnet.service.WarehouseStatManager;
import hu.unideb.hospitalnet.service.stats.vo.WarehouseStatVo;
import hu.unideb.hospitalnet.service.util.DateUtil;
import hu.unideb.hospitalnet.vo.ItemVo;

@ViewScoped
@ManagedBean(name = "inactiveItemView")
public class InactiveItemController implements Serializable {

	private static final long serialVersionUID = 1L;

	private ItemVo selectedItem;

	private List<ItemVo> selectedItems;
	
	private ThrowedItem selectedTrhowedItem;
	
	private List<ThrowedItem> selectedTrhowedItems;

	@ManagedProperty("#{itemManager}")
	private ItemManager itemManager;

	@ManagedProperty("#{lazyItemModel}")
	private LazyDataModel<ItemVo> lazyItemModel;
	
	@ManagedProperty("#{lazyThrowedItemModel}")
	private LazyDataModel<ThrowedItem> lazyThrowedItemModel;

	@ManagedProperty("#{warehouseStatManager}")
	private WarehouseStatManager warehouseStatManager;

	public LazyDataModel<ItemVo> getLazyItemModel() {
		return lazyItemModel;
	}

	public void setLazyItemModel(LazyDataModel<ItemVo> lazyItemModel) {
		this.lazyItemModel = lazyItemModel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public void onRowSelect(SelectEvent e) {
		selectedItem = (ItemVo) e.getObject();
	}

	public ItemVo getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(ItemVo selectedItem) {
		this.selectedItem = selectedItem;
	}

	public List<ItemVo> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<ItemVo> selectedItems) {
		this.selectedItems = selectedItems;
	}

	public WarehouseStatManager getWarehouseStatManager() {
		return warehouseStatManager;
	}

	public void setWarehouseStatManager(WarehouseStatManager warehouseStatManager) {
		this.warehouseStatManager = warehouseStatManager;
	}

	public void setItemsToInactive() {
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			itemManager.setItemsStatus(selectedItems, "elszállítva");
			saveStat(selectedItems.stream().mapToInt(itm -> itm.getNumberOfUnitNow()).sum());
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres elszállítás!", getSuccesMessage()));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			context.addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikeres elszállítás!", getErrorMessage()));
		}
	}

	private void saveStat(int shippedAway) {
		WarehouseStatVo whStatVo = new WarehouseStatVo();
		whStatVo.setDayOfOccurence(DateUtil.getStartOfDay(DateUtil.now()));
		whStatVo.setOrdered(0L);
		whStatVo.setShippedAway(Long.valueOf(shippedAway));
		whStatVo.setGivenToPatients(0L);
		warehouseStatManager.addStat(whStatVo);
	}
	
	public void removeItems(){
		FacesContext context = FacesContext.getCurrentInstance();
	
		List<ItemVo> removedItems = new ArrayList<>();
		for(ThrowedItem ti : selectedTrhowedItems){
			ItemVo ivo = new ItemVo();
			ivo.setId(ti.getId());
			ivo.setNumberOfUnit(ti.getNumberOfUnit());
			ivo.setNumberOfUnitNow(ti.getNumberOfUnitNow());
			ivo.setStatus("leselejtezett");
			ivo.setWarranty(ti.getWarranty());
			removedItems.add(ivo);
		}
		
		try {
			itemManager.setItemsStatus(removedItems, "elszállítva");
			saveStat(removedItems.stream().mapToInt(itm -> itm.getNumberOfUnitNow()).sum());
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres elszállítás!", getThrowedSuccessMessage()));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen elszállítás!", getThrowedErrorMessage()));
		}
	}
	
	public String getThrowedSuccessMessage(){
		StringBuilder sb = new StringBuilder();
		if(selectedTrhowedItems.size() > 1){
			sb.append("Elszállított tételek azonosítója: ");
		}
		else{
			sb.append("Elszállított tétel azonosítója: ");
		}
		for(ThrowedItem ti : selectedTrhowedItems){
			sb.append(ti.getId() + ", ");
		}
			
		return sb.toString().substring(0, sb.length()-2);
	}
	
	public String getThrowedErrorMessage(){
		StringBuilder sb = new StringBuilder();
		
		if(selectedTrhowedItems.size() > 1){
			sb.append("Nem sikeült leselejtezni a következő azonosítójú tételeket: ");
		}
		else{
			sb.append("Nem sikeült leselejtezni a következő azonosítójú tételt: ");
		}
		for(ThrowedItem ti : selectedTrhowedItems){
			sb.append(ti.getId() + ", ");
		}
			
		return sb.toString().substring(0, sb.length()-2);
	}
	
	public String getSuccesMessage() {
		StringBuilder sb = new StringBuilder();

		if (selectedItems.size() > 1) {
			sb.append("Elszállított tételek azonosítója: ");
		} else {
			sb.append("Elszállított tétel azonosítója: ");
		}
		for (ItemVo ivo : selectedItems) {
			sb.append(ivo.getId());
		}

		return sb.toString();
	}

	public String getErrorMessage() {
		StringBuilder sb = new StringBuilder();

		if (selectedItems.size() > 1) {
			sb.append("Nem sikerült leselejtezni a következő azonosítójú tételeket: ");
		} else {
			sb.append("Nem sikerült leselejtezni a következő azonosítójú tételt: ");
		}
		for (ItemVo ivo : selectedItems) {
			sb.append(ivo.getId());
		}

		return sb.toString();
	}

	public List<ThrowedItem> getSelectedTrhowedItems() {
		return selectedTrhowedItems;
	}

	public void setSelectedTrhowedItems(List<ThrowedItem> selectedTrhowedItems) {
		this.selectedTrhowedItems = selectedTrhowedItems;
	}

	public ThrowedItem getSelectedTrhowedItem() {
		return selectedTrhowedItem;
	}

	public void setSelectedTrhowedItem(ThrowedItem selectedTrhowedItem) {
		this.selectedTrhowedItem = selectedTrhowedItem;
	}

	public LazyDataModel<ThrowedItem> getLazyThrowedItemModel() {
		return lazyThrowedItemModel;
	}

	public void setLazyThrowedItemModel(LazyDataModel<ThrowedItem> lazyThrowedItemModel) {
		this.lazyThrowedItemModel = lazyThrowedItemModel;
	}
}
