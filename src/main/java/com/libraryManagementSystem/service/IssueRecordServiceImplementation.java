package com.libraryManagementSystem.service;

import java.awt.print.Book;
import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.libraryManagementSystem.model.IssueRecord;
import com.libraryManagementSystem.model.Student;
import com.libraryManagementSystem.repository.BookRepository;
import com.libraryManagementSystem.repository.IssueRecordRepository;
import com.libraryManagementSystem.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IssueRecordServiceImplementation implements IssueRecordService{
	
	private final BookRepository bookRepo;
	private final StudentRepository studentRepo;
	private final IssueRecordRepository issueRecordRepo;
	
	public IssueRecordServiceImplementation(BookRepository bookRepo, StudentRepository studentRepo,
			IssueRecordRepository issueRecordRepo) {
		super();
		this.bookRepo = bookRepo;
		this.studentRepo = studentRepo;
		this.issueRecordRepo = issueRecordRepo;
	}

	
	
	
	@Override
//	public IssueRecord issueBook(Long studentId, Long bookId) {
//		
//		com.libraryManagementSystem.model.Book book = bookRepo.findById(bookId)
//								.orElseThrow(()-> new RuntimeException());
//		
//		Student student = studentRepo.findById(studentId)
//										.orElseThrow(()-> new RuntimeException());
//		
//		if(book.getQuantity() <= 0) {
//			throw new RuntimeException("Book is out of stock");
//		}
//		
//		IssueRecord issue = new IssueRecord();
//		issue.setBook(book);
//		issue.setStudent(student);
//		issue.setLocalDate(LocalDate.now());
//		issue.setReturnDate(LocalDate.now().plusDays(14));
//		issue.setReturned(false);
//		
//		book.setQuantity(book.getQuantity()-1);
//		bookRepo.save(book);
//		System.out.println("Saved IssueRecord: " + issue.getId());
//
//		return issueRecordRepo.save(issue);
//	}
	
	public IssueRecord issueBook(Long studentId, Long bookId) {
		System.out.println("Fetching student and book...");
	    Student student = studentRepo.findById(studentId)
	        .orElseThrow(() -> new RuntimeException("Student not found with ID: " + studentId));

	    com.libraryManagementSystem.model.Book book = bookRepo.findById(bookId)
	        .orElseThrow(() -> new RuntimeException("Book not found with ID: " + bookId));
	    
	    System.out.println("Student: " + student.getName());
	    System.out.println("Book: " + book.getTitle());
	    System.out.println("Book quantity: " + book.getQuantity());




	    if (book.getQuantity() <= 0) {
	        throw new RuntimeException("Book is not available");
	    }

	    IssueRecord issue = new IssueRecord();
	    issue.setStudent(student);
	    issue.setBook(book);
	    issue.setLocalDate(LocalDate.now());
	    issue.setReturnDate(LocalDate.now().plusDays(14)); // or whatever logic you use
	    issue.setReturned(false);

	    book.setQuantity(book.getQuantity() - 1);
	    bookRepo.save(book);
	    
	    IssueRecord saved = issueRecordRepo.save(issue);
	    System.out.println("Issue saved with ID: " + saved.getId());

	    return saved;

	}
	
	
	@Override
	public IssueRecord returnBook(Long issueId) {
		
		IssueRecord issue = issueRecordRepo.findById(issueId)
												.orElseThrow(()-> new RuntimeException());
		
		if(issue.isReturned()) {
			throw new RuntimeException("Book Already Returned");
		}
		
		issue.setReturned(true);
		issue.setLocalDate(LocalDate.now());
		
		com.libraryManagementSystem.model.Book book = issue.getBook();
		book.setQuantity(book.getQuantity()+1);
		bookRepo.save(book);
		
		return issueRecordRepo.save(issue);
	}

	@Override
	public List<IssueRecord> getAllIssues() {
		
		return issueRecordRepo.findAll();
	}

	@Override
	public List<IssueRecord> getActiveIssues() {
		
		return issueRecordRepo.findByReturnedFalse();
	}

}
