package com.example.springjdbcandswingui.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import com.example.springjdbcandswingui.dao.CustomerDAO;
import com.example.springjdbcandswingui.dao.CustomerDAOImpl;
import com.example.springjdbcandswingui.entity.Customer;

public class CustomerStoreUI extends JFrame {

	private CustomerDAO customerDAO;

	private JTable customerTable;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JCheckBox adminCheckbox;

	public CustomerStoreUI(CustomerDAO customerDAO) {
		this.customerDAO = customerDAO;

		setTitle("Customer Management");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		customerTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(customerTable);

		addButton = new JButton("Add Customer");
		updateButton = new JButton("Update Customer");
		deleteButton = new JButton("Delete Customer");
		adminCheckbox = new JCheckBox("Admin");

		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				addCustomer();
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = customerTable.getSelectedRow();
				if (selectedRow != -1) {
					Long customerId = (Long) customerTable.getValueAt(selectedRow, 0);
					Customer customerToUpdate = customerDAO.getCustomerById(customerId);
					CustomerDialog dialog = new CustomerDialog(CustomerStoreUI.this, "Update Customer",
							customerToUpdate, customerDAO);
					dialog.setVisible(true);
					refreshCustomerTable();
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = customerTable.getSelectedRow();
				if (selectedRow != -1) {
					int option = JOptionPane.showConfirmDialog(CustomerStoreUI.this,
							"Are you sure you want to delete this customer?", "Confirm Delete",
							JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION) {
						Long customerId = (Long) customerTable.getValueAt(selectedRow, 0);
						customerDAO.deleteCustomer(customerId);
						refreshCustomerTable();
					}
				}
			}
		});

		JPanel buttonPanel = new JPanel(new FlowLayout());
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		JPanel checkboxPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		checkboxPanel.add(adminCheckbox);

		JPanel southPanel = new JPanel(new BorderLayout());
		southPanel.add(buttonPanel, BorderLayout.NORTH); // top
		southPanel.add(checkboxPanel, BorderLayout.SOUTH); // bottom

		setLayout(new BorderLayout());
		add(tableScrollPane, BorderLayout.CENTER);
		add(southPanel, BorderLayout.SOUTH);

		refreshCustomerTable();
	}

	private void refreshCustomerTable() {
		List<Customer> customers = customerDAO.getAllCustomers();
		CustomerTableModel tableModel = new CustomerTableModel(customers);
		customerTable.setModel(tableModel);
	}

	private void addCustomer() {
		String name = JOptionPane.showInputDialog(CustomerStoreUI.this, "Enter Name:");
		String email = JOptionPane.showInputDialog(CustomerStoreUI.this, "Enter Email:");
		String address = JOptionPane.showInputDialog(CustomerStoreUI.this, "Enter Address:");
		boolean isAdmin = adminCheckbox.isSelected();

		Customer customer = new Customer();
		customer.setName(name);
		customer.setEmail(email);
		customer.setAddress(address);
		customer.setIs_admin(isAdmin);

		customerDAO.addCustomer(customer);
		refreshCustomerTable();
	}

}
