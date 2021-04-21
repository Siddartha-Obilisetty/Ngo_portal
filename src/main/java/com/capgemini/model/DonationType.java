package com.capgemini.model;

public enum DonationType {
	MONEY(0),CLOTHES(1),BOOKS(2),EDIBLE(3),OTHERS(4);
	private final int type;
	DonationType(int type) {
		this.type=type;
	}
	public int getType() {
		return type;
	}
}
