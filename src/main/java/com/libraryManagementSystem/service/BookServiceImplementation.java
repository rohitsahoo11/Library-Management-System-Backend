package com.libraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.repository.BookRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class BookServiceImplementation implements BookService{
	
	private final BookRepository bookRepo;

	public BookServiceImplementation(BookRepository bookRepo) {
		super();
		this.bookRepo = bookRepo;
	}
	
	
	
	@Override
	public Book addBook(Book book){
		
		if(bookRepo.existsByIsbn(book.getIsbn())) {
			throw new RuntimeException("Book with ISBN Already exists.");
		}
		
		return bookRepo.save(book);
	}



	

	@Override
	public Book updateBook(Long id,Book book) {
		
		Book existing = bookRepo.findById(id)
									.orElseThrow(()-> new RuntimeException("Book not Found"));
		
		existing.setTitle(book.getTitle());
		existing.setAuthor(book.getAuthor());
		existing.setIsbn(book.getIsbn());
		existing.setQuantity(book.getQuantity());
		
		return bookRepo.save(existing);
		
		
		
	}



	@Override
	public void deleteBook(long id) {
		
		if(!bookRepo.existsById(id)) {
			throw new RuntimeException("Book with this id doesn't exisit");
		}
		
		bookRepo.deleteById(id);
		
	}



	@Override
	public List<Book> getAllBook() {
		
		return bookRepo.findAll();
	}



	@Override
	public Optional<Book> getBookById(Long id) {
		
		return Optional.of(bookRepo.findById(id)
							.orElseThrow(()-> new EntityNotFoundException("Book with the id not found: "+id)));
	}
}
