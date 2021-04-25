package com.capgemini.exception;

//Exception class

public class WrongPasswordException extends Exception
{
	String message="Wrong Password";
	
	//constructor
	public WrongPasswordException() {}
	
	@Override
	public String getMessage() {
		return message;
	}

}
