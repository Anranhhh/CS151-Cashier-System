package model;

import cashierUI.Cart;
import domain.DataListener;
import domain.Item;
import domain.ShopInfo;

import java.util.HashMap;

public class DataModel implements DataListener {
    private HashMap<String, Item> inventory;
    private HashMap<String, Integer> inCart;
    private Cart cart;
    public static ShopInfo shop;
    // one more variable to store location/tax/discount info (or read directly from JSON?)

    public DataModel() {
        inCart = new HashMap<>();
        cart = new Cart();
    }

    // run this when new JSON is fed
    public void updateStoreInfo() {
        cart.summaryPanel.setVisible(true);

        // things to fire when JSON is read
        cart.taxPercent = (double) shop.getTaxRate() / 100;
        cart.discountPercent = (double) shop.getDiscount() / 100;
        cart.locationInfoLabel.setText("Sales Tax(" + shop + "):");
        cart.salesTaxLabel.setText(shop.getTaxRate() + "%");
        cart.discountLabel.setText("Discount Available: " + shop.getDiscount() + "%");
    }

    public void loadInventory() {
        // load from JSON
    }

    // when item ID and qty is entered in textField, fire this
    // might need to check for input mismatch
    public void addItemToCart(String id, int qty) {
        Integer row;
        if ((row = inCart.putIfAbsent(id, cart.tableModel.getRowCount())) == null) {
            // create new row in table
            cart.tableModel.addRow(new Object[] {
                    id,
                    inventory.get(id).getName(),
                    qty,
                    String.format("%.2f", (inventory.get(id).getPrice() * qty))
            });
        } else { // update row in table with new values
            int currQty = (int) cart.tableModel.getValueAt(row, 2) + qty;
            cart.tableModel.setValueAt(currQty, row, 2);
            cart.tableModel.setValueAt(inventory.get(id).getPrice() * currQty, row, 3);
        }
        calculateTotals();
    }

    // run this when table is edited
    public void calculateTotals() {
        // calculate subtotal by looping price column in table
        double calTotal = 0;
        for (int i = 0; i < cart.tableModel.getRowCount(); i++) {
            calTotal += (double) cart.tableModel.getValueAt(i, 3);
        }

        cart.subtotal = calTotal;
        cart.checkDiscountSetTotal(); // calculate grand total w/ discount
    }

    // if remove button is clicked, fire this
    // need to check for input mismatch
    public void removeItem(String id) {
        cart.tableModel.removeRow(inCart.remove(id));
    }

    // when cashier clocks out, reset all UIs
    public void resetCart() {
        cart.reset();
        inCart.clear();
        inventory.clear();
    }

    // might not even need a dataListener?
    @Override
    public void dataChanged() {
        System.out.println("Not for use in this class.");
    }
}
