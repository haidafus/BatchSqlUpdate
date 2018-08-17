package com.spring.person;

public class Person {
	private int id;

	private String firstName;

	private String lastName;

	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	public String toString()
	{
		return " ID "+id+"FirstNmae"+firstName+"LastName"+lastName+"Address"+address; 
	}
}
