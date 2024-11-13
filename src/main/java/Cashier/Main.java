package Cashier;

import Cashier.logic.DataModel;
import Cashier.ui.Cart;

public class Main {
    public static void main(String[] args) {
        DataModel model = new DataModel();
        new Cart(model);
    }
}
