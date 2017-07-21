
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.primefaces.component.column.Column;
import org.primefaces.component.panelgrid.PanelGrid;

import DataEntities.DAO.FieldDAO;
import DataEntities.DAO.UserDataDAO;
import DataEntities.DAO.Impl.FieldDAOImpl;
import DataEntities.DAO.Impl.UserDataDAOImpl;
import DataEntitys.Field;
import DataEntitys.UserData;
import Observers.ResponseObserver;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;

@ManagedBean(name="dataCollector")
@RequestScoped
public class CollectorDataBean implements Serializable {

	private static final long serialVersionUID = 1L;

	//Declaration vars
	private UserData data;
	private Map<String,Field> fields;
	private PanelGrid panel;
	
	//Constructors
	public CollectorDataBean()
	{
		data = new UserData();
		FieldDAO FDAO = new FieldDAOImpl();
		fields = FDAO.getMapActive();
		panel = new PanelGrid();
		panel.setColumns(1);
		addComp();
	}
	
	//Methods
	public void addComp()
	{
		List<UIComponent> childs = panel.getChildren();
		Column col;
		for(Field field:fields.values())
		{
			col = new Column();
			col.getChildren().add(field.toUIcomponent());
			childs.add(field.toUIcomponent());
		}
	}
	
	public String collect()
	{
		HttpServletRequest req = (HttpServletRequest)FacesContext.getCurrentInstance().getExternalContext().getRequest();
		Enumeration<String> e = req.getParameterNames();
		
		String paramname, paramvalue;
		while(e.hasMoreElements())
		{
			paramname = e.nextElement();
			if(paramname.startsWith("response:in_") && !paramname.endsWith("_focus"))
			{
				paramvalue = req.getParameter(paramname);
				paramname = (paramname.split("_"))[1];
				System.out.println("name:["+paramname+"] value:["+paramvalue+"]");
				data.getData().put(paramname, paramvalue);
			}
		}
		UserDataDAO UDAO = new UserDataDAOImpl(); 
		UDAO.add(data);
		ResponseObserver.getInstanse().addResponse(data);
		
		return "congratulation";
	}
	
	//Getters & Setters------------------------
	public PanelGrid getPanel() {
		return panel;
	}
	public void setPanel(PanelGrid panel) {
		this.panel = panel;
	}
	//-----------------------------------------
}