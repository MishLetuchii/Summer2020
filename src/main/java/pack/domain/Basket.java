package pack.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne()
    @JoinColumn(name = "USER_ID_F")
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL,
            fetch = FetchType.EAGER)
    private List<Position> basket_items;
    //private float totalPrice;

    public Basket() {
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

    public float getTotalPrice() {
    float totalPrice=0;
        for (Position elem:this.basket_items) {
            totalPrice+=elem.getTotalPrice();
        }
    return totalPrice;
    }


    public void removeItemFromBasket(Items item)
    {

      boolean deleted = false;
        Position el=null;
            for (Position elem:this.basket_items) { //ищем нужную позицию товара в списке
                if (elem.getThing().getId()==item.getId()) {
                    elem.decCount();                //уменьшаем количество товаров в позиции

                    if (elem.getCount() < 1) {  //если количество товаров меньше единицы
                     el=elem;                   //запоминаем этот элемент
                     deleted = true;
                    }
                }
            }
            if(deleted==true) this.basket_items.remove(el);//удаляем этот элемент, когда выйдем из цикла

    }

    public void addItemToBasket(Items item)
    {
        boolean checkItem = false;
        if(basket_items==null)
            basket_items=new ArrayList<>();

        for (Position elem:this.basket_items) {
            if(elem.getThing().getId()==item.getId()){
                elem.incCount();
                checkItem=true;
            }
        }
        if (checkItem==false)
        {
            basket_items.add(new Position(item,1,this));

        }
    }

}
