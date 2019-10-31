package com.proquest.interview.phonebook;

import java.io.Serializable;

public class Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -881763870183928497L;

	private String name;
	private String phoneNumber;
	private String address;

	public Person(String name, String phoneNumber, String address) {
		if (name == null || phoneNumber == null || address == null) {
			throw new IllegalArgumentException("Tride to create person with invalid valued");
		}

		this.name = name;
		this.phoneNumber = phoneNumber;
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String toString() {
		return "Person (name=" + getName() + ", phoneNumber=" + getPhoneNumber() + ", address=" + getAddress() + ")";
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + phoneNumber.hashCode();
		result = 31 * result + address.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof Person)) {
			return false;
		}
		Person person = (Person) o;
		return person.name.equals(name) && person.phoneNumber.equals(phoneNumber) && person.address.equals(address);
	}

}
