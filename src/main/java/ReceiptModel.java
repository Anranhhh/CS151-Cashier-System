package Receipt;

public class ReceiptModel {
    private String storeName;
    private String city;
    private String state;
    private String phone;
    private String cashier;
    private JSONArray items;
    private double taxRate;
    private double discountRate;

    // Constructor
    public ReceiptModel(String storeName, String city, String state, String phone, String cashier, 
                        JSONArray items, double taxRate, double discountRate) {
        this.storeName = storeName;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.cashier = cashier;
        this.items = items;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
    }

    // Getters
    public String getStoreName() {
        return storeName;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPhone() {
        return phone;
    }

    public String getCashier() {
        return cashier;
    }

    public JSONArray getItems() {
        return items;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }
}
