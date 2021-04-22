package com.capgemini.controller.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.capgemini.exception.*;

@ControllerAdvice
public class GlobalExceptionalHandler 
{
	@ExceptionHandler(DuplicateDonorException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public void exceptionHandlerMethod(DuplicateDonorException ex) {
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(DuplicateEmployeeException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public void exceptionHandlerMethod(DuplicateEmployeeException ex) {
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(DuplicateNeedyPeopleException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public void exceptionHandlerMethod(DuplicateNeedyPeopleException ex) {
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(NoSuchNeedyPeopleException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void exceptionHandlerMethod(NoSuchNeedyPeopleException ex) {
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(NoSuchDonorException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void exceptionHandlerMethod(NoSuchDonorException ex) {
		System.out.println(ex.getMessage());
	}
	@ExceptionHandler(NoSuchEmployeeException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void exceptionHandlerMethod(NoSuchEmployeeException ex) {
		System.out.println(ex.getMessage());
	}
}
