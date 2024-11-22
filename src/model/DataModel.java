package model;

/// Line 19, 39, 73 needs addressing

import cashierUI.Cart;
import domain.Item;
import domain.ShopInfo;

import java.util.HashMap;

public class DataModel {
    private HashMap<String, Item> inventory;
    private HashMap<String, Integer> inCart;
    private Cart cart;
    public ShopInfo shop;
    public double subtotal;
    public double grandtotal;
    public double grandtotalWithDiscount;

    public DataModel() {
        inCart = new HashMap<>();
        inventory = new HashMap<>();
        cart = new Cart(this); // might not need to pass model, depends: receipt
    }

    // run this when new JSON is fed
    public void updateStoreInfo() {
        cart.summaryPanel.setVisible(true);

        // things to fire when JSON is read
        cart.taxPercent = (double) shop.getTaxRate() / 100;
        cart.discountPercent = (double) shop.getDiscount() / 100;
        cart.locationInfoLabel.setText("Sales Tax (" + shop + ") :");
        cart.salesTaxLabel.setText(shop.getTaxRate() + "%");
        cart.discountCheck.setText(shop.getDiscount() + "% ");
    }

    public void loadInventory() {
        // load from JSON
    }

    // THIS IS A TEST METHOD CREATED JUST FOR TESTING.
    // !!!! DO NOT USE TO CODE !!!!
    public void testLOADINVENTORY(String id, Item item) {
        inventory.put(id, item);
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

    // run this when table is edited at all
    // a little inefficient: lots of loops -> thoughts?
    public void calculateTotals() {
        // calculate subtotal by looping price column in table
        double calcTotal = 0;
        for (int i = 0; i < cart.tableModel.getRowCount(); i++) {
            calcTotal += Double.parseDouble(cart.tableModel.getValueAt(i, 3).toString());
        }
        subtotal = calcTotal;
        grandtotalWithDiscount = calcTotal * (1 - (shop.getDiscount() / 100)) * (1 + (shop.getTaxRate() / 100));
        grandtotal = calcTotal * (1 + (shop.getTaxRate() / 100));
        cart.checkDiscountSetTotal(); // calculate grand total w/ discount
    }

    // if remove button is clicked, fire this
    // need to check for input mismatch
    public void removeItem(String id) {
        cart.tableModel.removeRow(inCart.remove(id));
        calculateTotals();
    }

    // when cashier clocks out, reset all UIs
    public void resetCart() {
        subtotal = 0;
        grandtotal = 0;
        grandtotalWithDiscount = 0;
        cart.reset();
        inCart.clear();
        inventory.clear();
    }
}
