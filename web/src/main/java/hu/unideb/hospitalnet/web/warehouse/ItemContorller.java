package hu.unideb.hospitalnet.web.warehouse;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import hu.unideb.hospitalnet.service.ItemManager;
import hu.unideb.hospitalnet.vo.ItemVo;

@ManagedBean(name="itemView")
@ViewScoped
public class ItemContorller implements Serializable {
	private static final long serialVersionUID = 1L;
	private List<ItemVo> selectedItems;
	
	@ManagedProperty("#{itemManager}")
	private ItemManager itemManager;

	public List<ItemVo> getSelectedItems() {
		return selectedItems;
	}

	public void setSelectedItems(List<ItemVo> selectedItems) {
		this.selectedItems = selectedItems;
	}
	
	public ItemManager getItemManager() {
		return itemManager;
	}

	public void setItemManager(ItemManager itemManager) {
		this.itemManager = itemManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void setItems(){
		FacesContext context = FacesContext.getCurrentInstance();
		try {
			itemManager.setItemsStatus(selectedItems);
			context.addMessage(null,new FacesMessage(FacesMessage.SEVERITY_INFO, "Sikeres leselejtezés!", getSuccesMassage()));
		} catch (Exception e) {
			e.getMessage();
			e.printStackTrace();
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Sikertelen leselejtezés!", getErrorMassage()));
		}
		
	}
	
	public String getSuccesMassage(){
		StringBuilder sb = new StringBuilder();
		
		if(selectedItems.size() > 1){
			sb.append("Leselejtezett tételek azonosítója: ");
		}
		else{
			sb.append("Leselejtezett tétel azonosítója: ");
		}
		for(ItemVo ivo : selectedItems){
			sb.append(ivo.getId());
		}
			
		return sb.toString();
	}
	
	public String getErrorMassage(){
		StringBuilder sb = new StringBuilder();
		
		if(selectedItems.size() > 1){
			sb.append("Nem sikerült leselejtezni a következő azonosítójú tételeket: ");
		}
		else{
			sb.append("Nem sikerült leselejtezni a következő azonosítójú tételt: ");
		}
		for(ItemVo ivo : selectedItems){
			sb.append(ivo.getId());
		}
			
		return sb.toString();
	}
	
}
