package models;

import java.util.ArrayList;
import java.util.List;

public class Location {
    private String city;
    private List<Product> productsAvailable = new ArrayList<>();

    public Location(String city) {
        this.city = city;
    }

    public Location() {
        this.city = "";
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void addProduct(Product product) {
        this.productsAvailable.add(product);
    }

    public void removeProduct(Product product) {
        this.productsAvailable.remove(product);
    }

    public List<Product> getProductsAvailable() {
        return productsAvailable;
    }
}
