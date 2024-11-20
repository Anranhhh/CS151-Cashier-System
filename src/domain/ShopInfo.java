package domain;

public class ShopInfo {
    private String city;
    private String state;
    private int taxRate;
    private int discount;

    public int getTaxRate() {
        return taxRate;
    }

    public int getDiscount() {
        return discount;
    }

    public String toString() {
        return city + ", " + state;
    }
}
