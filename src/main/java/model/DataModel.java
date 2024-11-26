package model;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import cashierUI.Cart;
import domain.Item;
import domain.ShopInfo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;


public class DataModel {
    private Map<String, Item> inventory;
    private final Map<String, Integer> inCart;
    private final Cart cart;
    public ShopInfo shop;
    public double subtotal;
    public double grandtotal;
    public double grandtotalWithDiscount;

    public DataModel() {
        inCart = new LinkedHashMap<>();
        inventory = new HashMap<>();
        cart = new Cart(this);

    }

    // run this when new JSON is fed
    public void updateJSON() {
        resetCart();

        Gson gson = new Gson();
        try (BufferedReader br = new BufferedReader(new FileReader("Store.json"))) {
            JsonObject jsonData = gson.fromJson(br, JsonObject.class);
            shop = gson.fromJson(jsonData.get("shop"), ShopInfo.class);
            Type itemMapType = new TypeToken<Map<String, Item>>(){}.getType();
            inventory = gson.fromJson(jsonData.get("inventory"), itemMapType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        cart.locationInfoLabel.setText("Sales Tax (" + shop + ") :");
        cart.salesTaxLabel.setText(shop.getTaxRate() + "%");
        cart.discountCheck.setText(shop.getDiscount() + "% ");
    }

    // when item ID and amount is entered in textField, fire this
    // might need to check for input mismatch
    public void addItemToCart(String id, int amount) {
        Integer qty;
        if ((qty = inCart.putIfAbsent(id, amount)) == null) {
            // create new row in table
            cart.tableModel.addRow(new Object[] {
                    id,
                    inventory.get(id).getName(),
                    amount,
                    String.format("%.2f", (inventory.get(id).getPrice() * amount))
            });
        } else { // update row in table with new values
            int newQty = qty + amount;
            inCart.put(id, newQty);
            int row = new ArrayList<>(inCart.keySet()).indexOf(id);
            cart.tableModel.setValueAt(newQty, row, 2);
            cart.tableModel.setValueAt(String.format("%.2f", inventory.get(id).getPrice() * newQty), row, 3);
        }
        calculateTotals();
    }

    // run this when table is edited at all
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
    // might not be efficient due to keySet loop
    public void removeItem(String id) {
        cart.tableModel.removeRow(new ArrayList<>(inCart.keySet()).indexOf(id));
        inCart.remove(id);
        calculateTotals();
    }

    // when cashier clocks out, reset all UIs
    // when different JSON initialized
    public void resetCart() {
        shop = null;
        inventory.clear();
        clearCart();
    }

    public void clearCart() {
        subtotal = 0;
        grandtotal = 0;
        grandtotalWithDiscount = 0;
        cart.reset();
        inCart.clear();
    }
}
