package com.libraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import com.libraryManagementSystem.model.Book;

public interface BookService {
	
	Book addBook(Book book);
	Book updateBook(Long id,Book book);
	void deleteBook(long id);
	List<Book> getAllBook();
	Optional<Book> getBookById(Long id);
}
