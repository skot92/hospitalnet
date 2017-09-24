package hu.unideb.hospitalnet.web.warehouse;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import hu.unideb.hospitalnet.service.ProductManager;
import hu.unideb.hospitalnet.vo.ProductVo;

@ManagedBean(name = "productVoConverter")
@RequestScoped
public class ProductVoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;
	
	@ManagedProperty("#{productManager}")
	private ProductManager productManager;

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		if (arg2 != null && arg2.trim().length() > 0) {
			try {
				return productManager.getProductById(Long.valueOf(arg2));
			} catch (NumberFormatException e) {
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage(FacesMessage.SEVERITY_ERROR, "Hiba!", "Nincs ilyen termék!"));
			}
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object arg2) {
		return arg2 == null ? null : String.valueOf(((ProductVo) arg2).getId());
	}

	public ProductManager getProductManager() {
		return productManager;
	}

	public void setProductManager(ProductManager productManager) {
		this.productManager = productManager;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
