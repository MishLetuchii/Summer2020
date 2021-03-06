package pack.domain;

import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.List;

@Entity
public class Categories {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;//Id категории, генерируем автоматически
    private String name;//название категории
    private String description;//описание категории
    @Lob
    private byte[] image;
    @Transient
    private String imageString;
    //@JsonIgnore
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL,
            fetch = FetchType.LAZY, orphanRemoval = true)//все операции изменения в коллекции=изменения в бд
    //выборка из бд только при обращении к элементу коллекции, удаление в коллекции=удаление в бд
    private List<Items> items;//предметы, входящие в категорию,связь один-ко-многим (Категория-предметы)

    public Categories() {} //конструктор для JPA

    public Categories(String name, String description) {//конструктор для сохранения экземпляра в бд
        this.name = name;
        this.description = description;
    }

    public void addItem (Items item)
    {
        this.items.add(item);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
    }
     
    public byte[] getImage() {  return image; }

    public void setImage(byte[] image) {  this.image = image;  }

    public String getImageString() {  return imageString;  }

    public void setImageString(String imageString) { this.imageString = imageString;  }


    @Override
    public String toString() {
        return String.format(
                "Catalog[id=%d, name='%s', description='%s']",
                id, name, description);
    }
}