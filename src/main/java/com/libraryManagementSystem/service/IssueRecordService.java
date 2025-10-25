package com.libraryManagementSystem.service;

import java.util.List;

import com.libraryManagementSystem.model.IssueRecord;

public interface IssueRecordService {
	IssueRecord issueBook(Long studentId, Long bookId);
    IssueRecord returnBook(Long issueId);
    List<IssueRecord> getAllIssues();
    List<IssueRecord> getActiveIssues();
}
