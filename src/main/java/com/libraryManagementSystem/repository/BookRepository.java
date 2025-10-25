package com.libraryManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import com.libraryManagementSystem.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>{
	
	Optional<Book> findByIsbn(String isbn);
	
	List<Book> findBookByAuthor(String author);
	
	boolean existsByIsbn(String isbn);
	boolean existsById(Long id);
}
