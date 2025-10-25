package com.libraryManagementSystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.libraryManagementSystem.model.Student;
import com.libraryManagementSystem.repository.StudentRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class StudentServiceImplementation implements StudentService{
	
	private final StudentRepository studentRepo;
	
	public StudentServiceImplementation(StudentRepository studentRepo) {
		super();
		this.studentRepo = studentRepo;
	}

	@Override
	public Student addStudent(Student student) {
		
		if(studentRepo.existsByEmail(student.getEmail())) {
			throw new RuntimeException("Email already exisits");
		}
		if(studentRepo.existsByRollNo(student.getRollNo())) {
			throw new RuntimeException("RollNo. already exisits");
		}
		
		return studentRepo.save(student);
	}

	@Override
	public Student updateStudent(Long id, Student student) {
		Student existing = studentRepo.findById(id)
										.orElseThrow(()->  new RuntimeException("Student doesnot exsists"));
		
		existing.setEmail(student.getEmail());
		existing.setDepartment(student.getDepartment());
		existing.setName(student.getName());
		existing.setRollNo(student.getRollNo());
		
		return studentRepo.save(existing);
	}

	@Override
	public void deleteStudent(Long id) {
		
		if(!studentRepo.existsById(id)) {
			throw new RuntimeException("Student not Found");
		}
		
		studentRepo.deleteById(id);
	}

	@Override
	public List<Student> getAllStudent() {
		
		return studentRepo.findAll();
	}

	@Override
	public Optional<Student> getStudentById(Long id) {
		
		return studentRepo.findById(id);
	}
	
}
