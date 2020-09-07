package pack.DTOs;

import pack.domain.Items;

import java.util.List;

public class CategoriesDTO {

    private long id;//Id категории
    private String name;//название категории
    private String description;//описание категории
    private byte[] image;//хранимая картинка
    private String imageString;//картинка ввиде строки
    private List<Items> items;//Список товаров в категории

    public CategoriesDTO() {
    }

    public CategoriesDTO(CategoriesDTO obj)  {
        this.id = obj.getId();
        this.name = obj.getName();
        this.description = obj.getDescription();
        this.image = obj.getImage();
        this.imageString = obj.getImageString();
        this.items = obj.getItems();
    }

    public List<Items> getItems() {
        return items;
    }

    public void setItems(List<Items> items) {
        this.items = items;
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getImageString() {
        return imageString;
    }

    public void setImageString(String imageString) {
        this.imageString = imageString;
    }
}
