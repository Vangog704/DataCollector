package DataEntitys;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.sql.Select;
import org.primefaces.json.JSONObject;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name="UserData")
public class UserData {
	
	//Declaration vars
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@javax.persistence.Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ElementCollection(fetch = FetchType.EAGER)
	@Fetch(FetchMode.SELECT)
	@MapKeyColumn(name="key")
	@Column(name="value")
	@CollectionTable(name="USERDATA_DATA", joinColumns=@JoinColumn(name="id"))
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
