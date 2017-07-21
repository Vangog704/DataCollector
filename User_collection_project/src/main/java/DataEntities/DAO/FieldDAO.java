
package DataEntities.DAO;
import java.util.List;
import java.util.Map;

import DataEntitys.Field;

public interface FieldDAO 
{
	public List<Field> getAll();
	public List<Field> getAllActive();
	public boolean add(Field field);
	public Field getById(String id);
	public void delete(Field field);
	public Map<String,Field> getMapActive();
	public Map<String,Field> getMap();
}
