package com.capgemini.exception;

//Exception class

public class DuplicateNeedyPeopleException extends Exception {

	private int np_id;
	
	//constructor
	public DuplicateNeedyPeopleException(int np_id) {
		this.np_id=np_id;
	}
	
	@Override
	public String getMessage() {
		return "The Person Id "+np_id+" already exists!";
	}
}
