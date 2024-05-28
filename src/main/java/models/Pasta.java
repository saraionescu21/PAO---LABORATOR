package models;

public class Pasta extends Product {
    private String size;

    public Pasta(int price, String name, String size) {
        super(price, name);
        this.size = size;
    }

    public Pasta() {
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