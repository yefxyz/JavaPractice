package com.andre.javapractice.lambda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * LambdaTest.
 * 
 * @author Andre
 */
public class LambdaTest {

	private static List<Person> roster;
	static {
		roster = new ArrayList<>();
		roster.add(
				new Person("Marvin", LocalDate.parse("1980-05-01"), Person.Sex.MALE, "13874689281", "marvin@sina.com"));
		roster.add(
				new Person("Steve", LocalDate.parse("1997-08-23"), Person.Sex.MALE, "18638659689", "steve01@163.com"));
		roster.add(new Person("Jane", LocalDate.parse("1995-10-14"), Person.Sex.FEMALE, "18761616688",
				"janeAnne@outlook.com"));
		roster.add(new Person("Dolores", LocalDate.parse("2000-01-31"), Person.Sex.FEMALE, "13610102846",
				"doloresWong@gmail.com"));
		roster.add(new Person("William", LocalDate.parse("1992-02-14"), Person.Sex.MALE, "13910563827",
				"williamBlack@westworld.com"));
	}

	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	/**
	 * 按重量列表构造苹果列表。
	 * 
	 * @param weightList
	 * @param f
	 * @return
	 */
	public static List<Apple> genApples(List<Integer> weightList, Function<Integer, Apple> f) {
		List<Apple> appleList = new ArrayList<>();
		for (Integer weight : weightList) {
			appleList.add(f.apply(weight));
		}
		return appleList;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// lambda test01.
		printPersonsWithPredicate(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() < 25);

		// ---- 构造函数方法引用 ---- //
		Supplier<Apple> c1a = () -> new Apple();
		Supplier<Apple> c1b = Apple::new;
		Apple c1 = c1b.get();

		// weight为int。
		Function<Integer, Apple> c2a = weight -> new Apple(weight); // 有装箱。
		IntFunction<Apple> c2b = weight -> new Apple(weight); // 无装箱。
		IntFunction<Apple> c2c = Apple::new; // 构造函数引用。
		Apple c2 = c2c.apply(5);

		BiFunction<String, Integer, Apple> c3a = (color, weight) -> new Apple(color, weight);
		BiFunction<String, Integer, Apple> c3b = Apple::new;
		Apple c3 = c3b.apply("green", 8);

		List<Integer> weightList = Arrays.asList(7, 3, 4, 10, 8, 6);
		List<Apple> appleList = genApples(weightList, Apple::new);

		// 按重量排序一个列表中的苹果。
		appleList.sort((a1, a2) -> a1.getIntegerWeight().compareTo(a2.getIntegerWeight())); // Lambda定义Comparator。
		appleList.sort(Comparator.comparing(a -> a.getWeight())); // Comparator中的辅助方法comparing接收一个Function接口，用于提取可比较的key，返回一个Comparator。
		appleList.sort(Comparator.comparing(Apple::getWeight)); // 上一条继续抽象为方法引用形式。
		appleList.sort(Comparator.comparing(Apple::getWeight).reversed().thenComparing(Apple::getCountry)); // 比较器复合。
	}

}
