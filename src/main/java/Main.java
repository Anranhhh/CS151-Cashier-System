package Receipt;

public class Main {
    public static void main(String[] args) {
        // Initialize items
        Receipt.Item[] items = {
            new Receipt.Item("Laptop", "LAP123", 1, 1200.00),
            new Receipt.Item("Mouse", "MSE456", 2, 25.00),
            new Receipt.Item("Keyboard", "KEY789", 1, 45.00)
        };

        // Create Receipt instance
        Receipt receipt = new Receipt(
            "TechMart", "San Francisco", "CA", "555-123-4567", 
            "John Doe", 0.08, 0.05, items
        );

        // Generate and print the receipt
        System.out.println(receipt.generateReceipt());
    }
}

