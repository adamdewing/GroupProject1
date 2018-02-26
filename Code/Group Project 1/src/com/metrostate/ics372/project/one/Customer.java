package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * This class holds data relating to customers.
 *
 */
public class Customer implements Serializable{
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String phoneNumber;
	private String customerId;
	private static final long serialVersionUID = 4915L;

	public Customer(String firstName, String lastName, String streetAddress,
					String city, String state, String zipCode, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.customerId = generateCustomerId();
	}

	public String getCustomerId() {
		return customerId;
	}

	private String generateCustomerId(){
		Random random = new Random();
		int randomNumber = 1000 + random.nextInt(10000 - 1000);
		return this.firstName.substring(0,1) +
				this.lastName.substring(0, 1) +
				this.phoneNumber.substring(this.phoneNumber.length() - 4,
						this.phoneNumber.length()) +
				new Random().nextInt(1000000 - 100000);
	}

	public String toString(){

		return "Customer ID: " + this.customerId + "\n" +
				"Name: " + this.firstName + " " + this.lastName + "\n" +
				"Address: " + this.streetAddress + " " + this.city + ", " +
				this.state + " " + this.zipCode + "\n" +
				"Phone: " + this.phoneNumber + "\n";
	}
}
