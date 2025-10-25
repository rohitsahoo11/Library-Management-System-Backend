package com.libraryManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagementSystem.dto.BookRequestDto;
import com.libraryManagementSystem.dto.BookResponseDto;
import com.libraryManagementSystem.model.Book;
import com.libraryManagementSystem.service.BookServiceImplementation;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class BookController {
	
	private BookServiceImplementation service;

	public BookController(BookServiceImplementation service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/book")
	public ResponseEntity<BookResponseDto> addBook(@Valid @RequestBody BookRequestDto dto){
		Book book = new Book();
		book.setAuthor(dto.getAuthor());
		book.setTitle(dto.getTitle());
		book.setIsbn(dto.getIsbn());
		book.setQuantity(dto.getQuantity());
		
		Book savedBook = service.addBook(book);
		
		BookResponseDto response = new BookResponseDto();
		response.setId(savedBook.getId());
		response.setAuthor(savedBook.getAuthor());
		response.setIsbn(savedBook.getIsbn());
		response.setQuantity(savedBook.getQuantity());
		
		
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	@GetMapping("/book")
	public ResponseEntity<List<Book>> getAllBooks(){
		return new ResponseEntity<>(service.getAllBook(),HttpStatus.OK);
	}
	
	
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBookById(@PathVariable Long id){
		Optional<Book> book = service.getBookById(id);
		
		if(book != null) {
			return new ResponseEntity<>(book.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	
	@DeleteMapping("/book/{id}")
	public ResponseEntity<?> deleteBookById(@PathVariable Long id){
		Optional<Book> book = service.getBookById(id);
		
		if(book != null) {
			service.deleteBook(id);
			return new ResponseEntity<>("Book Deleted Successfully",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Failed to delete the book",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	
	@PutMapping("/book/{id}")
	public ResponseEntity<?> updateBook(@PathVariable Long id, @RequestBody Book book){
		Book book1 = null;
		
		try {
			book1 = service.updateBook(id, book);
		} 
		catch (Exception e) {
			return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
		}
		
		if(book1 != null) {
			return new ResponseEntity<>("Updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
		}
	}
	
}
