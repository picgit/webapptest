package com.proquest.interview.phonebook;


/**
 * This class processes the interview assignment tasks.
 * 
 * @author Eric Wolf
 */
public class AssignmentProcessor {

	private PhoneBookImpl phoneBookImpl = new PhoneBookImpl();
	
	/**
	 * Runs all the assignment methods
	 */
	public void runAssignment() {
		initializeAssignment();
		
		addAssignmentEntries1();
		System.out.println("Print phone book (people) after task #1");
		for (Person person : phoneBookImpl.getPeople()) {
			System.out.println(person.toString());
		}
		System.out.println();
		
		System.out.println("Print phone book for task #2 (Should be kept in sync with previous printout)");
		printPhoneBook2();
		System.out.println();
		
		processAssignment3();
	}
	
	/**
	 * Do initialization tasks
	 */
	protected void initializeAssignment() {
		phoneBookImpl.loadPeopleFromDataStore();
		System.out.println("Print phone book (people) after initialization");
		for (Person person : phoneBookImpl.getPeople()) {
			System.out.println(person.toString());
		}
		System.out.println();
	}
	
	/**
	 * Task #1 of assignment
	 */
	protected void addAssignmentEntries1() {
		Person newPerson = new Person();
		
		newPerson.setAddress("1234 Sand Hill Dr, Royal Oak, MI");
		newPerson.setName("John Smith");
		newPerson.setPhoneNumber("(248) 123-4567");
		phoneBookImpl.addPerson(newPerson);
		
		newPerson.setAddress("875 Main St, Ann Arbor, MI");
		newPerson.setName("Cynthia Smith");
		newPerson.setPhoneNumber("(824) 128-8758");
		phoneBookImpl.addPerson(newPerson);
	}
	
	/**
	 * Task #2 of assignment
	 */
	protected void printPhoneBook2() {
		for (Person person : phoneBookImpl.getPeople()) {
			System.out.println(person.toString());
		}
	}
	
	/**
	 * Task #3 of assignment
	 */
	protected void processAssignment3() {
		Person person = phoneBookImpl.findPerson("Cynthia", "Smith");
		if (person != null) {
			System.out.println("Print task #3");
			System.out.println(person.toString() + "\n");
		}
	}

}
