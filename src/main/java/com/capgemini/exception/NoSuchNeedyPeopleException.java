package com.capgemini.exception;

public class NoSuchNeedyPeopleException extends Exception {

	private int needyPersonid;
	private String name;
	public NoSuchNeedyPeopleException(int needyPersonid) {
		this.needyPersonid=needyPersonid;
	}
	public NoSuchNeedyPeopleException(String name) {
		this.name=name;
	}
	@Override
	public String getMessage() {
		if(needyPersonid!=0)
			return "The Person with Id "+needyPersonid+" doesn't exists!";
		else
			return "The Donor with Name "+name+" doesn't exists!";
	}
}
