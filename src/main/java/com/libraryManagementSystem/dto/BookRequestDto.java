package com.libraryManagementSystem.dto;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
public class BookRequestDto {
	
	@NotBlank(message="Book author name is required")
	private String author;
	
	@NotBlank(message="Book title name is required")
	private String title;
	
	@NotBlank(message="Book isbn is required")
	private String isbn;
	
	@Min(value = 1, message = "Book quantity must be at least 1")
	private Integer quantity;


	public BookRequestDto() {

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
	
	
	
	
}
