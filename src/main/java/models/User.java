package models;
import java.util.ArrayList;
import java.util.List;

public class User {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    protected List<models.Order> orders;

    public User(String firstName, String lastName, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.orders = new ArrayList<>();
    }

    public User() {
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";

        this.orders = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void addOrder(models.Order order) {
        this.orders.add(order);
    }

    public List<models.Order> getOrders() {
        return orders;
    }
}
