package com.capgemini.exception;

public class DuplicateNeedyPeopleException extends Exception {

	private int needyPersonid;
	public DuplicateNeedyPeopleException(int needyPersonid) {
		this.needyPersonid=needyPersonid;
	}
	@Override
	public String getMessage() {
		return "The Person Id "+needyPersonid+" already exists!";
	}
}
