package com.example.springjdbcandswingui.entity;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRowMapper implements RowMapper<Customer> {
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setId(rs.getLong("id"));
		customer.setName(rs.getString("name"));
		customer.setEmail(rs.getString("email"));
		customer.setAddress(rs.getString("address"));
		customer.setIs_admin(rs.getBoolean("is_admin"));
		return customer;
	}
}
