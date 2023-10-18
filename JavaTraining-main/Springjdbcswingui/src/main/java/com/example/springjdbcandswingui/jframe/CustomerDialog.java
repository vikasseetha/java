package com.example.springjdbcandswingui.jframe;

import javax.swing.*;

import com.example.springjdbcandswingui.dao.CustomerDAO;
import com.example.springjdbcandswingui.entity.Customer;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomerDialog extends JDialog {

	private JTextField nameField;
	private JTextField emailField;
	private JTextField addressField;
	private JCheckBox adminCheckbox;

	private CustomerDAO customerDAO;
	private Customer customerToUpdate;

	public CustomerDialog(JFrame parent, String title, Customer customerToUpdate, CustomerDAO customerDAO) {
		super(parent, title, true);
		this.customerDAO = customerDAO;
		this.customerToUpdate = customerToUpdate;

		setLayout(new BorderLayout());

		nameField = new JTextField(20);
		emailField = new JTextField(20);
		addressField = new JTextField(20);
		adminCheckbox = new JCheckBox("Admin");

		// If customerToUpdate is not null, set the fields with its data
		if (customerToUpdate != null) {
			nameField.setText(customerToUpdate.getName());
			emailField.setText(customerToUpdate.getEmail());
			addressField.setText(customerToUpdate.getAddress());
			adminCheckbox.setSelected(customerToUpdate.isIs_admin());

		}

		JButton saveButton = new JButton("Save");
		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveCustomer();
			}
		});

		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		JPanel inputPanel = new JPanel(new GridLayout(6, 2));
		inputPanel.add(new JLabel("Name: "));
		inputPanel.add(nameField);
		inputPanel.add(new JLabel("Email: "));
		inputPanel.add(emailField);
		inputPanel.add(new JLabel("Address: "));
		inputPanel.add(addressField);
		inputPanel.add(new JLabel("Admin: "));
		inputPanel.add(adminCheckbox);

		JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		add(inputPanel, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		pack();
		setLocationRelativeTo(parent);
	}

	private void saveCustomer() {
		String name = nameField.getText();
		String email = emailField.getText();
		String address = addressField.getText();
		boolean isAdmin = adminCheckbox.isSelected();

		if (name.isEmpty() || email.isEmpty() || address.isEmpty()) {
			// Display an error message if any field is empty
			JOptionPane.showMessageDialog(this, "Please fill all the fields.", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}

		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setIs_admin(isAdmin);

		if (customerToUpdate != null) {
			customer.setId(customerToUpdate.getId());
			// Update the existing customer in the database
			customerDAO.updateCustomer(customer);
		} else {
			// Create a new customer and add it to the database
			customerDAO.addCustomer(customer);
		}

		dispose();
	}
}
