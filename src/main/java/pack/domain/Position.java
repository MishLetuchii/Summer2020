package pack.domain;

import javax.persistence.*;

@Entity
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    long id;

    @ManyToOne()
    @JoinColumn(name = "BASKET_ID_F", nullable = false)
    private Basket basket;

    @ManyToOne
    @JoinColumn(name = "THING_ID_F")
    private Items thing;
    private long count;
    private float totalPrice;

    public Position(){}

    public Position(Items thing, long count,Basket basket) {
        this.basket=basket;
        this.thing = thing;
        this.count=count;
        this.updTotalPrice();
    }
    private void updTotalPrice(){
       this.totalPrice=this.thing.getPrice()*this.count;
    }

    public void incCount()
    {
        this.count++;
        this.updTotalPrice();
    }

    public void decCount(){
        this.count--;
        this.updTotalPrice();
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (obj instanceof Items)
        {
            Items item = (Items) obj;
            return item.getId()==this.id;
        }
        if (!(obj instanceof Position)) return false;
        Position pos = (Position) obj;
        return pos.id == this.id;
    }
}
