package cashierUI;

import model.DataModel;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Cart extends JFrame {
    public DataModel model;
    public DefaultTableModel tableModel;
    public JPanel summaryPanel;
    public JLabel subtotalPriceLabel;
    public JLabel locationInfoLabel;
    public JLabel salesTaxLabel;
    public JCheckBox discountCheck;
    public JLabel totalPriceLabel;

    public Cart(DataModel model) {
        this.model = model; // MAYBE OPTIONAL? I'M NOT SURE WHAT YOU NEED FOR RECEIPT
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(600, 700));
        GridBagConstraints gbc = new GridBagConstraints();
        panel.setLayout(new GridBagLayout());
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
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
        JLabel subtotalLabel = new JLabel("Subtotal:", SwingConstants.RIGHT);
        subtotalPriceLabel = new JLabel("", SwingConstants.RIGHT);
        locationInfoLabel = new JLabel("#No Store Info Found#", SwingConstants.RIGHT);
        salesTaxLabel = new JLabel("", SwingConstants.RIGHT);
        JLabel discountLabel = new JLabel("Discount Available:", SwingConstants.RIGHT);
        discountCheck = new JCheckBox("");
        discountCheck.setHorizontalTextPosition(SwingConstants.LEFT);
        discountCheck.setHorizontalAlignment(SwingConstants.RIGHT);
        discountCheck.addActionListener(e -> {
            checkDiscountSetTotal();
        });
        JLabel totalLabel = new JLabel("<html><b><font size=\"4\">TOTAL:</font></html></b>", SwingConstants.RIGHT);
        totalPriceLabel = new JLabel("", SwingConstants.RIGHT);
        JButton clearCart = new JButton(("CLEAR"));
        clearCart.addActionListener(e -> {
            model.clearCart();
        });
        JButton checkout = new JButton("PRINT");
        checkout.addActionListener(e -> {
            new ReceiptFrame(model, discountCheck.isSelected());
        });

        // add components to summary panel
        summaryPanel.add(subtotalLabel);
        summaryPanel.add(subtotalPriceLabel);
        summaryPanel.add(locationInfoLabel);
        summaryPanel.add(salesTaxLabel);
        summaryPanel.add(discountLabel);
        summaryPanel.add(discountCheck);
        summaryPanel.add(totalLabel);
        summaryPanel.add(totalPriceLabel);
        summaryPanel.add(clearCart);
        summaryPanel.add(checkout);
        summaryPanel.setVisible(true);

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
        this.add(panel);
        this.setTitle("Shopping Cart");
        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    public void checkDiscountSetTotal() {
        subtotalPriceLabel.setText("$" + String.format("%.2f", model.subtotal));
        double t = discountCheck.isSelected() ? model.grandtotalWithDiscount : model.grandtotal;
        totalPriceLabel.setText("<html><b><font size=\"4\">$" + String.format("%.2f", t) + "</font></html></b>");
    }

    // clear table UI
    public void reset() {
        tableModel.setRowCount(0);
        discountCheck.setSelected(false);
        subtotalPriceLabel.setText("$" + String.format("%.2f", 0.00));
        totalPriceLabel.setText("<html><b><font size=\"4\">$" + String.format("%.2f", 0.00) + "</font></html></b>");
    }
}
