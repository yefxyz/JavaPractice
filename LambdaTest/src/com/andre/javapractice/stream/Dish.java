package com.andre.javapractice.stream;

public class Dish {

	public enum Type {
		MEAT, FISH, OTHER
	};

	private final Type type;

	private final String name;

	private final boolean vegetarian;

	private final int calories;

	public Type getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Dish(Type type, String name, boolean vegetarian, int calories) {
		this.type = type;
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
	}

	@Override
	public String toString() {
		return name;
	}

}
