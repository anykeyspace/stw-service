package accounts;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserProfile implements Serializable { // Serializable important to Hibernate!

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true, updatable = false)
    private String login;

    @Column(name = "password")
    @Basic(optional = false)
    private String pass;

    @Column(name = "email")
    private String email;

    // for Hibernate
    public UserProfile() {
    }

    public UserProfile(String login, String pass, String email) {
        this.login = login;
        this.pass = pass;
        this.email = email;
    }

    public UserProfile(String login) {
        this.login = login;
        this.pass = login;
        this.email = login;
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User Profile: { " +
                "id = " + id +
                ", login = " + login +
                " }";
    }
}
