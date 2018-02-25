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

	private List<CreditCard> creditCards = new ArrayList<CreditCard>();

	public Customer(String firstName, String lastName, String streetAddress,
					String city, String state, String zipCode, String phoneNumber,
					String creditCardNumber, String creditCardExpiration) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.streetAddress = streetAddress;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
		this.phoneNumber = phoneNumber;
		this.customerId = generateCustomerId();
		creditCards.add(new CreditCard(this.customerId, creditCardNumber, creditCardExpiration));
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

	public void addCreditCard(String customerId, String creditCardNumber,
							  String creditCardExpiration){
		creditCards.add(new CreditCard(customerId, creditCardNumber, creditCardExpiration));
	}

	public String removeCreditCard(String creditCardNumber){
		if(creditCards == null){
			return "Failed to remove card. No credit cards on file";
		}

		if(creditCards.size() == 1){
			return "Failed to remove card. Must have at least one credit card on" +
					"file";
		} else {
			for(int i = 0; i < creditCards.size(); i++){
				if(creditCards.get(i).getCreditCardNumber().equals(creditCardNumber)){
					creditCards.remove(i);
					return "Successfully removed credit card";
				}
			}
		}

		return "Failed to remove card. Credit card not found.";
	}

	public String toString(){
		String customerInformation = "";
		customerInformation += "Customer ID: " + this.customerId + "\n" +
				"Name: " + this.firstName + " " + this.lastName + "\n" +
				"Address: " + this.streetAddress + " " + this.city + ", " +
				this.state + " " + this.zipCode + "\n" +
				"Phone: " + this.phoneNumber + "\n";
		for(int i = 0; i < creditCards.size(); i++){
			customerInformation += "Credit Card " + i+1 + ":\n" +
					creditCards.get(i).toString();
		}

		return customerInformation;
	}
}
