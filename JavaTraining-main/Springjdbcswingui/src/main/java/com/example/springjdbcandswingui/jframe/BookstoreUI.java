package com.example.springjdbcandswingui.jframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.springjdbcandswingui.dao.BookDAO;
import com.example.springjdbcandswingui.dao.CustomerDAO;
import com.example.springjdbcandswingui.entity.Book;
import com.example.springjdbcandswingui.entity.Customer;

public class BookstoreUI extends JFrame {

	@Autowired
	private BookDAO bookDAO;

	@Autowired
	private CustomerDAO customerDao;

	private JTable bookTable;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;

	public BookstoreUI(BookDAO bookDAO, CustomerDAO customerDao) {
		this.bookDAO = bookDAO;
		this.customerDao = customerDao;
		setTitle("Bookstore Management");
		setSize(800, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bookTable = new JTable();
		JScrollPane tableScrollPane = new JScrollPane(bookTable);

		addButton = new JButton("Add Book");
		updateButton = new JButton("Update Book");
		deleteButton = new JButton("Delete Book");

		// Add action listeners for buttons
		addButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String customerName = JOptionPane.showInputDialog(BookstoreUI.this, "Enter Customer or admin Name:",
						"to add book", JOptionPane.PLAIN_MESSAGE);
				List<String> customerNames = customerDao.getAllCustomers().stream().map(Customer::getName).toList();
				if (customerName != null && !customerName.trim().isEmpty() && customerNames.contains(customerName)) {

					// Handle adding a book - show a dialog to input book details
					BookDialog dialog = new BookDialog(BookstoreUI.this, "Add Book", null, bookDAO);
					dialog.setVisible(true);
					refreshBookTable();
				} else {
					JOptionPane.showMessageDialog(BookstoreUI.this,
							"Please enter a valid customer name or admin name to add the book.",
							"Invalid Customer Name or Invalid admin Name", JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		updateButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle updating a book - show a dialog with book details to update
				String customerName = JOptionPane.showInputDialog(BookstoreUI.this,
						"Enter Customer Name or Admin Name :", "to add book", JOptionPane.PLAIN_MESSAGE);
				List<String> customerNames = customerDao.getAllCustomers().stream().map(Customer::getName).toList();
				if (customerName != null && !customerName.trim().isEmpty() && customerNames.contains(customerName)) {

					int selectedRow = bookTable.getSelectedRow();
					if (selectedRow != -1) {
						Long bookId = (Long) bookTable.getValueAt(selectedRow, 0);
						Book bookToUpdate = bookDAO.getBookById(bookId);
						BookDialog dialog = new BookDialog(BookstoreUI.this, "Update Book", bookToUpdate, bookDAO);
						dialog.setVisible(true);
						refreshBookTable();
					}
				} else {
					JOptionPane.showMessageDialog(BookstoreUI.this,
							"Please enter a valid customer name or Admin name to update the book.",
							"Invalid Customer Name Or Admin Name", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		deleteButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Handle deleting a book - show a confirmation dialog and delete if confirmed
				String customerName = JOptionPane.showInputDialog(BookstoreUI.this,
						"Enter Customer Name Or Admin Name:", "to add book", JOptionPane.PLAIN_MESSAGE);
				List<String> customerNames = customerDao.getAllCustomers().stream().map(Customer::getName).toList();
				if (customerName != null && !customerName.trim().isEmpty() && customerNames.contains(customerName)) {

					int selectedRow = bookTable.getSelectedRow();
					if (selectedRow != -1) {
						int option = JOptionPane.showConfirmDialog(BookstoreUI.this,
								"Are you sure you want to delete this book?", "Confirm Delete",
								JOptionPane.YES_NO_OPTION);
						if (option == JOptionPane.YES_OPTION) {
							Long bookId = (Long) bookTable.getValueAt(selectedRow, 0);
							bookDAO.deleteBook(bookId);
							refreshBookTable();
						}
					}
				} else {
					JOptionPane.showMessageDialog(BookstoreUI.this,
							"Please enter a valid Customer Name or Admin Name to delete the book.",
							"Invalid Customer Name or Admin Name", JOptionPane.WARNING_MESSAGE);
				}
			}
		});

		// Arrange components using layout managers
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(addButton);
		buttonPanel.add(updateButton);
		buttonPanel.add(deleteButton);

		setLayout(new BorderLayout());
		add(tableScrollPane, BorderLayout.CENTER);
		add(buttonPanel, BorderLayout.SOUTH);

	}

	private void refreshBookTable() {
		List<Book> books = bookDAO.getAllBooks();
		BookTableModel tableModel = new BookTableModel(books);
		bookTable.setModel(tableModel);
	}

}
