package com.capgemini.exception;

//Exception class

public class NoSuchDonorException extends Exception 
{
	private int donorId;	
	
	//constructor
	public NoSuchDonorException(int donorId) {
		this.donorId=donorId;
	}

	@Override
	public String getMessage() {
		return "The Donor with DonorId "+donorId+" doesn't exists!";
	}

}
