package com.libraryManagementSystem.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libraryManagementSystem.model.Student;
import com.libraryManagementSystem.service.StudentServiceImplementation;

@RestController
@RequestMapping("/api")
@CrossOrigin
public class StudentController {
	private final StudentServiceImplementation service;

	public StudentController(StudentServiceImplementation service) {
		super();
		this.service = service;
	}
	
	
	@PostMapping("/student")
	public ResponseEntity<Student> addStudent(@RequestBody Student student){
		Student stu = service.addStudent(student);
		return new ResponseEntity<>(stu,HttpStatus.CREATED);
	}
	
	
	@GetMapping("/student")
	public ResponseEntity<List<Student>> getAllStudent(){
		return new ResponseEntity<>(service.getAllStudent(),HttpStatus.OK);
	}
	
	
	@GetMapping("/student/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable Long id){
		Optional<Student> stu = service.getStudentById(id);
		
		if(stu != null) {
			return new ResponseEntity<>(stu.get(),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@DeleteMapping("student/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable Long id){
		Optional<Student> stu = service.getStudentById(id);
		
		if(stu != null) {
			service.deleteStudent(id);
			return new ResponseEntity<>("Student deleted",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not Found",HttpStatus.BAD_REQUEST);
		}
	}
	
	
	@PutMapping("/student/{id}")
	public ResponseEntity<?> updateStudentById(@PathVariable Long id, @RequestBody Student student){
		Student stu = null;
		
		try {
			stu = service.updateStudent(id, student);
		} catch (Exception e) {
			return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
		}
		
		if(stu !=null) {
			return new ResponseEntity<>("Updated",HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>("Not Updated",HttpStatus.BAD_REQUEST);
		}
	}
	
	
}
