package com.example.springjdbcandswingui.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.springjdbcandswingui.entity.Book;

@Repository
public interface BookDAO {
	List<Book> getAllBooks();

	Book getBookById(Long id);

	void addBook(Book book);

	void updateBook(Book book);

	void deleteBook(Long id);
}
