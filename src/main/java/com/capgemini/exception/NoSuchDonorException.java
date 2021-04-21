package com.capgemini.exception;

public class NoSuchDonorException extends Exception 
{
	private int donorId;	
	public NoSuchDonorException(int donorId) {
		this.donorId=donorId;
	}

	@Override
	public String getMessage() {
		return "The Donor with DonorId "+donorId+" doesn't exists!";
	}

}
