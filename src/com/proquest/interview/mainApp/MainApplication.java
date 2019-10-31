package com.proquest.interview.mainApp;

import com.proquest.interview.phonebook.PhoneBook;
import com.proquest.interview.phonebook.PhoneBookImpl;

public class MainApplication {

	public static void main(String[] args) {
		System.out.println("Start: proQuest Test app");

		PhoneBook phoneBook = new PhoneBookImpl();

		phoneBook.findAllPeopleAndPrintToConsole();

		phoneBook.findPerson("Cynthia", "Smith"); // Not in DB yet! check console output

		phoneBook.createPeopleInDataBase();

		phoneBook.findPerson("Cynthia", "Smith"); // Should now be in DB! check console output

		System.out.println("End: proQuest Test app");
	}
}
