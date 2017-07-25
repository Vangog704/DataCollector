package DataEntities.DAO.Impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import DataEntities.DAO.FieldDAO;
import DataEntitys.Field;
import unit.HibernateUtil;

public class FieldDAOImpl implements FieldDAO
{
	@SuppressWarnings("unchecked")
	@Override
	public List<Field> getAll() {
		Session ses = null;
		List<Field> fields = new ArrayList<Field>();
		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			
			fields = (List<Field>)ses.createCriteria(Field.class).list(); 
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
		return fields;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Field> getAllActive() {
		Session ses = null;
		List<Field> fields = new ArrayList<Field>();
		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			fields = (List<Field>)ses
					.createCriteria(Field.class)
					.add(Restrictions.sqlRestriction(" isactive is true "))
					.list();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
		return fields;
	}

	@Override
	public boolean add(Field field) {
		Session ses = null;
		boolean res = true;

		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			ses.beginTransaction();
			ses.saveOrUpdate(field);
			ses.getTransaction().commit();
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
	
	public Map<String,Field> getMap()
	{
		Map<String,Field> res = new HashMap<String,Field>();
		
		for(Field it : getAll())
		{
			it.getItems().remove("");
			res.put(it.getLable(), it);
		}
		return res;
	}
	
	public Map<String,Field> getMapActive()
	{
		Map<String,Field> res = new HashMap<String,Field>();
		
		for(Field it : getAllActive())
		{
			it.getItems().remove("");
			res.put(it.getLable(), it);
		}		
		return res;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public Field getById(String id) {
		Session ses = null;
		Field field = null;
		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			Criteria crit = ses.createCriteria(Field.class);
			crit.add(Restrictions.sqlRestriction(" NAME like('"+id+"')"));
			List<Field> fields = crit.list();
			if(!fields.isEmpty()){	field = fields.get(0); }
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
		return field;
	}
	

	@Override
	public void delete(Field field) {

		Session ses = null;
		try {
			ses = HibernateUtil.getSessionFactory().openSession();
			ses.beginTransaction();
			ses.delete(field);
			ses.getTransaction().commit();
		}
		catch(Exception e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "getting feilds fail", JOptionPane.OK_OPTION);
		}
		finally { 
			if (ses != null && ses.isOpen()) { ses.close(); }
		}
	}
	
}
