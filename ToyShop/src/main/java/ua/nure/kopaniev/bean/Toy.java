package ua.nure.kopaniev.bean;

import java.util.List;

/**
 * Created by Vladyslav_Kopaniev on 11/11/2015.
 */
public class Toy {
    private int id;
    private String name;
    private float price;
    private float weight;
    private String image;
    private String category;
    private String brand;
    private List<Option> options;

    public Toy(int id, String name, float price, float weight,
               String image, String category, String brand, List<Option> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.weight = weight;
        this.image = image;
        this.category = category;
        this.brand = brand;
        this.options = options;
    }

    public List<Option> getOptions() {
        return options;
    }

    public void setOptions(List<Option> options) {
        this.options = options;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

    public float getWeight() {
        return weight;
    }

    public String getImage() {
        return image;
    }

    public String getCategory() {
        return category;
    }

    public String getBrand() {
        return brand;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Toy toy = (Toy) o;

        if (id != toy.id) return false;
        if (Float.compare(toy.price, price) != 0) return false;
        if (Float.compare(toy.weight, weight) != 0) return false;
        if (name != null ? !name.equals(toy.name) : toy.name != null) return false;
        if (image != null ? !image.equals(toy.image) : toy.image != null) return false;
        if (category != null ? !category.equals(toy.category) : toy.category != null) return false;
        return (brand != null ? brand.equals(toy.brand) : toy.brand == null);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (price != +0.0f ? Float.floatToIntBits(price) : 0);
        result = 31 * result + (weight != +0.0f ? Float.floatToIntBits(weight) : 0);
        result = 31 * result + (image != null ? image.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (brand != null ? brand.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Toy{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                ", brand='" + brand + '\'' +
                ", options=" + options +
                '}';
    }
}
