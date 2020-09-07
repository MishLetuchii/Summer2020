package pack.DTOs;

import pack.domain.Basket;
import pack.domain.Items;



public class PositionDTO {

    long id;
    private Basket basket;
    private Items thing;
    private long count;
    private float totalPrice;

    public PositionDTO() {
    }

    public PositionDTO(PositionDTO obj) {
        this.id = obj.getId();
        this.basket = obj.getBasket();
        this.thing = obj.getThing();
        this.count = obj.getCount();
        this.totalPrice = obj.getTotalPrice();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Basket getBasket() {
        return basket;
    }

    public void setBasket(Basket basket) {
        this.basket = basket;
    }

    public Items getThing() {
        return thing;
    }

    public void setThing(Items thing) {
        this.thing = thing;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
