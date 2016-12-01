package com.andre.javapractice.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTest {

	private static List<Dish> menu = Arrays.asList(
			new Dish(Dish.Type.MEAT, "pork", false, 800),
			new Dish(Dish.Type.MEAT, "beef", false, 700),
			new Dish(Dish.Type.MEAT, "chicken", false, 400),
			new Dish(Dish.Type.OTHER, "french fries", true, 530),
			new Dish(Dish.Type.OTHER, "rice", true, 350),
			new Dish(Dish.Type.OTHER, "season fruit", true, 120),
			new Dish(Dish.Type.OTHER, "pizza", true, 550),
			new Dish(Dish.Type.FISH, "prawns", false, 300),
			new Dish(Dish.Type.FISH, "salmon", false, 450));

	public static void main(String[] args) {
		// 将两个列表中的数字组成数对。
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(3, 4);
		List<int[]> pairs = list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j}))
				.collect(Collectors.toList());
		pairs.stream().forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ")"));
		
	}

}
