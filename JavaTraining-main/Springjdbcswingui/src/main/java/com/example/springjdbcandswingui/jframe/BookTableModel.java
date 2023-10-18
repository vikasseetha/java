package com.example.springjdbcandswingui.jframe;

import javax.swing.table.AbstractTableModel;

import com.example.springjdbcandswingui.entity.Book;

import java.util.List;

public class BookTableModel extends AbstractTableModel {

	private List<Book> books;
	private String[] columnNames = { "ID", "Title", "Author", "Price", "quantity" };

	public BookTableModel(List<Book> books) {
		this.books = books;
	}

	@Override
	public int getRowCount() {
		return books.size();
	}

	@Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		Book book = books.get(rowIndex);
		switch (columnIndex) {
		case 0:
			return book.getId();
		case 1:
			return book.getTitle();
		case 2:
			return book.getAuthor();
		case 3:
			return book.getPrice();
		case 4:
			return book.getQuantity();
		default:
			return null;
		}
	}

	
	//It is used to give above mentioned column nmaes if we are not giving means we are getting coulmn names as a,b,c,d,e
	@Override
	public String getColumnName(int column) {
		return columnNames[column];
	}
}
