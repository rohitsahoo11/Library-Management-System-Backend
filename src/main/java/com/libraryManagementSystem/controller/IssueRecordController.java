package com.libraryManagementSystem.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagementSystem.model.IssueRecord;
import com.libraryManagementSystem.service.IssueRecordServiceImplementation;

@RestController
@RequestMapping("/api/issues")
@CrossOrigin(origins = "*")
public class IssueRecordController {
	
	private final IssueRecordServiceImplementation service;

	public IssueRecordController(IssueRecordServiceImplementation service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/issue")
	public ResponseEntity<IssueRecord> issueBook(@RequestParam Long studentId, @RequestParam Long bookId){
		System.out.println("Received issue request: studentId=" + studentId + ", bookId=" + bookId);
		IssueRecord issue = service.issueBook(studentId, bookId);
		return new ResponseEntity<>(issue,HttpStatus.CREATED);
		
	}
	
	
	@GetMapping("/issue")
	public ResponseEntity<List<IssueRecord>> getAllIssue(){
		return new ResponseEntity<>(service.getAllIssues(),HttpStatus.OK);
	}
	
	
	@PutMapping("/return/{issueId}")
	public ResponseEntity<IssueRecord> returnIssue(@PathVariable Long issueId){
		IssueRecord record = service.returnBook(issueId);
		return new ResponseEntity<>(record,HttpStatus.OK);
	}
	
	
	@GetMapping("/active")
	public ResponseEntity<List<IssueRecord>> getActiveIssue(){
		List<IssueRecord> record = service.getActiveIssues();
		return new ResponseEntity<>(record,HttpStatus.OK);
	}
}
