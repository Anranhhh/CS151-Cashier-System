import com.google.gson.Gson;
import domain.Item;
import domain.ShopInfo;
import model.DataModel;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        DataModel model = new DataModel(); // CART is in here
        model.updateJSON();
        cartTest(model);
    }


    // TEST FOR CART: FRAME2
    public static void cartTest(DataModel model) {
        JFrame testFrame = new JFrame("Test Frame");
        JButton button1 = new JButton("Add T3DDY");
        button1.addActionListener(e -> {
            model.addItemToCart("T3DDY", 1);
        });
        JButton button2 = new JButton("Add C4NDY");
        button2.addActionListener(e -> {
            model.addItemToCart("C4NDY", 1);
        });
        JButton button3 = new JButton("Add IP14M");
        button3.addActionListener(e -> {
            model.addItemToCart("IP14M", 1);
        });
        JButton button4 = new JButton("Add OLEDT");
        button4.addActionListener(e -> {
            model.addItemToCart("OLEDT", 1);
        });
        JButton Rbutton1 = new JButton("Remove T3DDY");
        Rbutton1.addActionListener(e -> {
            model.removeItem("T3DDY");
        });
        JButton Rbutton2 = new JButton("Remove C4NDY");
        Rbutton2.addActionListener(e -> {
            model.removeItem("C4NDY");
        });
        JButton Rbutton3 = new JButton("Remove IP14M");
        Rbutton3.addActionListener(e -> {
            model.removeItem("IP14M");
        });
        JButton Rbutton4 = new JButton("Remove OLEDT");
        Rbutton4.addActionListener(e -> {
            model.removeItem("OLEDT");
        });
        testFrame.setLayout(new GridLayout(2, 4));
        testFrame.add(button1);
        testFrame.add(button2);
        testFrame.add(button3);
        testFrame.add(button4);
        testFrame.add(Rbutton1);
        testFrame.add(Rbutton2);
        testFrame.add(Rbutton3);
        testFrame.add(Rbutton4);

        testFrame.pack();
        testFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        testFrame.setVisible(true);
    }
}
