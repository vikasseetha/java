package com.book.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.Entity.Book;
import com.book.Service.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

	@Autowired
	private BookService bookService;

	@PostMapping("/save")
	public Book addBook(@RequestBody Book book) {
		return bookService.addBook(book);
	}

	@GetMapping("/{id}")
	public Book getById(@PathVariable Integer id) {
		return bookService.findById(id);
	}

	@GetMapping
	public Page<Book> getAllBooks(@RequestParam(value = "pageNumber", defaultValue = "1") Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "2") Integer pageSize) {
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		return bookService.getAll(pageable);
	}

	@PutMapping("/{id}")
	public Book updateBook(@RequestBody Book book, @PathVariable Integer id) {
		return bookService.updateBook(book, id);
	}

	@DeleteMapping("/{id}")
	public String deleteBook(@PathVariable Integer id) {
		bookService.deleteBookById(id);
		return "Deleted Successfully";
	}

	@GetMapping("/search")
	public List<Book> getByTitle(@RequestParam String title) {
		return bookService.getByTitle(title);
	}

	@GetMapping("/active")
	public List<Book> getActiveBooks() {
		return bookService.getActiveBooks();
	}

}
