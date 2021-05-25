package com.capgemini.exception;

//Exception class

public class NoSuchNeedyPeopleException extends Exception {

	private int needyPersonid;
	private String name;
	
	//constructor with Id parameter
	public NoSuchNeedyPeopleException(int needyPersonid) {
		this.needyPersonid=needyPersonid;
	}
	
	//constructor with Name parameter
	public NoSuchNeedyPeopleException(String name) {
		this.name=name;
	}
	
	@Override
	public String getMessage() {
		if(needyPersonid!=0)
			return "The NeedyPerson with Id "+needyPersonid+" doesn't exists!";
		else
			return "The NeedyPeople with Name "+name+" doesn't exists!";
	}
}
