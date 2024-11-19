package model;

import cashierUI.Cart;
import domain.DataListener;
import domain.Item;

import java.util.HashMap;

public class DataModel implements DataListener {
    public HashMap<String, Item> inventory;
    public HashMap<String, Integer> inCart;
    private Cart cart;

    public DataModel() {
        inCart = new HashMap<>();
        cart = new Cart();
    }

    public void loadInventory() {
        // load from JSON
    }

    // when item ID and qty is entered in textField, fire this
    // need to check for input mismatch
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
    }

    // if remove button is clicked, fire this
    // need to check for input mismatch
    public void removeItem(String id) {
        cart.tableModel.removeRow(inCart.remove(id));
    }

    // might not even need a dataListener?
    @Override
    public void dataChanged() {
        System.out.println("Not for use in this class.");
    }
}
