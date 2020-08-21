package com.proquest.interview.phonebook;

//import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.proquest.interview.util.DatabaseUtil;

public class PhoneBookImpl implements PhoneBook {
	
		//Actual Phone book of this class - EDW
		//Changed access to private for better encapsulation and added parameterized type - EDW
	private List<Person> people;
	
	/**
	 * Add an entry to the phone book.
	 */
	@Override
	public void addPerson(Person newPerson) {
		//TODO: write this method - Completed by Eric Wolf
		
		Connection connection = null;
		PreparedStatement ps = null;
		
		try {
			connection = DatabaseUtil.getConnection();
			
			String sql = "INSERT INTO PHONEBOOK (NAME, PHONENUMBER, ADDRESS) VALUES (?,?,?)";
			ps = connection.prepareStatement(sql);
			ps.setString(1, newPerson.getName().trim());
			ps.setString(2, newPerson.getPhoneNumber().trim());
			ps.setString(3, newPerson.getAddress().trim());
			
			int attectedRowCount = ps.executeUpdate();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if(ps != null) {
					ps.close();
					connection.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(connection != null)
					connection.close();
			} catch(SQLException se) {
				se.printStackTrace();
			} //end finally block
		} //end try block

		loadPeopleFromDataStore();	//Keep instance variable "people" in sync with data store
	}
	
	/**
	 * Phone book searches are done with the first name and last name, but the name is persisted 
	 * in the data store as one field. This method uses the method trim() on both name fields and 
	 * adds a "space" in between them to search the database in the correct format.
	 */
	@Override
	public Person findPerson(String pfirstName, String pLastName) {
			//TODO: write this method - Completed by Eric Wolf
		Connection connection = null;
		PreparedStatement ps = null;
		Person person = null;
		try {
				//Create database NAME field as stored in the database with separating space
			String fullName = pfirstName.trim() + " " + pLastName.trim();
			connection = DatabaseUtil.getConnection();

			String sql = "SELECT * FROM PHONEBOOK WHERE NAME = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, fullName.trim());
			
			ResultSet resultSet = ps.executeQuery();

			while(resultSet.next()) {
					//Retrieve by column name
				String name = resultSet.getString("NAME");
				String phoneNumber = resultSet.getString("PHONENUMBER");
				String address = resultSet.getString("ADDRESS");
				
				person = new Person();
				person.setName(name);
				person.setPhoneNumber(phoneNumber);
				person.setAddress(address);
			}
			resultSet.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if(ps != null) {
					ps.close();
					connection.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(connection != null)
					connection.close();
			} catch(SQLException se) {
				se.printStackTrace();
			} //end finally block
		} //end try block
		
		return person;
	}
	
	public static void main(String[] args) {
		DatabaseUtil.initDB();  // Do not remove this line, it creates the simulated database.

		// Context: the basic idea is that the phone book lives in ("is persisted to") an
		// SQL database.  For this exercise, we're using a simulated database (that really just
		// lives in memory), but pretend that it is a "real", persisted-on-disk database.
		//
		// But ALL of the data should live in-memory in an instance of
		// the PhoneBookImpl class, AS WELL AS being persisted to the database.
		
		// TODO: 1. Create these new Person objects, and put them in both PhoneBook and Database.
		//    John Smith, (248) 123-4567, 1234 Sand Hill Dr, Royal Oak, MI
		//    Cynthia Smith, (824) 128-8758, 875 Main St, Ann Arbor, MI

		// TODO 2: Print the whole phone book to System.out.
		
		// TODO 3: Find Cynthia Smith and print just her entry to System.out.
		
		// Hint: you don't have to implement these features strictly in that order.
		
		AssignmentProcessor assignmentProcessor = new AssignmentProcessor();
		assignmentProcessor.runAssignment();
		System.out.println("Assignment run complete!");
	}
	
	/**
	 * Load the "people" variable (phone book) from the data store
	 */
	public void loadPeopleFromDataStore() {
		
		Connection connection = null;
		PreparedStatement ps = null;
		List<Person> phoneBook = new ArrayList<Person>();
		try {
			connection = DatabaseUtil.getConnection();
	
			String sql = "SELECT * FROM PHONEBOOK";
			ps = connection.prepareStatement(sql);
			ResultSet resultSet = ps.executeQuery();
	
			while(resultSet.next()) {
					//Retrieve by column name
				String name = resultSet.getString("NAME");
				String phoneNumber = resultSet.getString("PHONENUMBER");
				String address = resultSet.getString("ADDRESS");
				
				Person person = new Person();
				person.setName(name);
				person.setPhoneNumber(phoneNumber);
				person.setAddress(address);
				phoneBook.add(person);
			}
			people = phoneBook;		//assign phone book from data store to people
			resultSet.close();
		} catch(SQLException se) {
			se.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			//finally block used to close resources
			try {
				if(ps != null) {
					ps.close();
					connection.close();
				}
			} catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(connection != null)
					connection.close();
			} catch(SQLException se) {
				se.printStackTrace();
			} //end finally block
		} //end try block
	}

	/**
	 * @return the List<Person> people
	 */
	public List<Person> getPeople() {
		return people;
	}
	
}
