package com.capgemini.exception;

public class DuplicateEmployeeException extends Exception{
	private int emp_id;
	public DuplicateEmployeeException(int empid) {
		this.emp_id=empid;
	}
	@Override
	public String getMessage() {
		return "The Employee with EmployeeId "+emp_id+" already exists!";
	}
}
