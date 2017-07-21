package DataEntities.DAO.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.hibernate.Session;

import DataEntities.DAO.UserDataDAO;
import DataEntitys.UserData;
import unit.HibernateUtil;

public class UserDataDAOImpl implements UserDataDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<UserData> getAll() {
		Session ses = null;
		List<UserData> data = new ArrayList<UserData>();
		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			data = (List<UserData>)ses.createCriteria(UserData.class).list();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
		return data;
	}

	@Override
	public boolean add(UserData data) {
		Session ses = null;
		boolean res = true;
		ses = HibernateUtil.getSessionFactory().openSession();
		ses.beginTransaction();
		ses.save(data);
		ses.getTransaction().commit();
		try {

		}
		catch(Exception e) {
			System.out.println("Exeption");
			res = false;
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
		return res;
	}

}
