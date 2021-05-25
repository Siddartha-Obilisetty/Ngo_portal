package com.capgemini.exception;

//Exception class

public class WrongCredentialsException extends Exception
{
	String message="Wrong Credentials";
	
	//constructor
	public WrongCredentialsException() {}
	
	@Override
	public String getMessage() {
		return message;
	}

}
