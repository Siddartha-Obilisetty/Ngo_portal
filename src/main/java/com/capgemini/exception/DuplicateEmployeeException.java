package com.capgemini.exception;

//Exception class

public class DuplicateEmployeeException extends Exception{
	
	private int emp_id;
	
	//constructor
	public DuplicateEmployeeException(int empid) {
		this.emp_id=empid;
	}
	
	@Override
	public String getMessage() {
		return "The Employee with EmployeeId "+emp_id+" already exists!";
	}
}
