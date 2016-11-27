package com.andre.javapractice.lambda;

public class Apple {

	private int weight;
	
	private String color;
	
	private String country;
	
	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public Apple() {
		
	}
	
	public Apple(int weight) {
		this.weight = weight;
	}

	public Apple(String color, int weight) {
		this.color = color;
		this.weight = weight;
	}
	
	public Integer getIntegerWeight() {
		return weight;
	}
}
