package com.libraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import com.libraryManagementSystem.model.Student;

public interface StudentService {
	Student addStudent(Student student);
	Student updateStudent(Long id, Student Student);
	void deleteStudent(Long id);
	List<Student> getAllStudent();
	Optional<Student> getStudentById(Long id);
}
