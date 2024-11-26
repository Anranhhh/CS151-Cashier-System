package domain;

public class ShopInfo {
    private String city;
    private String state;
    private double taxRate;
    private double discount;
    private String contact;

    public ShopInfo(String city, String state, double taxRate, double discount, String contact) {
        this.city = city;
        this.state = state;
        this.taxRate = taxRate;
        this.discount = discount;
        this.contact = contact;
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
