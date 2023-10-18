package com.example.springjdbcandswingui.entity;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class OrderRowMapper implements RowMapper<Order> {

	@Override
	public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
		Order order = new Order();
		order.setId(rs.getLong("id"));
		order.setCustomerName(rs.getString("customer_name"));
		order.setTotalAmount(rs.getDouble("total_amount"));
		order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
		Book book = new Book();
		book.setId(rs.getLong("book_id"));
		order.setBook(book);
		return order;
	}
}
