import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.servlet.http.HttpServletRequest;

import DataEntities.DAO.FieldDAO;
import DataEntities.DAO.Impl.FieldDAOImpl;
import DataEntitys.Field;

@ManagedBean(name="fieldRedact")
@ViewScoped
public class FieldRedactBean extends Field implements Serializable{

	private static final long serialVersionUID = 1L;
	
	//Declaration vars
	private String itemListRendered; 

	//Constructors
	public FieldRedactBean()
	{
		super();
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String fieldname = req.getParameter("fieldname");
		if(fieldname != null)
		{
			FieldDAO FDAO = new FieldDAOImpl();
			Field field = FDAO.getById(fieldname);
			if(field != null)
			{
				System.out.println("field copy");
				this.copy(field);
			}
		}
		else
		{
			setType("simpletext");
		}
		
		this.setIsActive(true);
	}
	
	//Methods
	public void changeComponent(ValueChangeEvent event){}
	
	@Override
	public void setType(String type)
	{
		super.setType(type);
		switch(type)
		{
			case "combobox": 		setItemListRendered("true"); 	break;
			case "radio": 			setItemListRendered("true"); 	break;
			default: 				setItemListRendered("false"); 	break;
		}
	}
	
	public String saveField()
	{
		if(getItemListRendered().equals("true") && this.getItems().size() < 2){
			FacesContext.getCurrentInstance().addMessage(
						FacesContext.getCurrentInstance().getViewRoot().findComponent("field:options").getClientId(), 
						new FacesMessage("В списке недостаточно опций"));	
			return null;
		}
		System.out.println(getItems());
		Field field  = this.copy();
		FieldDAOImpl FDAO = new FieldDAOImpl();
		FDAO.add(field);
		
		return "fields";
	} 

	//Getters & Setters------------------------
	public Field getField()
	{
		return (Field)this;
	}
	public String getItemListRendered() {
		return itemListRendered;
	}
	public void setItemListRendered(String itemListRendered) {
		this.itemListRendered = itemListRendered;
	}
	public String getOption() { 
		return null;
	}
	public void setOption(String option) {
		addItem(option);
	}
	//-----------------------------------------
}
