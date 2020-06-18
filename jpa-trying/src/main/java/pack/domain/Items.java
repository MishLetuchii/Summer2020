package pack.domain;


import javax.persistence.*;

@Entity
public class Items {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;   // Id предмета в категории
    private long articul; //Артикул
    private String name; //Название позиции в катекогии
    private long count; //Количество предметов в наличии
    private float price; //Цена предмета
    private String description; //Описание предмета

    @ManyToOne()
    @JoinColumn(name = "CATEGORY_ID_F", nullable = false)
    private Categories category; //категория, к которой относится предмет, связь один-ко-многим (категории-предметы)

    protected Items() {} //Конструктор, необходим для JPA

    //конструктор, которым инициализируется предмет(позиция в категории)
    public Items(long articul, String name, long count, float price, String description, Categories category) {
        this.articul = articul;
        this.name = name;
        this.count = count;
        this.price = price;
        this.description = description;
        this.category = category;
    }


    public long getArticul() {
        return articul;
    }

    public void setArticul(long articul) {
        this.articul = articul;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format(
                "Items[id=%d, articul='%s', name='%s', count='%d', price='%f', description='%s', category='%s']",
                id, articul, name, count, price, description, category.getName());
    }
}