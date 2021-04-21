package com.capgemini.exception;

public class NoSuchEmployeeException extends Exception{

	private int donorId=0;	
	private String name="";
	public NoSuchEmployeeException(int donorId) {
		this.donorId=donorId;
	}

	public NoSuchEmployeeException(String name) {
		this.name=name;
	}
	
	@Override
	public String getMessage() {
		if(donorId!=0)
			return "The Employee with EmployeeId "+donorId+" doesn't exists!";
		else
			return "The Donor with Name "+name+" doesn't exists!";
	}

}
