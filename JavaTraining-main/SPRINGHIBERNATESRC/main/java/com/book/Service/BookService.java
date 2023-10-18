
package com.book.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.book.Entity.Book;
import com.book.Repository.BookRepository;

@Service
public class BookService {

	@Autowired
	private BookRepository bookRepository;

	/**
	 * This method is used for Adding the books
	 * 
	 * @param book
	 * @return
	 */

	public Book addBook(Book book) {
		return bookRepository.save(book);
	}

	/**
	 * This method is used for getting particular book based on Id
	 * 
	 * @param id
	 * @return
	 */

	public Book findById(int id) {
		return bookRepository.findById(id).get();
	}

	/**
	 * Getting the All book details
	 * 
	 * @return
	 */

	public Page<Book> getAll(Pageable pageable) {
		
		return  bookRepository.findAll(pageable);
	}

	/**
	 * Update the existing book details based on id
	 * 
	 * @param book
	 * @return
	 */

	public Book updateBook(Book book, Integer id) {
		book.setId(id);
		return bookRepository.save(book);
	}

	/**
	 * Delete the book from database based on id
	 * 
	 * @param id
	 */
	public void deleteBookById(int id) {
		bookRepository.deleteById(id);

	}

	/**
	 * Fetching book details based on title
	 * 
	 * @param title
	 * @return
	 */
	public List<Book> getByTitle(String title) {
		return bookRepository.findByTitle(title);

	}

	/**
	 * Fetching active book details
	 * 
	 * @return
	 */
	public List<Book> getActiveBooks() {
		return bookRepository.findByActiveTrue();
	}

}
