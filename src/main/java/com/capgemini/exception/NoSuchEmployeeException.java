package com.capgemini.exception;

//Exception class

public class NoSuchEmployeeException extends Exception{

	private int donorId=0;	
	private String name="";
	
	//constructor with Id parameter
	public NoSuchEmployeeException(int donorId) {
		this.donorId=donorId;
	}

	//constructor with name parameter
	public NoSuchEmployeeException(String name) {
		this.name=name;
	}
	
	@Override
	public String getMessage() {
		if(donorId!=0)
			return "The Employee with EmployeeId "+donorId+" doesn't exists!";
		else
			return "The Employee with Name "+name+" doesn't exists!";
	}

}
