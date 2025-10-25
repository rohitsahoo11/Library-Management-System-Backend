package com.libraryManagementSystem.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class IssueRecord {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private LocalDate localDate;
	private LocalDate returnDate;
	private boolean returned;
	
	 @ManyToOne
	    @JoinColumn(name = "book_id")
	 @JsonManagedReference(value = "book-issue")

	    private Book book;

	    @ManyToOne
	    @JoinColumn(name = "student_id")
	    @JsonManagedReference(value = "student-issue")
	    private Student student;

		public IssueRecord(Long id, LocalDate localDate, LocalDate returnDate, boolean returned, Book book,
				Student student) {
			super();
			this.id = id;
			this.localDate = localDate;
			this.returnDate = returnDate;
			this.returned = returned;
			this.book = book;
			this.student = student;
		}

		public IssueRecord() {
			super();
		}

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public LocalDate getLocalDate() {
			return localDate;
		}

		public void setLocalDate(LocalDate localDate) {
			this.localDate = localDate;
		}

		public LocalDate getReturnDate() {
			return returnDate;
		}

		public void setReturnDate(LocalDate returnDate) {
			this.returnDate = returnDate;
		}

		public boolean isReturned() {
			return returned;
		}

		public void setReturned(boolean returned) {
			this.returned = returned;
		}

		public Book getBook() {
			return book;
		}

		public void setBook(Book book) {
			this.book = book;
		}

		public Student getStudent() {
			return student;
		}

		public void setStudent(Student student) {
			this.student = student;
		}

		@Override
		public String toString() {
			return "IssueRecord [id=" + id + ", localDate=" + localDate + ", returnDate=" + returnDate + ", returned="
					+ returned + ", book=" + book + ", student=" + student + "]";
		}
	    
	    
	    
	
}
