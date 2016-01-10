package com.mcnedward.app;

public class Skill {
	private int number;
	private int id;

	public Skill(int number, int id) {
		this.number = number;
		this.id = id;
	}
	
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

}
