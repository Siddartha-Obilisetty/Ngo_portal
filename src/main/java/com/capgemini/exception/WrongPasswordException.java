package com.capgemini.exception;

public class WrongPasswordException extends Exception
{
	String message="Wrong Password";
	public WrongPasswordException() {}
	@Override
	public String getMessage() {
		return message;
	}

}
