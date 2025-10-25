package com.libraryManagementSystem.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	//Handle Validation errors
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationErrors(MethodArgumentNotValidException ex){
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getFieldErrors().forEach(error->
					errors.put(error.getField(), error.getDefaultMessage())
				);
		
		return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
	}
	
	
	// Handle entity not found
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleAllException(Exception ex){
		return new ResponseEntity<>("Internal Server Error: "+ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
