package domain;

public class Item {
    private String code;
    private String name;
    private double price;
    private String description;

    public Item(String code, String name, double price, String description) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public double getPrice() {
        return this.price;
    }

    public String toString() {
        return String.format("%-12s %-20s %-25s %10.2f", code, name, description, price);
    }
}
