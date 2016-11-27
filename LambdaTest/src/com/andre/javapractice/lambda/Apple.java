package com.andre.javapractice.lambda;

public class Apple {

	private int weight;
	
	private String color;
	
	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
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
	
}
