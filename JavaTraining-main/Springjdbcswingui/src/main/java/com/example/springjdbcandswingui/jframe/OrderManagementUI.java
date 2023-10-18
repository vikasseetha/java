package com.example.springjdbcandswingui.jframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import com.example.springjdbcandswingui.dao.BookDAO;
import com.example.springjdbcandswingui.dao.CustomerDAO;
import com.example.springjdbcandswingui.dao.OrderDAO;
import com.example.springjdbcandswingui.entity.Book;
import com.example.springjdbcandswingui.entity.Customer;
import com.example.springjdbcandswingui.entity.Order;

public class OrderManagementUI extends JFrame {

	private OrderDAO orderDAO;
	private BookDAO bookDAO;
	private CustomerDAO customerDAO;
	private JTable orderTable;
	private JButton placeOrderButton;
	private JButton updateOrderButton;
	private JButton deleteOrderButton;

	public OrderManagementUI(OrderDAO orderDAO, BookDAO bookDAO, CustomerDAO customerDAO) {
		this.orderDAO = orderDAO;
		this.bookDAO = bookDAO;
		this.customerDAO = customerDAO;

		setTitle("Order Management");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		orderTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(orderTable);

		placeOrderButton = new JButton("Place Order");
		updateOrderButton = new JButton("Update Order");
		deleteOrderButton = new JButton("Delete Order");

		placeOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Ask for customer name
				String customerName = JOptionPane.showInputDialog(OrderManagementUI.this, "Enter Customer Name:",
						"Place Order", JOptionPane.PLAIN_MESSAGE);
				List<String> customerNames = customerDAO.getAllCustomers().stream().map(Customer::getName)
						.collect(Collectors.toList());
				if (customerName != null && !customerName.trim().isEmpty() && customerNames.contains(customerName)) {
					// Retrieve available books
					List<Book> availableBooks = bookDAO.getAllBooks();
					if (availableBooks.isEmpty()) {
						JOptionPane.showMessageDialog(OrderManagementUI.this, "No books available to place an order.",
								"No Books Available", JOptionPane.INFORMATION_MESSAGE);
						return;
					}

					// Create a dialog to select the book
					JDialog bookSelectionDialog = new JDialog(OrderManagementUI.this, "Select Book to Place Order",
							true);
					bookSelectionDialog.setSize(400, 300);

					JList<Book> bookList = new JList<>(availableBooks.toArray(new Book[0]));
					JScrollPane bookListScrollPane = new JScrollPane(bookList);

					JButton placeOrderButton = new JButton("Place Order");

					placeOrderButton.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Book selectedBook = bookList.getSelectedValue();
							if (selectedBook != null) {
								// Create the order using the selected book and customer
								Order order = new Order();
								order.setCustomerName(customerName);
								order.setTotalAmount(selectedBook.getPrice());
								order.setOrderDate(LocalDateTime.now());
								order.setBook(selectedBook);
								orderDAO.addOrder(order);

								// Reduce the book quantity in the database
								selectedBook.setQuantity(selectedBook.getQuantity() - 1);
								bookDAO.updateBook(selectedBook);

								// Refresh the order table and close the dialog
								refreshOrderTable();
								bookSelectionDialog.dispose();
							} else {
								JOptionPane.showMessageDialog(bookSelectionDialog,
										"Please select a book to place the order.", "No Book Selected",
										JOptionPane.WARNING_MESSAGE);
							}
						}
					});

					bookSelectionDialog.setLayout(new BorderLayout());
					bookSelectionDialog.add(bookListScrollPane, BorderLayout.CENTER);
					bookSelectionDialog.add(placeOrderButton, BorderLayout.SOUTH);

					bookSelectionDialog.setVisible(true);
				} else {
					JOptionPane.showMessageDialog(OrderManagementUI.this,
							"Please enter a valid customer name to place the order.", "Invalid Customer Name",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		updateOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRowIndex = orderTable.getSelectedRow();
				if (selectedRowIndex >= 0) {
					String adminName = JOptionPane.showInputDialog(OrderManagementUI.this, "Enter Admin Name:",
							"Update Order", JOptionPane.PLAIN_MESSAGE);
					List<String> adminNames = customerDAO.getAdminCustomers().stream().map(Customer::getName)
							.collect(Collectors.toList());
					if (adminName != null && !adminName.trim().isEmpty() && adminNames.contains(adminName)) {
						// Get the order ID from the selected row
						Long orderId = (Long) orderTable.getValueAt(selectedRowIndex, 0);
						// Get the order object from the database using the order ID
						Order orderToUpdate = orderDAO.getOrderById(orderId);
						if (orderToUpdate != null) {
							OrderItemDialog dialog = new OrderItemDialog(OrderManagementUI.this, "Update Order",
									orderToUpdate, orderDAO);
							dialog.setVisible(true);

							refreshOrderTable();
						}
					} else {
						JOptionPane.showMessageDialog(OrderManagementUI.this,
								"Please enter a valid Admin name to update the order.", "Invalid Admin Name",
								JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(OrderManagementUI.this, "Please select an order to update.",
							"No Order Selected", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		deleteOrderButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String adminName = JOptionPane.showInputDialog(OrderManagementUI.this, "Enter Admin Name:",
						"Delete Order", JOptionPane.PLAIN_MESSAGE);
				List<String> adminNames = customerDAO.getAdminCustomers().stream().map(Customer::getName)
						.collect(Collectors.toList());
				if (adminName != null && !adminName.trim().isEmpty() && adminNames.contains(adminName)) {
					int selectedRowIndex = orderTable.getSelectedRow();
					if (selectedRowIndex >= 0) {
						// Get the order ID from the selected row
						Long orderId = (Long) orderTable.getValueAt(selectedRowIndex, 0);
						// Ask for confirmation before deleting the order
						int confirmResult = JOptionPane.showConfirmDialog(OrderManagementUI.this,
								"Are you sure you want to delete this order?", "Confirm Deletion",
								JOptionPane.YES_NO_OPTION);
						if (confirmResult == JOptionPane.YES_OPTION) {
							// Delete the order from the database
							orderDAO.deleteOrder(orderId);
							// Refresh the order table
							refreshOrderTable();
						}
					} else {
						JOptionPane.showMessageDialog(OrderManagementUI.this, "Please select an order to delete.",
								"No Order Selected", JOptionPane.WARNING_MESSAGE);
					}
				} else {
					JOptionPane.showMessageDialog(OrderManagementUI.this,
							"Please enter a valid Admin name to delete the order.", "Invalid Admin Name",
							JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Arrange components using layout managers
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(placeOrderButton);
		buttonPanel.add(updateOrderButton);
		buttonPanel.add(deleteOrderButton);

		setLayout(new BorderLayout());
		add(tableScrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

		// Load and display the initial list of orders
		refreshOrderTable();
	}

	private void refreshOrderTable() {
		List<Order> orders = orderDAO.getAllOrders();

		OrderTableModel tableModel = new OrderTableModel(orders);
		orderTable.setModel(tableModel);
	}

	private class OrderTableModel extends AbstractTableModel {
		private List<Order> orders;

		public OrderTableModel(List<Order> orders) {
			this.orders = orders;
		}

		@Override
		public int getRowCount() {
			return orders.size();
		}

		@Override
		public int getColumnCount() {
			return 5;
		}

		@Override
		public Object getValueAt(int rowIndex, int columnIndex) {
			Order order = orders.get(rowIndex);
			switch (columnIndex) {
			case 0:
				return order.getId();
			case 1:
				return order.getCustomerName();
			case 2:
				return order.getTotalAmount();
			case 3:
				return order.getOrderDate();
			case 4:
				return order.getBook().getId();
			default:
				return null;
			}
		}

		@Override
		public String getColumnName(int column) {
			switch (column) {
			case 0:
				return "Order ID";
			case 1:
				return "Customer Name";
			case 2:
				return "Total Amount";
			case 3:
				return "Order Date";
			case 4:
				return "Book Id";
			default:
				return "";
			}
		}
	}

}
