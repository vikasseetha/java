package com.example.springjdbcandswingui.jframe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.example.springjdbcandswingui.dao.BookDAO;
import com.example.springjdbcandswingui.entity.Book;

public class BookDialog extends JDialog {

	private JTextField titleField;
	private JTextField authorField;
	private JTextField priceField;
	private JTextField quantityField; 
	private JButton saveButton;
	private JButton cancelButton;
	private JLabel totalLabel; 

	private BookDAO bookDAO;
	private Book bookToUpdate;

	public BookDialog(JFrame parentFrame, String title, Book bookToUpdate, BookDAO bookDAO) {
		super(parentFrame, title, true);
		this.bookDAO = bookDAO;
		this.bookToUpdate = bookToUpdate;

		titleField = new JTextField(20);
		authorField = new JTextField(20);
		priceField = new JTextField(10);
		quantityField = new JTextField(5); 
		saveButton = new JButton("Save");
		cancelButton = new JButton("Cancel");
		totalLabel = new JLabel("Total:"); 

		//Gives notification  related to total amount whenever we perform any operation
		DocumentListener documentListener = new DocumentListener() {
			@Override
			public void insertUpdate(DocumentEvent e) {
				updateTotal();//total amount calculated method
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				updateTotal();
			}

			@Override
			public void changedUpdate(DocumentEvent e) {
				updateTotal();
			}
		};

		quantityField.getDocument().addDocumentListener(documentListener);
		priceField.getDocument().addDocumentListener(documentListener);

		saveButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				saveBook();
			}
		});

		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			// is used to close or hide a Swing window or dialog.
				dispose();
			}
		});

		// Arrange components using layout managers
		JPanel formPanel = new JPanel(new GridLayout(4, 2)); // grid layout gives rows and columns
		formPanel.add(new JLabel("Title:"));
		formPanel.add(titleField);
		formPanel.add(new JLabel("Author:"));
		formPanel.add(authorField);
		formPanel.add(new JLabel("Price:"));
		formPanel.add(priceField);
		formPanel.add(new JLabel("Quantity:")); 
		formPanel.add(quantityField); 

		JPanel buttonPanel = new JPanel();
		buttonPanel.add(saveButton);
		buttonPanel.add(cancelButton);

		JPanel totalPanel = new JPanel(); 
		totalPanel.add(totalLabel);

		setLayout(new BorderLayout());
		add(formPanel, BorderLayout.CENTER); //add form at center
		add(buttonPanel, BorderLayout.SOUTH);//add button panel at bottom of the dialog
		add(totalPanel, BorderLayout.NORTH); // Add the total panel to the top of the dialog

		// If bookToUpdate is not null, set the fields with its data
		if (bookToUpdate != null) {
			titleField.setText(bookToUpdate.getTitle());
			authorField.setText(bookToUpdate.getAuthor());
			priceField.setText(String.valueOf(bookToUpdate.getPrice()));
			quantityField.setText(String.valueOf(bookToUpdate.getQuantity())); // Initialize the quantity field
			updateTotal(); // Update the total label based on the initial quantity and price
		}
		//pack is called to resize the dialog to fit its components. 
		pack();
		// method sets the location of the dialog relative to the parentframe
		setLocationRelativeTo(parentFrame);
	}

	//calculate total based on quantity and total price
	private void updateTotal() {
		try {
			int quantity = Integer.parseInt(quantityField.getText());
			double price = Double.parseDouble(priceField.getText());
			double total = quantity * price;
			totalLabel.setText("Total: " + total);
		} catch (NumberFormatException e) {
			totalLabel.setText("Total: Invalid Input");
		}
	}

	// New method to get the book after saving or updating
	public Book getBook() {
		String title = titleField.getText();
		String author = authorField.getText();
		double price = Double.parseDouble(priceField.getText());
		int quantity = Integer.parseInt(quantityField.getText());

		if (bookToUpdate == null) {
			// Create a new book and return it
			Book newBook = new Book();
			newBook.setTitle(title);
			newBook.setAuthor(author);
			newBook.setPrice(price);
			newBook.setQuantity(quantity);
			return newBook;
		} else {
			// Update the existing book and return it
			bookToUpdate.setTitle(title);
			bookToUpdate.setAuthor(author);
			bookToUpdate.setPrice(price);
			bookToUpdate.setQuantity(quantity);
			return bookToUpdate;
		}
	}

	
	//This method get data from above get method and check update or post operation
	private void saveBook() {
		Book book = getBook();

		if (bookToUpdate == null) {
			// Create a new book and add it to the database
			bookDAO.addBook(book);
		} else {
			// Update the book and save changes to the database
			bookDAO.updateBook(book);
		}

		dispose();
	}

//	public void setCustomerName(String customerName) {
//		// TODO Auto-generated method stub
//		
//	}
}
