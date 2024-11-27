package cashierUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JScrollPane;

import ShiftFrame.Frame1;
import model.DataModel;



public class Frame1 extends JFrame{
	private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField shiftStartField;
    private JTextField shiftEndField;
    private JButton shiftStartButton;
    private JButton shiftEndButton;
    
    private JButton loadButton;
    private JButton showButton;
    
    public Frame1(DataModel model) {
    	setTitle("Cashier Shift Management");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
      
/////////////////////////////////Panel 1//////////////////////////////////////////////////
        
        JPanel shiftPanel = new JPanel();
        shiftPanel.setLayout(new GridLayout(5, 2, 5, 5));
        
        // buttons
        shiftStartButton = new JButton("Start Shift");
        shiftStartButton.addActionListener(new shiftStartListener());
        shiftPanel.add(shiftStartButton);
        
        shiftEndButton = new JButton("End Shift");
        shiftEndButton.addActionListener(new shiftEndListener());
        shiftPanel.add(shiftEndButton);
        
        shiftPanel.add(new JLabel("First Name: "));
        firstNameField = new JTextField(10);
        shiftPanel.add(firstNameField);
        
        shiftPanel.add(new JLabel("Last Name: "));
        lastNameField = new JTextField(10);
        shiftPanel.add(lastNameField);
        
        shiftPanel.add(new JLabel("Shift Start Time: "));
        shiftStartField = new JTextField(20);
        shiftStartField.setEditable(false);
        shiftPanel.add(shiftStartField);
        
        shiftPanel.add(new JLabel("Shift End Time: "));
        shiftEndField = new JTextField(20);
        shiftEndField.setEditable(false);
        shiftPanel.add(shiftEndField);
        
        add(shiftPanel, BorderLayout.NORTH);
        
/////////////////////////////////Panel 2//////////////////////////////////////////////////
        
        JPanel panel2 = new JPanel();
        panel2.setLayout(new FlowLayout());
        
        loadButton = new JButton("Load Inventory");
        showButton = new JButton("Show Products");
        
        // add ActionListener to load and show the inventory
        loadButton.addActionListener(e -> {
        	model.updateJSON();
        });
        showButton.addActionListener(new showButtonListener());
        
        panel2.add(loadButton);
        panel2.add(showButton);
        
        add(panel2, BorderLayout.CENTER);
        
/////////////////////////////////Panel 3//////////////////////////////////////////////////
        
        JTextField codeField = new JTextField();
        JTextField qtyField = new JTextField();
        JTextField itemLine = new JTextField();
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BorderLayout());
        JPanel addItemPanel = new JPanel();
        JPanel removeItemPanel = new JPanel();
        
        addItemPanel.setLayout(new GridLayout(1, 5));
        removeItemPanel.setLayout(new GridLayout(1, 3, 5, 5));
        addItemPanel.add(new JLabel("Item (code): "));
        addItemPanel.add(codeField);
        addItemPanel.add(new JLabel("QTY: "));
        addItemPanel.add(qtyField);
        addItemPanel.add(addButton);
        removeItemPanel.add(new JLabel("Item #: "));
        removeItemPanel.add(itemLine);
        removeItemPanel.add(removeButton);
        
        // ActionListener to add new items into the cart
        addButton.addActionListener(e ->{
        	String code = codeField.getText().trim();
        	String quantity = qtyField.getText().trim();
        	
        	if (code.isEmpty() || quantity.isEmpty()) {
        		JOptionPane.showMessageDialog(this, "Please enter valid product code or quantity.", "Input Error", JOptionPane.ERROR_MESSAGE);
    			return;
        	}
        	
        	int qtyNumber = Integer.parseInt(quantity);
        	model.addItemToCart(code, qtyNumber);
        });
        
        removeButton.addActionListener(e ->{
        	
        });
        
        itemPanel.add(addItemPanel, BorderLayout.NORTH);
        itemPanel.add(removeItemPanel, BorderLayout.SOUTH);
        add(itemPanel, BorderLayout.SOUTH);
    }
    
    public class shiftStartListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		String firstName = firstNameField.getText();
    		String lastName = lastNameField.getText();
    		
    		if (firstName.isEmpty() || lastName.isEmpty()) {
    			JOptionPane.showMessageDialog(Frame1.this, "Please enter both first name and last name.", "Input Error", JOptionPane.ERROR_MESSAGE);
    			return;
    		}
    		
    		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		shiftStartField.setText(simpleDate.format(new Date()));
    		
    		System.out.println("Casher added: " + firstName + " " + lastName);
    	}
    }
    
    public class shiftEndListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		SimpleDateFormat simpleDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    		shiftEndField.setText(simpleDate.format(new Date()));
    	}
    }
    
    public class showButtonListener implements ActionListener{
    	public void actionPerformed(ActionEvent e) {
    		JFrame productFrame = new JFrame();
    		productFrame.setSize(400, 300);
    		productFrame.setLayout(new BorderLayout());
    		
    		JPanel panel = new JPanel();
           	panel.setLayout(new GridLayout(3, 1, 5, 5));
    		
    		JLabel label = new JLabel("Code" + "       " + "Name" + "      " + "Description");
    		panel.add(label);
    		
    		JTextField text = new JTextField();
    		text.setEditable(false);
    		panel.add(text);
    		
    		// close the frame when button is clicked
    		JButton closeButton = new JButton("Close");
    		panel.add(closeButton);
    		closeButton.addActionListener(new  ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				productFrame.dispose();
    	    	}
    		});
    		
    		productFrame.add(new JScrollPane(text));
    		productFrame.add(panel, BorderLayout.CENTER);
    		productFrame.setVisible(true);
    	}
    }

    
    public static void main(String[] args) {
    	DataModel model = new DataModel(); // CART is in here
    	Frame1 frame = new Frame1(model);
    	frame.setVisible(true);
    }
}
