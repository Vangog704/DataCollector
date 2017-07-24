package DataEntitys;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="UserManager")
public class User {

    //Declaration vars
    @Id
    @javax.persistence.Column(name = "login", unique = true, nullable = false)
    private String login;

    @javax.persistence.Column(name="password", nullable = false)
    private String password;

    //Getters & Setters------------------------
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    //-----------------------------------------
}
