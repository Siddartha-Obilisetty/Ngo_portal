package com.capgemini.controller.advice;

//imports

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.capgemini.exception.*;

//Controller Adviser

@ControllerAdvice
public class GlobalExceptionHandler 
{
	//Duplicate Donor Exception
	@ExceptionHandler(DuplicateDonorException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateDonorException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Duplicate Employee Exception
	@ExceptionHandler(DuplicateEmployeeException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateEmployeeException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	//Duplicate Needy People Exception
	@ExceptionHandler(DuplicateNeedyPeopleException.class)
	public ResponseEntity<String> exceptionHandlerMethod(DuplicateNeedyPeopleException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
	
	//No Such Needy People Exception
	@ExceptionHandler(NoSuchNeedyPeopleException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchNeedyPeopleException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//No Such Donor Exception
	@ExceptionHandler(NoSuchDonorException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchDonorException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//No Such Employee Exception
	@ExceptionHandler(NoSuchEmployeeException.class)
	public ResponseEntity<String> exceptionHandlerMethod(NoSuchEmployeeException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	//Wrong Credentials Exception
	@ExceptionHandler(WrongCredentialsException.class)
	public ResponseEntity<String> exceptionHandlerMethod(WrongCredentialsException ex) {
		return new ResponseEntity<String>(ex.getMessage(),HttpStatus.NOT_ACCEPTABLE);
	}
}







