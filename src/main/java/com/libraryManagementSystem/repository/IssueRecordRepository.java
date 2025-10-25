package com.libraryManagementSystem.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libraryManagementSystem.model.IssueRecord;

@Repository
public interface IssueRecordRepository extends JpaRepository<IssueRecord, Long>{
	
	List<IssueRecord> findByStudentId(Long studentId);
	
	List<IssueRecord> findByBookId(Long bookId);
	
	List<IssueRecord> findByReturnedFalse();
	
	
}
