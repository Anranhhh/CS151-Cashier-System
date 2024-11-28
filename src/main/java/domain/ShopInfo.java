package domain;

public class ShopInfo {
    private String name;
    private String city;
    private String state;
    private double taxRate;
    private double discount;
    private String contact;

    public ShopInfo(String name, String city, String state, double taxRate, double discount, String contact) {
        this.name = name;
        this.city = city;
        this.state = state;
        this.taxRate = taxRate;
        this.discount = discount;
        this.contact = contact;
    }

    public String getName() {
        return this.name;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getDiscount() {
        return discount;
    }

    public String getContact() {
        return contact;
    }

    public String toString() {
        return city + ", " + state;
    }

}
