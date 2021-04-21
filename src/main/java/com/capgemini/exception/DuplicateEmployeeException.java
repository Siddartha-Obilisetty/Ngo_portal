package com.capgemini.exception;

public class DuplicateEmployeeException extends Exception{
	private int empid;
	public DuplicateEmployeeException(int empid) {
		this.empid=empid;
	}
	@Override
	public String getMessage() {
		return "The Employee with EmployeeId "+empid+" already exists!";
	}
}
