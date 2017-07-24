package DataEntities.DAO.Impl;

import DataEntities.DAO.UserDAO;
import DataEntitys.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import unit.HibernateUtil;

import javax.swing.*;

public class UserDAOImpl implements UserDAO {

    @Override
    public boolean validate(String login, String pass) {
        Session ses = null;
        boolean res = true;
        try {
            ses = HibernateUtil.getSessionFactory().openSession();
            Criteria crit = ses.createCriteria(User.class);
            crit.add(Restrictions.sqlRestriction(" login like('"+login+"') and password like('"+pass+"') "));
            if(crit.list().isEmpty())
                res = false;
        }
        catch(Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "validate get failed", JOptionPane.OK_OPTION);
        }
        finally {
            if (ses != null && ses.isOpen()) { ses.close(); }
        }
        return res;
    }
}
