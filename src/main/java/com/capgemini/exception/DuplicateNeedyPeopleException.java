package com.capgemini.exception;

public class DuplicateNeedyPeopleException extends Exception {

	private int np_id;
	public DuplicateNeedyPeopleException(int np_id) {
		this.np_id=np_id;
	}
	@Override
	public String getMessage() {
		return "The Person Id "+np_id+" already exists!";
	}
}
