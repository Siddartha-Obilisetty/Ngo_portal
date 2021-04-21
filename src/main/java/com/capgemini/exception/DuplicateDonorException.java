package com.capgemini.exception;

public class DuplicateDonorException extends Exception
{
	private int donorid;
	public DuplicateDonorException(int donorid){
		this.donorid=donorid;
	}
	@Override
	public String getMessage() {
		return "The Donor with DonorId "+donorid+" already exists!";
	}
}

