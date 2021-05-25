package com.capgemini.model;

//Enum class

public enum DonationType {
	//enum values
	MONEY(0),
	CLOTHES(1),
	BOOKS(2),
	EDIBLE(3),
	OTHERS(4);
	
	private final int type;
	
	//Parameterized constructor
	
	DonationType(int type) {
		this.type=type;
	}
	
	//Getter
	public int getType() {
		return type;
	}
}
