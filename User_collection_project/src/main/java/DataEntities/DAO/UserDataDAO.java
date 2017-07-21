package DataEntities.DAO;

import java.util.List;

import DataEntitys.UserData;

public interface UserDataDAO {
	public List<UserData> getAll();
	public boolean add(UserData arg0);
}
