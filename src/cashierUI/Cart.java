package cashierUI;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cart extends JFrame {
    public DefaultTableModel tableModel;
    public JPanel summaryPanel;
    public JLabel subtotalPriceLabel;
    public JLabel locationInfoLabel;
    public JLabel salesTaxLabel;
    public JLabel discountLabel;
    public JCheckBox discountCheck;
    public JLabel totalPriceLabel;
    public Double taxPercent;
    public Double discountPercent;
    public double subtotal;
    public double grandtotal;

    public Cart() {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 700));
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        tableModel = new DefaultTableModel(0,0);
        String[] header = {"Product ID", "Product", "Quantity", "Price"};
        tableModel.setColumnIdentifiers(header);
        JTable table = new JTable(tableModel);
        JScrollPane jsTable = new JScrollPane(table);
        jsTable.setVisible(true);

        // gbc initialize for table
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 4;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(jsTable, gbc);

        // initialize summary components
        summaryPanel = new JPanel();
        summaryPanel.setLayout(new GridLayout(5, 2));
        JLabel subtotalLabel = new JLabel("Subtotal:");
        subtotalPriceLabel = new JLabel("", SwingConstants.RIGHT);
        locationInfoLabel = new JLabel("");
        salesTaxLabel = new JLabel("", SwingConstants.RIGHT);
        discountLabel = new JLabel("");
        discountCheck = new JCheckBox();
        discountCheck.addActionListener(e -> {
            checkDiscountSetTotal();
        });
        JLabel totalLabel = new JLabel("<html><b><font size=\"4\">TOTAL:</font></html></b>");
        totalPriceLabel = new JLabel("", SwingConstants.RIGHT);
        JLabel blankLabel = new JLabel();
        JButton checkout = new JButton("PRINT");

        // add components to summary panel
        summaryPanel.add(subtotalLabel);
        summaryPanel.add(subtotalPriceLabel);
        summaryPanel.add(locationInfoLabel);
        summaryPanel.add(salesTaxLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(discountCheck);
        summaryPanel.add(totalLabel);
        summaryPanel.add(totalPriceLabel);
        summaryPanel.add(blankLabel);
        summaryPanel.add(checkout);
        summaryPanel.setVisible(false);

        // gbc initialize for order summary
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.gridheight = 2;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(summaryPanel, gbc);

        // frame stuff
        this.setTitle("Shopping Cart");
        this.pack();
        this.setLocationRelativeTo(null);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
        setVisible(true);
    }

    public void checkDiscountSetTotal() {
        if (discountCheck.isSelected()) {
            grandtotal = subtotal * (1 - discountPercent) * (1 + taxPercent);
        } else {
            grandtotal = subtotal * (1 + taxPercent);
        }
        totalPriceLabel.setText("<html><b><font size=\"4\">$" + String.format("%.2f", grandtotal) + "</font></html></b>");
    }

    public void reset() {
        // clear table and hide summary panel
        discountPercent = null;
        taxPercent = null;
        subtotal = 0;
        grandtotal = 0;
        tableModel.setRowCount(0);
        discountCheck.setSelected(false);
        subtotalPriceLabel.setText("");
        totalPriceLabel.setText("");
        summaryPanel.setVisible(false);
    }
}
