package models;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int nextId = 1;
    private final int id;
    private User user;
    private int totalPrice;
    private List<Product> products;
    private Location location;

    public Order(User user, Location location) {
        this.id = nextId++;
        this.user = user;
        this.products = new ArrayList<>();
        this.totalPrice = 0;
        this.location = location;
        user.addOrder(this);
    }

    public Order() {
        this.totalPrice = 0;
        this.user = new User();
        this.products = new ArrayList<>();
        this.id = 0;
        this.location = new Location();
    }

    public void addProduct(Product product) {
        products.add(product);
        updateTotalPrice();
    }

    public void removeProduct(Product product) {
        products.remove(product);
        updateTotalPrice();
    }

    public void updateTotalPrice() {
        totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice();
        }
    }

    public void displayOrderDetailsWithClient() {
        System.out.println("Order ID: " + id);
        System.out.println("User Name: " + user.getFirstName() + " " + user.getLastName());
        System.out.println("Total Price: " + totalPrice);
        System.out.println("Products in the order:");
        for (Product product : products) {
            System.out.println("- " + product.getName());
        }
    }

    public int getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Location getLocation() {
        return location;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public List<Product> getProducts() {
        return new ArrayList<>(products);
    }


}
