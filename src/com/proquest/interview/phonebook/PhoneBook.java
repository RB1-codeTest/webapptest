package com.proquest.interview.phonebook;

public interface PhoneBook {

	public void addPerson(Person newPerson);

	public Person findPerson(String firstName, String lastName);

	public void createPeopleInDataBase();

	public void findAllPeopleAndPrintToConsole();

}
