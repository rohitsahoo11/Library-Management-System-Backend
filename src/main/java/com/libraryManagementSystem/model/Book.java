package com.libraryManagementSystem.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Book {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String author;
	
	@Column(nullable = false)
	private String title;
	
	@Column(unique = true, nullable = false)
	private String isbn;
	private int quantity;
	
	@OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
	@JsonBackReference(value = "book-issue")
	private List<IssueRecord> issueRecords;

	public Book() {
		super();
	}

	public Book(Long id, String author, String title, String isbn, int quantity, List<IssueRecord> issueRecords) {
		super();
		this.id = id;
		this.author = author;
		this.title = title;
		this.isbn = isbn;
		this.quantity = quantity;
		this.issueRecords = issueRecords;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public List<IssueRecord> getIssueRecords() {
		return issueRecords;
	}

	public void setIssueRecords(List<IssueRecord> issueRecords) {
		this.issueRecords = issueRecords;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", author=" + author + ", title=" + title + ", isbn=" + isbn + ", quantity="
				+ quantity + ", issueRecords=" + issueRecords + "]";
	}
	
	
	
}
