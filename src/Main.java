import domain.Item;
import domain.ShopInfo;
import model.DataModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DataModel model = new DataModel(); // CART is in here
        cartTest(model);

    }


    // TEST FOR CART: FRAME2
    public static void cartTest(DataModel model) {
        Item teddy = new Item("Teddy Bear", 5.50); // ID 0001
        Item candy = new Item("Candy", 1.5); // ID 0002
        Item iphone = new Item("iPhone", 1119.99); // ID 0003
        Item tv = new Item("TV", 3581.26); // ID 0004
        ShopInfo shopInfo = new ShopInfo("San Jose", "CA", 9.38, 10, "(408) 924-1000");
        model.shop = shopInfo;
        model.updateStoreInfo();
        model.testLOADINVENTORY("0001", teddy); // THIS IS A TEST METHOD CREATED JUST FOR TESTING
        model.testLOADINVENTORY("0002", candy);
        model.testLOADINVENTORY("0003", iphone);
        model.testLOADINVENTORY("0004", tv);

        JFrame testFrame = new JFrame("Test Frame");
        JButton button1 = new JButton("Add 0001");
        button1.addActionListener(e -> {
            model.addItemToCart("0001", 3);
        });
        JButton button2 = new JButton("Add 0002");
        button2.addActionListener(e -> {
            model.addItemToCart("0002", 7);
        });
        JButton button3 = new JButton("Add 0003");
        button3.addActionListener(e -> {
            model.addItemToCart("0003", 1);
        });
        JButton button4 = new JButton("Add 0004");
        button4.addActionListener(e -> {
            model.addItemToCart("0004", 2);
        });
        testFrame.setLayout(new FlowLayout());

        testFrame.add(button1);
        testFrame.add(button2);
        testFrame.add(button3);
        testFrame.add(button4);

        testFrame.pack();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    }
}
