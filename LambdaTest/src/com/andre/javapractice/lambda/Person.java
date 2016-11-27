package com.andre.javapractice.lambda;

import java.time.LocalDate;

public class Person {

	public enum Sex {
		MALE, FEMALE
	}

	private String name;
	
	private LocalDate birthday;
	
	private Sex gender;

	private String phoneNumber;

	private String emailAddress;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public Sex getGender() {
		return gender;
	}

	public void setGender(Sex gender) {
		this.gender = gender;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public Person(String name, LocalDate birthday, Sex gender, String phoneNumber, String email) {
		this.name = name;
		this.birthday = birthday;
		this.gender = gender;
		this.phoneNumber = phoneNumber;
		this.emailAddress = email;
	}

	public int getAge() {
		LocalDate now = LocalDate.now();
		return now.getYear() - birthday.getYear();
	}

	public void printPerson() {
		System.out.println("Name: " + name + " Gender: " + gender + " Age: " + getAge() + " Mobile: " + phoneNumber
				+ " email: " + emailAddress);
	}
}
