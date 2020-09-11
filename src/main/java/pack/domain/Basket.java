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
    private float totalPrice;
    //Целиком отказаться от поля не получилось - при создании новых учетных записей требуется значение поля totalPrice
    //Пришлось оставить само поле

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
    if (this.basket_items!= null) {
        for (Position elem : this.basket_items) {
            totalPrice += elem.getTotalPrice();
        }
    }
    return totalPrice;
    }

    //К сожалению, упростить эти методы не вышло
    public void removeItemFromBasket(Items item)
    {

      boolean deleted = false;
        Position el=null;
            for (Position elem:this.basket_items) {         //ищем нужную позицию товара в списке
                if (elem.getThing().getId()==item.getId()) {//позицию определяем, сравнивая Id товара в позиции и Id удаляемого товара
                    elem.decCount();                        //уменьшаем количество товаров в позиции

                    if (elem.getCount() < 1) {              //если количество товаров меньше единицы
                     el=elem;                               //запоминаем этот элемент
                     deleted = true;
                    }
                    break;                                  //выход из цикла, когда нашли нужный товар
                }
            }
            if(deleted==true) this.basket_items.remove(el); //удаляем  элемент, если счетчик меньше нуля когда выйдем из цикла

    }

    public void addItemToBasket(Items item)
    {
        boolean checkItem = false;                          //если списка товаров еще нет- добавляем его
        if(basket_items==null)
            basket_items=new ArrayList<>();

        for (Position elem:this.basket_items) {             //ищем элемент в списке
            if(elem.getThing().getId()==item.getId()){      //Сравнивая id товара в позиции и Id добавляемого товара
                elem.incCount();                            //увеличиваем счетчик в позиции
                checkItem=true;                             //запоминаем, если товар нашелся
                break;                                      //Выход из цикла, когда нашли нужный товар
            }

        }
        if (checkItem==false)                               //если товар не нашелся, то добавляем новый
        {
            basket_items.add(new Position(item,1,this));

        }
    }

}
