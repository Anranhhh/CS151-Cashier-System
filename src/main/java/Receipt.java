package Receipt;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Receipt {
    // Receipt data fields
    private String storeName;
    private String city;
    private String state;
    private String phone;
    private String cashier;
    private double taxRate;
    private double discountRate;
    private Item[] items;

    // Constructor to initialize receipt data
    public Receipt(String storeName, String city, String state, String phone, String cashier,
                   double taxRate, double discountRate, Item[] items) {
        this.storeName = storeName;
        this.city = city;
        this.state = state;
        this.phone = phone;
        this.cashier = cashier;
        this.taxRate = taxRate;
        this.discountRate = discountRate;
        this.items = items;
    }

    // Method to generate the receipt content
    public String generateReceipt() {
        try {
            // Build Receipt Content
            StringBuilder receipt = new StringBuilder();
            receipt.append("************** RECEIPT **************\n");
            receipt.append(storeName).append("\n");
            receipt.append(city).append(", ").append(state).append("\n");
            receipt.append("Phone: ").append(phone).append("\n\n");

            // Date and Time
            String dateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
            receipt.append("Date/Time: ").append(dateTime).append("\n\n");

            // Items
            double rawTotal = 0;
            receipt.append("Items:\n");
            for (Item item : items) {
                double total = item.getQuantity() * item.getPrice();
                rawTotal += total;

                receipt.append(item.getProductName()).append(" (").append(item.getProductCode()).append(") - ")
                        .append(item.getQuantity()).append(" x $").append(item.getPrice()).append(" = $").append(total).append("\n");
            }
            receipt.append("\n");

            // Totals
            double tax = rawTotal * taxRate;
            double discount = rawTotal * discountRate;
            double grandTotal = rawTotal + tax - discount;

            receipt.append("Raw Total: $").append(String.format("%.2f", rawTotal)).append("\n");
            receipt.append("Tax: $").append(String.format("%.2f", tax)).append("\n");
            receipt.append("Discount: $").append(String.format("%.2f", discount)).append("\n");
            receipt.append("Grand Total: $").append(String.format("%.2f", grandTotal)).append("\n\n");

            // Footer
            receipt.append("Your cashier serving you today is ").append(cashier).append("\n");
            receipt.append("Thank you for shopping with us!\n");

            return receipt.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating receipt.";
        }
    }

    // Nested Item class for purchase items
    public static class Item {
        private String productName;
        private String productCode;
        private int quantity;
        private double price;

        public Item(String productName, String productCode, int quantity, double price) {
            this.productName = productName;
            this.productCode = productCode;
            this.quantity = quantity;
            this.price = price;
        }

        public String getProductName() {
            return productName;
        }

        public String getProductCode() {
            return productCode;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }
    }
}


