package com.example.springjdbcandswingui.jframe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.example.springjdbcandswingui.dao.OrderDAO;
import com.example.springjdbcandswingui.entity.Order;

public class OrderItemDialog extends JDialog {
	private OrderDAO orderDAO;
	private Order orderToUpdate;

	private JTextField customerNameField;
	private JTextField totalAmountField;
	private JTextField orderDateFiled;
	private JTextField orderId;
	// private JTextField bookIdFiled;
	private JButton updateButton;

	public OrderItemDialog(JFrame parent, String title, Order orderToUpdate, OrderDAO orderDAO) {
		super(parent, title, true);
		this.orderToUpdate = orderToUpdate;
		this.orderDAO = orderDAO;

		customerNameField = new JTextField(orderToUpdate.getCustomerName());
		totalAmountField = new JTextField(String.valueOf(orderToUpdate.getTotalAmount()));
		orderDateFiled = new JTextField(orderToUpdate.getOrderDate().toString());
		// bookIdFiled = new JTextField(orderToUpdate.getBook().getId().toString());
		updateButton = new JButton("Update");

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String updatedCustomerName = customerNameField.getText().trim();
				double updatedTotalAmount = Double.parseDouble(totalAmountField.getText().trim());
				String orderDate = orderDateFiled.getText().trim();
				orderToUpdate.setCustomerName(updatedCustomerName);
				orderToUpdate.setTotalAmount(updatedTotalAmount);
				orderToUpdate.setOrderDate(LocalDateTime.parse(orderDate));
				orderToUpdate.setId(orderToUpdate.getId());

				orderDAO.updateOrder(orderToUpdate);
				JOptionPane.showMessageDialog(OrderItemDialog.this, "Order updated successfully.", "Update Order",
						JOptionPane.INFORMATION_MESSAGE);

				setVisible(false);
			}
		});

		JPanel panel = new JPanel(new GridLayout(5, 4));
		panel.add(new JLabel("Customer Name:"));
		panel.add(customerNameField);
		panel.add(new JLabel("Total Amount:"));
		panel.add(totalAmountField);
		panel.add(orderDateFiled);
	

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(updateButton);

		add(panel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(parent);
	}
}
