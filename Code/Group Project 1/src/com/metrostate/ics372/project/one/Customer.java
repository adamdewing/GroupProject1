package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This class holds data relating to customers.
 *
 */
public class Customer {
	private String firstName;
	private String lastName;
	private String streetAddress;
	private String city;
	private String state;
	
	private int zipCode;
	private int phoneNumber;
	private int creditCardExpiration;
	
	private UUID customerId;
	
	private List<CreditCard> creditCard = new ArrayList<CreditCard>();

	public Customer(String firstName, String lastName, String streetAddress, String city, 
			String state, int zipCode, int phoneNumber, int creditCard, int creditCardExpiration) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.creditCardExpiration = creditCardExpiration;
		
		//Generate a unique id
		customerId = UUID.randomUUID();
	}
	
	
	
}
