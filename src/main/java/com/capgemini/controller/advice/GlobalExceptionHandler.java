package com.capgemini.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.exception.*;
@ControllerAdvice
public class GlobalExceptionHandler 
{
	@ExceptionHandler(DuplicateDonorException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateDonorException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateEmployeeException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(DuplicateNeedyPeopleException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateNeedyPeopleException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	@ExceptionHandler(NoSuchNeedyPeopleException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchNeedyPeopleException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchDonorException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchDonorException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(NoSuchEmployeeException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchEmployeeException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
}







