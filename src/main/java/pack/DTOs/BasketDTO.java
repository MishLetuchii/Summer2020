package pack.DTOs;


import pack.domain.Position;
import pack.domain.User;

import java.util.ArrayList;
import java.util.List;


public class BasketDTO {

    private long id;
    private User user;
    private float totalPrice;
    private List<Position> basket_items;

    public BasketDTO() {
    }

    public BasketDTO(BasketDTO obj) {
        this.id = obj.getId();
        this.totalPrice=obj.getTotalPrice();
        this.user = obj.getUser();
        this.basket_items =obj.getBasket_items();
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Position> getBasket_items() {
        return basket_items;
    }

    public void setBasket_items(List<Position> basket_items) {
        this.basket_items = basket_items;
    }
}
