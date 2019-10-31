package com.proquest.interview.phonebook;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {

	public PhoneBookImpl() {
		DatabaseUtil.initDB(); // You should not remove this line, it creates the in-memory database
	}
	
	@Override
	public void addPerson(Person newPerson) {
		if (newPerson == null || newPerson.getName() == null || newPerson.getPhoneNumber() == null
				|| newPerson.getAddress() == null) {
			throw new IllegalArgumentException("Invalid values when calling addPerson");
		}

		DatabaseUtil.addPerson(newPerson);
	}

	@Override
	public Person findPerson(String firstName, String lastName) {
		if (firstName == null || lastName == null) {
			throw new IllegalArgumentException("Invalid values when calling findPerson");
		}

		return DatabaseUtil.findPerson(firstName, lastName);
	}


	@Override
	public void createPeopleInDataBase() {
		addPerson(new Person("John Smith", "(248) 123-4567", "1234 Sand Hill Dr, Royal Oak, MI"));
		addPerson(new Person("Cynthia Smith", "(824) 128-8758", "875 Main St, Ann Arbor, MI"));
	}

	@Override
	public void findAllPeopleAndPrintToConsole() {
		DatabaseUtil.findAllPeople();
	}
}
