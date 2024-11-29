package cashierUI;

import domain.Item;
import model.DataModel;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReceiptFrame extends Frame {
    private DataModel model;
    private boolean discountActivated;

    public ReceiptFrame(DataModel model, boolean discountActivated) {
        this.model = model;
        this.discountActivated = discountActivated;
        setupFrame();
    }

    // Method to set up the frame
    private void setupFrame() {
        setTitle("Receipt Viewer");
        setSize(400, 700);
        setLayout(new BorderLayout());

        // Main Panel for Receipt Content
        TextArea receiptArea = new TextArea(generateReceipt());
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        receiptArea.setBackground(Color.WHITE);

        add(receiptArea, BorderLayout.CENTER);

        // Close Button
        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> dispose());
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    private String generateReceipt() {
        try {
            // Build Receipt Content
            StringBuilder receipt = new StringBuilder();
            receipt.append("************** RECEIPT **************\n");
            receipt.append(model.shop.getName()).append("\n");
            receipt.append(model.shop).append("\n");
            receipt.append("Phone: ").append(model.shop.getContact()).append("\n\n");

            // Date and Time
            String dateTime = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(new Date());
            receipt.append("Date/Time: ").append(dateTime).append("\n\n");

            // Items
            double rawTotal = 0;
            receipt.append("Items:\n");
            for (String id : model.inCart.keySet()) {
                Item item = model.inventory.get(id);
                int qty = model.inCart.get(id);
                double total = item.getPrice() * qty;

                receipt.append(item.getName()).append(" (").append(item.getCode()).append(") - ")
                        .append(qty).append(" x $").append(item.getPrice()).append(" = $").append(String.format("%.2f", total)).append("\n");
            }
            receipt.append("\n");

            double discount = 0;
            double tax = 0;
            double grandTotal = 0;
            // Totals
            if (discountActivated) {
                discount = model.subtotal * (model.shop.getDiscount() / 100);
                tax = (model.subtotal - discount) * (model.shop.getTaxRate() / 100);
                grandTotal = model.grandtotalWithDiscount;
            } else {
                tax = model.subtotal * (model.shop.getTaxRate() / 100);
                grandTotal = model.grandtotal;
            }

            receipt.append("Sub Total: $").append(String.format("%.2f", model.subtotal)).append("\n");
            receipt.append("Discount: $").append(String.format("%.2f", discount)).append("\n");
            receipt.append("Tax: $").append(String.format("%.2f", tax)).append("\n");
            receipt.append("Grand Total: $").append(String.format("%.2f", grandTotal)).append("\n\n");

            // Footer
            receipt.append("Your cashier serving you today is ").append(model.getCashier()).append("\n");
            receipt.append("Thank you for shopping with us!\n");

            return receipt.toString();

        } catch (Exception e) {
            e.printStackTrace();
            return "Error generating receipt.";
        }
    }
}


