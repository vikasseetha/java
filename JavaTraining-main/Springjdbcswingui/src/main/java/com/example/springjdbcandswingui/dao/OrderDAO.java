package com.example.springjdbcandswingui.dao;

import java.util.List;
import java.util.Map;

import com.example.springjdbcandswingui.entity.Book;
import com.example.springjdbcandswingui.entity.Order;
//import com.example.springjdbcandswingui.entity.OrderItem;

public interface OrderDAO {
	List<Order> getAllOrders();

	Order getOrderById(Long id);

	void addOrder(Order order);

	void updateOrder(Order order);

	void deleteOrder(Long id);

	}
