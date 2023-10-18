package com.example.springjdbcandswingui.dao;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbcandswingui.entity.Book;
import com.example.springjdbcandswingui.entity.Order;
import com.example.springjdbcandswingui.entity.OrderRowMapper;

@Repository
public class OrderDAOImpl implements OrderDAO {

	private static final String INSERT_QUERY = "INSERT INTO \"ORDERS\" (customer_name, total_amount, order_date, book_id) VALUES (?, ?, ?, ?)";

	private static final String SELECT_ALL_QUERY = "SELECT * FROM \"ORDERS\"";

	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM \"ORDERS\" WHERE id = ?";

	private static final String UPDATE_QUERY = "UPDATE \"ORDERS\" SET customer_name = ?, total_amount = ?, order_date = ? WHERE id = ?";

	private static final String DELETE_QUERY = "DELETE FROM \"ORDERS\" WHERE id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private BookDAO bookDAO;

	@Override
	public List<Order> getAllOrders() {
		return jdbcTemplate.query(SELECT_ALL_QUERY, new OrderRowMapper());
	}

	@Override
	public Order getOrderById(Long id) {
		List<Map<String, Object>> rows = jdbcTemplate.queryForList(SELECT_BY_ID_QUERY, id);
		Order order = new Order();
		if (!rows.isEmpty()) {
			Map<String, Object> row = rows.get(0);

			Long orderId = (Long) row.get("id");
			String orderName = (String) row.get("customer_name");

			double totalAmount = (double) row.get("total_amount");
			Timestamp orderDateTimestamp = (Timestamp) row.get("order_date");
			LocalDateTime orderDate = orderDateTimestamp.toLocalDateTime();
			Long bookId = (Long) row.get("book_id");
			Book book = new Book();
			book.setId(bookId);

			order.setId(orderId);
			order.setCustomerName(orderName);
			order.setOrderDate(orderDate);
			order.setBook(book);
			order.setTotalAmount(totalAmount);
		}

		return order;
	}

	public void addOrder(Order order) {
		Book book = order.getBook();
		int quantity = book.getQuantity();
		book.setQuantity(quantity);
		bookDAO.updateBook(book);
		jdbcTemplate.update(INSERT_QUERY, order.getCustomerName(), order.getTotalAmount(), order.getOrderDate(),
				order.getBook().getId());
	}

	@Override
	public void updateOrder(Order order) {
		jdbcTemplate.update(UPDATE_QUERY, order.getCustomerName(), order.getTotalAmount(), order.getOrderDate(),
				order.getId());
	}

	@Override
	public void deleteOrder(Long id) {
		jdbcTemplate.update(DELETE_QUERY, id);
	}

}
