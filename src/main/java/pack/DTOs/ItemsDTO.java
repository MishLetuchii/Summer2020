package pack.DTOs;

import pack.domain.Categories;
import pack.domain.Position;
import pack.domain.Items;

import java.util.List;

public class ItemsDTO {

    private long id;   // Id предмета в категории
    private long articul; //Артикул
    private String name; //Название позиции в катекогии
    private long count; //Количество предметов в наличии
    private float price; //Цена предмета
    private String description; //Описание предмета
    private byte[] image;
    private String imageString;
    private Categories category; //категория, к которой относится предмет, связь один-ко-многим (категории-предметы)
    private List<Position> positions;

    public ItemsDTO() {
    }

    public ItemsDTO(ItemsDTO obj) {
        this.id = obj.getId();
        this.articul = obj.getArticul();
        this.name = obj.getName();
        this.count = obj.getCount();
        this.price = obj.getPrice();
        this.description = obj.getDescription();
        this.image = obj.getImage();
        this.imageString = obj.getImageString();
        this.category = obj.getCategory();
        this.positions = obj.getPositions();
    }

    public ItemsDTO(Items obj)
    {
        this.id = obj.getId();
        this.articul = obj.getArticul();
        this.name = obj.getName();
        this.count = obj.getCount();
        this.price = obj.getPrice();
        this.description = obj.getDescription();
        this.image = obj.getImage();
        this.imageString = obj.getImageString();
        this.category = obj.getCategory();
        this.positions = obj.getPositions();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public Categories getCategory() {
        return category;
    }

    public void setCategory(Categories category) {
        this.category = category;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }
}
