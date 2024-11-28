package Receipt;

import java.awt.*;
import java.awt.event.*;

public class ReceiptFrame extends Frame {

    private Receipt receipt;

    // Constructor to initialize frame with a Receipt object
    public ReceiptFrame(Receipt receipt) {
        this.receipt = receipt;
        setupFrame();
    }

    // Method to set up the frame
    private void setupFrame() {
        setTitle("Receipt Viewer");
        setSize(400, 700);
        setLayout(new BorderLayout());

        // Main Panel for Receipt Content
        TextArea receiptArea = new TextArea(receipt.generateReceipt());
        receiptArea.setEditable(false);
        receiptArea.setFont(new Font("Courier New", Font.PLAIN, 12));
        receiptArea.setBackground(Color.WHITE);

        add(receiptArea, BorderLayout.CENTER);

        // Close Button
        Button closeButton = new Button("Close");
        closeButton.addActionListener(e -> System.exit(0));
        add(closeButton, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static void main(String[] args) {
        // Sample items for the receipt
        Receipt.Item[] items = {
            new Receipt.Item("Laptop", "LAP123", 1, 1200.00),
            new Receipt.Item("Mouse", "MSE456", 2, 25.00),
            new Receipt.Item("Keyboard", "KEY789", 1, 45.00)
        };

        // Create Receipt object
        Receipt receipt = new Receipt(
            "TechMart", "San Francisco", "CA", "555-123-4567",
            "John Doe", 0.08, 0.05, items
        );

        // Launch Receipt Frame
        new ReceiptFrame(receipt);
    }
}


