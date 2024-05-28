package models;

public class Pizza extends Product {
    private String size;

    public Pizza(int price, String name, String size) {
        super(price, name);
        this.size = size;
    }

    public Pizza() {
        super();
        this.size = null;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

}