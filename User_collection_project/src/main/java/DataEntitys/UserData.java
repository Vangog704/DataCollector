package DataEntitys;

import java.util.HashMap;
import java.util.Map;

import org.primefaces.json.JSONObject;

public class UserData {
	
	//Declaration vars
	private Long id;
	private Map<String,String> data;
	
	//Constructors
	public UserData()
	{
		data = new HashMap<String,String>();
	}
	
	//Methods
	public JSONObject toJSON()
	{
		return new JSONObject(data);
	}
	
	//Getters & Setters------------------------
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Map<String, String> getData() {
		return data;
	}
	public void setData(Map<String, String> data) {
		this.data = data;
	}
	//----------------------------------------
}
