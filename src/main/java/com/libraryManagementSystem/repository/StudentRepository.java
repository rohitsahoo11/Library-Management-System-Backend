package com.libraryManagementSystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagementSystem.model.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{
	
	Optional<Student> findByEmail(String email);
	
	Optional<Student> findByRollNo(String rollNo);
	
	boolean existsByEmail(String email);
	boolean existsByRollNo(String rollNo);
	boolean existsById(Long id);
}
