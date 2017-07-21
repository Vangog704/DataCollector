import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.primefaces.component.column.Column;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.panelgrid.PanelGrid;
import org.primefaces.component.row.Row;

import DataEntities.DAO.FieldDAO;
import DataEntities.DAO.UserDataDAO;
import DataEntities.DAO.Impl.FieldDAOImpl;
import DataEntities.DAO.Impl.UserDataDAOImpl;
import DataEntitys.UserData;

@ManagedBean(name="response")
@ViewScoped
public class ResponsesViewBean implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	//Declaration vars
	private List<UserData> data;
	private Set<String> fields;
	private PanelGrid table;
	
	//Constructors
	public ResponsesViewBean()
	{
		setTable(new PanelGrid());
		
		FieldDAO FDAO = new FieldDAOImpl();
		UserDataDAO UDAO = new UserDataDAOImpl();
		
		fields = FDAO.getMapActive().keySet();
		
		setData(UDAO.getAll());
		fillTable();
	}
	
	//Methods
	private void fillTable()
	{
		String str;
		Row row = new Row();
		Column col;
		OutputLabel out;
		for(String field : fields) {
			col = new Column();	
			col.setStyleClass("ui-widget-header");
			out = new OutputLabel();
			out.setId(field);
			out.setValue(field);
			col.getChildren().add(out);
			row.getChildren().add(col);
			row.setId("headerrow");
		}
		table.getChildren().add(row);
		
		for(UserData it : data) {

			row = new Row();
			for(String field : fields) {
				out = new OutputLabel();
				col = new Column();
				
				str = it.getData().get(field);
				if(str != null)	out.setValue(str);
				else 			out.setValue("N/A");
				
				col.getChildren().add(out);
				row.getChildren().add(col);
			}
			table.getChildren().add(row);
			table.setId("maintable");
		}
	}
	
	//Getters & Setters------------------------
	public PanelGrid getTable() {
		return table;
	}
	public void setTable(PanelGrid table) {
		this.table = table;
	}
	public List<UserData> getData() {
		return data;
	}
	public void setData(List<UserData> data) {
		this.data = data;
	}
	//---------------------------------------
}
