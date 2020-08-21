package com.proquest.interview.phonebook;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.proquest.interview.util.DatabaseUtil;


public class PhoneBookImplTest {
	
	private PhoneBookImpl phoneBookImpl = null;
	
	@BeforeClass
	public static void initializeDB() throws Exception {
		DatabaseUtil.initDB();
		System.out.println("Database initialized properly");
	}
	
	@Before
	public void initializeTestMethods() throws Exception {
		phoneBookImpl = new PhoneBookImpl();
	}

	
	@Test
	public void testFindPerson() throws Exception {
		
		Person person = phoneBookImpl.findPerson("Dave", "Williams");
		assertTrue("Person object is null", person != null);
		System.out.println("Found: " + person.toString() + "\n");
	}
	
	@Test
	public void testAddPerson() throws Exception {
		
		Person newPerson = new Person();
		newPerson.setAddress("444 Main St, Kearney, MO");
		newPerson.setName("Eric Wolf");
		newPerson.setPhoneNumber("(816) 813-6828");
		
		phoneBookImpl.addPerson(newPerson);
		Person addedPerson = phoneBookImpl.findPerson("Eric", "Wolf");
		assertTrue("New Person NOT added", addedPerson != null);
		System.out.println("Added Person below was successful");
		System.out.println(addedPerson.toString() + "\n");
	}
		
	@Test
	public void testLoadPeopleFromDataStore() throws Exception {
		
		phoneBookImpl.loadPeopleFromDataStore();
		List<Person>phoneBook = phoneBookImpl.getPeople();
		assertTrue("Phone book is null", phoneBook != null);
		
		System.out.println("Phone book printed to show load people from data store was successful");
		for (Person person : phoneBook) {
			System.out.println(person.toString());
		}
		System.out.println();
	}
	
}
