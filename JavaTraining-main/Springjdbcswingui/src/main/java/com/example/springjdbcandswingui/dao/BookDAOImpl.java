package com.example.springjdbcandswingui.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.springjdbcandswingui.entity.Book;
import com.example.springjdbcandswingui.entity.BookRowMapper;

import java.util.List;

@Repository
public class BookDAOImpl implements BookDAO {

	private static final String SELECT_ALL_QUERY = "SELECT * FROM book";
	private static final String SELECT_BY_ID_QUERY = "SELECT * FROM book WHERE id = ?";

	private static final String DELETE_QUERY = "DELETE FROM book WHERE id = ?";

	private static final String INSERT_QUERY = "INSERT INTO book (title, author, price, quantity) VALUES (?, ?, ?, ?)";
	private static final String UPDATE_QUERY = "UPDATE book SET title = ?, author = ?, price = ?, quantity = ? WHERE id = ?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<Book> getAllBooks() {
		return jdbcTemplate.query(SELECT_ALL_QUERY, new BookRowMapper());
	}

	@Override
	public Book getBookById(Long id) {
		return jdbcTemplate.queryForObject(SELECT_BY_ID_QUERY, new Object[] { id }, new BookRowMapper());
	}

	@Override
	public void addBook(Book book) {
		jdbcTemplate.update(INSERT_QUERY, book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity());
	}

	@Override
	public void updateBook(Book book) {
		jdbcTemplate.update(UPDATE_QUERY, book.getTitle(), book.getAuthor(), book.getPrice(), book.getQuantity(),
				book.getId());
	}

	@Override
	public void deleteBook(Long id) {
		jdbcTemplate.update(DELETE_QUERY, id);
	}
}
