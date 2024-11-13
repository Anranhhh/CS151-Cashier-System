package Cashier.ui;

import Cashier.logic.DataModel;
import Cashier.resources.DataListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Map;

public class Cart extends Frame implements DataListener {
    private DataModel dataModel;
    DefaultTableModel tbModel;

    public Cart(DataModel dataModel) {
        this.dataModel = dataModel;
        this.setLayout(new GridLayout(4, 1, 10, 0));
        tbModel = new DefaultTableModel(0,0);
        String[] header = {"Item", "Quantity", "Price"};
        tbModel.setColumnIdentifiers(header);
        JTable table = new JTable(tbModel);
        JScrollPane jsTable = new JScrollPane(table);
        jsTable.setVisible(true);

        TextField location = new TextField(20);
        TextField discount = new TextField(20);

        this.add(jsTable);
        this.add(location);
        this.add(discount);
        this.setSize(600, 1500);
        this.setTitle("Shopping Cart");
        this.pack();
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);

    }

    public void dataChanged() {
        tbModel.setRowCount(1);
        populateCart();
    }

    public void populateCart() {
        for (Map.Entry<String, Integer> entry : dataModel.inCart.entrySet()) {
            String key = entry.getKey();
            int qty = entry.getValue();
            tbModel.addRow(new Object[] {dataModel.inventory.get(key).getName(), qty, String.format("%.2f", (dataModel.inventory.get(key).getPrice() * qty))});
        }
    }
}
