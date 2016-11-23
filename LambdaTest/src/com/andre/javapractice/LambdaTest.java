package com.andre.javapractice;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * LambdaTest.
 * 
 * @author Andre
 */
public class LambdaTest {

	private static List<Person> roster;
	static {
		roster = new ArrayList<>();
		roster.add(new Person("Marvin", LocalDate.parse("1980-05-01"), Person.Sex.MALE, "13874689281", "marvin@sina.com"));
		roster.add(new Person("Steve", LocalDate.parse("1997-08-23"), Person.Sex.MALE, "18638659689", "steve01@163.com"));
		roster.add(new Person("Jane", LocalDate.parse("1995-10-14"), Person.Sex.FEMALE, "18761616688", "janeAnne@outlook.com"));
		roster.add(new Person("Dolores", LocalDate.parse("2000-01-31"), Person.Sex.FEMALE, "13610102846", "doloresWong@gmail.com"));
		roster.add(new Person("William", LocalDate.parse("1992-02-14"), Person.Sex.MALE, "13910563827", "williamBlack@westworld.com"));
	}

	public static void printPersonsWithPredicate(List<Person> roster, Predicate<Person> tester) {
		for (Person p : roster) {
			if (tester.test(p)) {
				p.printPerson();
			}
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// lambda test01.
		printPersonsWithPredicate(roster, p -> p.getGender() == Person.Sex.MALE && p.getAge() >= 18 && p.getAge() < 25);
	}

}
