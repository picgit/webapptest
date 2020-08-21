package com.proquest.interview.phonebook;

public class Person {
	
		//Changed access to private for better encapsulation - EDW
	private String name;
	private String phoneNumber;
	private String address;
	
	
	/**
	 * Added to make record output cleaner and easier - EDW
	 */
	@Override
	public String toString() {
		return String.format("Person [name=%s, phoneNumber=%s, address=%s]", name, phoneNumber, address);
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

}
