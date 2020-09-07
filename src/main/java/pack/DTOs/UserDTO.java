package pack.DTOs;

import pack.domain.Basket;

public class UserDTO {
    private long id;
    private String userName;
    private String password;
    private String checkPassword;
    private String roles;
    private boolean isActive;
    private Basket basket;

    public UserDTO() {
    }

    public UserDTO(UserDTO obj) {
        this.id = obj.getId();
        this.userName = obj.getUserName();
        this.password = obj.getPassword();
        this.checkPassword = obj.getCheckPassword();
        this.roles = obj.getRoles();
        this.isActive = obj.isActive();
        this.basket = obj.getBasket();
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

    public String getCheckPassword() {
        return checkPassword;
    }

    public void setCheckPassword(String checkPassword) {
        this.checkPassword = checkPassword;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }
}
