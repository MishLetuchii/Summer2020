package pack.domain;

import com.sun.istack.NotNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;


@Entity
public class User implements UserDetails {
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

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private Basket basket;

    public User()
    {
    }

    public User(User user) {
        this.userName = user.getUsername();
        this.password = user.getPassword();
        this.isActive = user.isActive();
        this.roles = user.getRoles();
    }

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

    @Override
    public String getUsername() {

        return userName;
    }

    public void setUsername(String userName) {

        this.userName = userName;
    }

    @Override
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

    public void clearBasket()
    {
        basket.setBasket_items(null);
    }

    public Basket getBasket() {
        if (basket==null) {
            basket= new Basket();
            basket.setUser(this);
        }
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }




    @Override
    public String toString() {
        return String.format(
                "[id='%d', userName='%s', password='%s', roles='%s']",
                id, userName, password, roles);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(this.getRoles().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }


    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return isActive;
    }

}