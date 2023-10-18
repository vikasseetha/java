package com.example.springjdbcandswingui.jframe;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import com.example.springjdbcandswingui.entity.Customer;

class CustomerTableModel extends AbstractTableModel {

	private List<Customer> customers;
	private String[] columnNames = { "ID", "Name", "Email","Admin","Address"};

	public CustomerTableModel(List<Customer> customers) {
		this.customers = customers;
	}

	@Override
	public int getRowCount() {
		return customers.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Customer customer = customers.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return customer.getId();
		case 1:
			return customer.getName();
		case 2:
			return customer.getEmail();
		case 3:
			return customer.isIs_admin();
		case 4:
			return customer.getAddress();
		default:
			return null;
		}
	}

	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
