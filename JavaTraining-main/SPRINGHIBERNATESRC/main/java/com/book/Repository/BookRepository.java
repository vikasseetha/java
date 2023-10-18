package com.book.Repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.book.Entity.Book;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

	List<Book> findByActiveTrue();

	List<Book> findByTitle(String name);

	Page<Book> findAll(Pageable pageable);

}
