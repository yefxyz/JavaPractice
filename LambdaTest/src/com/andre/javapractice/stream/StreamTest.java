package com.andre.javapractice.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

	private static void practice01() {
		// �������б��е�����������ԡ�
		List<Integer> list1 = Arrays.asList(1, 2, 3);
		List<Integer> list2 = Arrays.asList(3, 4);
		List<int[]> pairs = list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j}))
				.collect(Collectors.toList());
		pairs.stream().forEach(arr -> System.out.println("(" + arr[0] + ", " + arr[1] + ")"));
	}

	private static void practice02() {
		Trader raoul = new Trader("Raoul", "Cambridge");
		Trader mario = new Trader("Mario", "Milan");
		Trader alan = new Trader("Alan", "Cambridge");
		Trader brian = new Trader("Brian", "Cambridge");

		List<Transaction> transactions = Arrays.asList(
				new Transaction(brian, 2011, 300),
				new Transaction(raoul, 2012, 1000),
				new Transaction(raoul, 2011, 400),
				new Transaction(mario, 2012, 710),
				new Transaction(mario, 2012, 700),
				new Transaction(alan, 2012, 950));

		// ����2011���н��ף��������׶�ӵ͵�������
		transactions.stream().filter(t -> t.getYear() == 2011).sorted(Comparator.comparing(Transaction::getValue))
				.forEach(System.out::println);
		// ����Ա������Щ��ͬ�ĳ��й�������
		transactions.stream().map(t -> t.getTrader().getCity()).distinct().forEach(System.out::println);
		// �������Խ��ŵĽ���Ա������������
		transactions.stream().map(Transaction::getTrader).distinct()
				.filter(tr -> tr.getCity().equalsIgnoreCase("Cambridge")).sorted(Comparator.comparing(Trader::getName))
				.forEach(System.out::println);
		// �������н���Ա������������ĸ����
		transactions.stream().map(t -> t.getTrader().getName()).distinct().sorted(Comparator.naturalOrder())
				.forEach(System.out::println);
		// ��û�н���Ա������������
		Optional<Trader> milan_Trader = transactions.stream().map(Transaction::getTrader)
				.filter(t -> t.getCity().equalsIgnoreCase("Milan")).findAny();
		System.out.println("Trader work in Milan: " + milan_Trader);
		
	}

	public static void main(String[] args) {
		// practice01();
		practice02();
	}

}
