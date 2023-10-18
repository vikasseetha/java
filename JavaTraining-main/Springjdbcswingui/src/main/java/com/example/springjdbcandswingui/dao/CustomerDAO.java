package com.example.springjdbcandswingui.dao;

import java.util.List;

import com.example.springjdbcandswingui.entity.Customer;

public interface CustomerDAO {
	List<Customer> getAllCustomers();

	Customer getCustomerById(Long id);

	void addCustomer(Customer customer);

	void updateCustomer(Customer customer);

	void deleteCustomer(Long id);
	
	 List<Customer> getAdminCustomers();
}
