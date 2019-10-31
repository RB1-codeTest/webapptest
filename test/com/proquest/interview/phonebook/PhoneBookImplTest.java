package com.proquest.interview.phonebook;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Before;
import org.junit.Test;

public class PhoneBookImplTest {
	
	private PhoneBook phoneBook;
	
	@Before
	public void init() {
		phoneBook = new PhoneBookImpl();
	}
	
	@Test
	public void shouldAdd_And_FindPerson() {
		// Given
		String firstName = "Fred";
		String lastName = "Bloggs";
		phoneBook.addPerson(new Person(firstName + " " + lastName, "(111) 123-4567", "1234 Example Street, Royal Oak, MI"));
		
		// When
		Person personFound = phoneBook.findPerson(firstName, lastName);
		
		// Then
		assertNotNull(personFound);
		assertEquals(firstName + " " + lastName, personFound.getName());
	}
	
	@Test
	public void personNotFound() {
		// Given - Default data (DB could also be blank) - see DatabaseUtil.initDB() 

		// When 
		Person personFound = phoneBook.findPerson("Bob", "Smith");
		
		// Then
		assertNull(personFound);
	}
	
	// TODO findPersonInvalidLastName would be similar to findPersonInvalidFirstName
	@Test
	public void personSearchInvalidFirstName() {
		boolean exceptionFound = false;
				
		// Given 
		String firstName = null;
		String lastName = "Bloggs";
		
		// When
		try {
			phoneBook.findPerson(firstName, lastName);
			fail();
		} catch (IllegalArgumentException e) {
			exceptionFound = true;
		}
		
		// Then
		assertTrue(exceptionFound);
	}
	
	
	@Test
	public void addPersonInvalidName() {
		boolean exceptionFound = false;
				
		// Given 
		Person person = new Person("Fred Bloggs", "(111) 123-4567", "1234 Example Street, Royal Oak, MI");
		person.setName(null);
		
		// When
		try {
			phoneBook.addPerson(person);
			fail();
		} catch (IllegalArgumentException e) {
			exceptionFound = true;
		}
		
		// Then
		assertTrue(exceptionFound);
	}
	
	
	@Test
	public void createPersonInvalidName() {
		boolean exceptionFound = false;
		
		// Given 
		String name = null;
		
		// When
		try {
			new Person(name, "(111) 123-4567", "1234 Example Street, Royal Oak, MI");
			fail();
		} catch (IllegalArgumentException e) {
			exceptionFound = true;
		}
		
		// Then
		assertTrue(exceptionFound);
	}
	
	// TODO these are basic test and lots more could be created to get valued test and more coverage.
	
}
