package models;

public class Burger extends Product {
    private String size;

    public Burger(int price, String name, String size) {
        super(price, name);
        this.size = size;
    }

    public Burger() {
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