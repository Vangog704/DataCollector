package DataEntitys;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.application.Application;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.component.UISelectItems;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;

import org.primefaces.component.calendar.Calendar;
import org.primefaces.component.column.Column;
import org.primefaces.component.inputtext.InputText;
import org.primefaces.component.inputtextarea.InputTextarea;
import org.primefaces.component.message.Message;
import org.primefaces.component.outputlabel.OutputLabel;
import org.primefaces.component.selectbooleanbutton.SelectBooleanButton;
import org.primefaces.component.selectonemenu.SelectOneMenu;
import org.primefaces.component.selectoneradio.SelectOneRadio;

public class Field {

	//Declaration vars   
	private String lable;
	private String type;
	private boolean isActive;
	private boolean required;
	private Set<String> items;
	
	//Constructors
	public Field()
	{
		items = new HashSet<String>();
	};
	
	
	//Methods
	public void addItem(String item)
	{
		items.add(item);
	}
	
	public UIComponent toUIcomponent()
	{
		
		String margin = " margin: 10px; ";
		String width = " width: 300px; ";
		String display = " display: block ;";
		String padding = " padding: 10px; ";
		
		UIComponent tree = new Column();
		UIInput input = null;
		OutputLabel output = new OutputLabel();
		Message message = new Message();
		Application app = FacesContext.getCurrentInstance().getApplication();
			
		//Input creation
		switch(type){
		case "simpletext":
			InputText inputtext = (InputText)app.createComponent("org.primefaces.component.InputText");
			inputtext.setStyle(margin + width + padding);
			input = inputtext;
			break;
		case "multitext":
			InputTextarea textarea = (InputTextarea)app.createComponent("org.primefaces.component.InputTextarea");
			textarea.setAutoResize(true);
			textarea.setRows(3);
			textarea.setCols(35);
			textarea.setStyle(margin + width + padding);
			input = textarea;
			break;
		case "combobox":
			SelectOneMenu selectonemenu = new SelectOneMenu();
			UISelectItems selectitems = new UISelectItems();
			List<SelectItem> itemlist = new ArrayList<SelectItem>();
			for(String item:items)
			{	
				itemlist.add(new SelectItem(item,item));
			}
			selectitems.setValue(itemlist);
			selectonemenu.getChildren().add(selectitems);
			selectonemenu.setStyle(margin + width + padding);
			input = selectonemenu;
			break;
		case "date":
			Calendar calendar = (Calendar)app.createComponent("org.primefaces.component.Calendar");
			calendar.setStyle(margin + width);
				GregorianCalendar currdate = new GregorianCalendar();
			calendar.setValue(currdate.getTime());
			calendar.setMode("inline");
			input = calendar;
			break;
		case "chackbox":
			SelectBooleanButton chackbox = (SelectBooleanButton)app.createComponent("org.primefaces.component.SelectBooleanButton");
			chackbox.setStyle(margin);
			chackbox.setOnLabel("On");
			chackbox.setOffLabel("Off");
			chackbox.setValue(true);
			input = chackbox;
			break;
		case "radio":
			SelectOneRadio SOR = new SelectOneRadio();
			UISelectItems selectradioitems = new UISelectItems();
			List<SelectItem> radioitemlist = new ArrayList<SelectItem>();
			for(String item:items)
			{	
				radioitemlist.add(new SelectItem(item,item));
			}
			selectradioitems.setValue(radioitemlist);
			SOR.getChildren().add(selectradioitems);
			SOR.setValue(items.iterator().next());
			SOR.setStyle(margin + width + padding);
			input = SOR;
			break;
		}
		input.setId("in_"+lable);
		
		message.setFor("in_"+lable);
		message.setStyle("color:red;");
		
		String labletext = lable+":"; 
		if(required)
		{
			labletext += " *";
			input.setRequired(true);
			input.setRequiredMessage("field is empty");
		}
		output.setValue(labletext);	
		output.setStyle(margin + display);
		
		tree.getChildren().add(output);
		tree.getChildren().add(input);
		tree.getChildren().add(message);	
		tree.setId("main_"+lable);
		
		return tree;
	}
	
	public Field copy()
	{	
		Field res = new Field();
		res.setLable(this.getLable());
		res.setType(this.getType());
		res.setItems(this.getItems());
		res.setIsActive(this.getIsActive());
		res.setRequired(this.getRequired());
		return res;
	}
	
	public void copy(Field field)
	{	
		this.setLable(field.getLable());
		this.setType(field.getType());
		this.setIsActive(field.getIsActive());
		this.setRequired(field.getRequired());
		this.setItems(field.getItems());
	}
	
	//Getters & Setters------------------------
	public String getLable() {
		return lable;
	}
	public void setLable(String lable) {
		this.lable = lable;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public Set<String> getItems() {
		return items;
	}
	public void setItems(Set<String> items) {
		this.items = items;
	}
	//-----------------------------------------

}
