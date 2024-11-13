package Cashier.logic;

import Cashier.resources.DataListener;
import Cashier.domain.Item;

import java.util.HashMap;

public class DataModel implements DataListener {
    public HashMap<String, Item> inventory;
    public HashMap<String, Integer> inCart;

    public DataModel() {
        inCart = new HashMap<>();
    }

    public void loadInventory() {

    }

    public void addQuantity(String id, int qty) {
        Integer val;
        if ((val = inCart.putIfAbsent(id, qty)) != null) {
            inCart.put(id, val + qty);
        }
    }

    public void removeItem(String id) {
        inCart.remove(id);
    }

    @Override
    public void dataChanged() {
        System.out.println("Not for use in this class.");
    }
}
