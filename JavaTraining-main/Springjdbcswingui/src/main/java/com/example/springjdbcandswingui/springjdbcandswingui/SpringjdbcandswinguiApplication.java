package com.example.springjdbcandswingui.springjdbcandswingui;

import javax.swing.SwingUtilities;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;


import com.example.springjdbcandswingui.dao.BookDAO;
import com.example.springjdbcandswingui.dao.BookDAOImpl;
import com.example.springjdbcandswingui.dao.CustomerDAO;
import com.example.springjdbcandswingui.dao.CustomerDAOImpl;
import com.example.springjdbcandswingui.dao.OrderDAO;
import com.example.springjdbcandswingui.dao.OrderDAOImpl;
import com.example.springjdbcandswingui.jframe.BookstoreUI;
import com.example.springjdbcandswingui.jframe.CustomerStoreUI;
import com.example.springjdbcandswingui.jframe.OrderManagementUI;

@SpringBootApplication

 @EntityScan(basePackages = "com.example.springjdbcandswingui.entity")
 @ComponentScan(basePackages = "com.example.springjdbcandswingui.dao")
public class SpringjdbcandswinguiApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		ConfigurableApplicationContext context = SpringApplication.run(SpringjdbcandswinguiApplication.class, args);

		
		BookDAO bookDAO = context.getBean(BookDAO.class);
		CustomerDAO customerDAO = context.getBean(CustomerDAO.class);
		OrderDAO orderDAO = context.getBean(OrderDAO.class);
		
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				BookstoreUI bookStoreUI = new BookstoreUI(bookDAO, customerDAO);
				bookStoreUI.setVisible(true);

				CustomerStoreUI customerStoreUI = new CustomerStoreUI(customerDAO);
				customerStoreUI.setVisible(true);

				OrderManagementUI orderManagementUI = new OrderManagementUI(orderDAO, bookDAO, customerDAO);
				orderManagementUI.setVisible(true);

			}
		});
	}

}
