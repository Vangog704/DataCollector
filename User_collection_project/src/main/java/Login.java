import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import DataEntities.DAO.Impl.UserDAOImpl;
import DataEntities.DAO.UserDAO;

@ManagedBean(name="logHandler")
@SessionScoped
public class Login implements Serializable {

    private static final long serialVersionUID = 1094801825228386363L;

    private String pwd;
    private String msg;
    private String user;

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    //validate login
    public String validateUsernamePassword() {
        UserDAO UDAO = new UserDAOImpl();
        if (UDAO.validate(user, pwd)) {
            HttpSession session = SessionUtils.getSession();
            session.setAttribute("username", user);
            return "responses";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    FacesContext.getCurrentInstance().getViewRoot().findComponent("login").getClientId(),
                    new FacesMessage("login or password is wrong"));
            return "login";
        }
    }

    //logout event, invalidate session
    public String logout() {
        HttpSession session = SessionUtils.getSession();
        session.invalidate();
        return "login";
    }
}
