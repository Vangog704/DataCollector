import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import DataEntities.DAO.FieldDAO;
import DataEntities.DAO.Impl.FieldDAOImpl;
import DataEntitys.Field;

@ManagedBean(name="fieldsview")
@ViewScoped
public class FieldsBean implements Serializable{
	
	private static final long serialVersionUID = 1L;

	
	private List<Field> fields;
	
	//Constructors
	public FieldsBean()
	{
		FieldDAO FDAO = new FieldDAOImpl();
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String fieldname = req.getParameter("fieldname");
		if(fieldname != null)
		{
			Field field = FDAO.getById(fieldname);
			if(field != null)
			{
				FDAO.delete(field);
			}
		}
		 
		setFields(FDAO.getAll());
	}
	
	//Getters & Setters------------------------
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}
	//-----------------------------------------
}
