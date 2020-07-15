package pack.domain;

import com.sun.istack.NotNull;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(unique = true)
    @NotNull
    private String userName;
    @NotNull
    private String password;
    @Transient
    private String checkPassword;
    @NotNull
    private String roles;
    @NotNull
    private boolean isActive;

    public User() { }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {

        this.id = id;
    }

    public String getUserName() {

        return userName;
    }

    public void setUserName(String userName) {

        this.userName = userName;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRoles() {

        return roles;
    }

    public void setRoles(String roles) {

        this.roles = roles;
    }

    public String getCheckPassword() {

        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {

        this.checkPassword = checkPassword;
    }


    @Override
    public String toString() {
        return String.format(
                "[id='%d', userName='%s', password='%s', roles='%s']",
                id, userName, password, roles);
    }

}