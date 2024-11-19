package cashierUI;

import domain.DataListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cart extends Frame implements DataListener {
    public DefaultTableModel tableModel;

    public Cart() {
        this.setLayout(new GridLayout(4, 1, 10, 0));
        tableModel = new DefaultTableModel(0,0);
        String[] header = {"Product ID", "Product", "Quantity", "Price"};
        tableModel.setColumnIdentifiers(header);
        JTable table = new JTable(tableModel);
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
        tableModel.setRowCount(1);
    }
}
