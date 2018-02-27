package com.metrostate.ics372.project.one;

import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * This class holds data relating to clients.
 *
 */
public class Client implements Serializable{

	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String phoneNumber;
	private double balance;
	private String clientId;
	boolean isScheduled;
	
	public Client() {};
	
	public Client(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.balance = 0;
		Random rndObj = new Random();
		int random = 10000 + rndObj.nextInt(100000 - 10000);
		this.clientId = random + phoneNumber.substring(3, 6);
		this.isScheduled = false;
	};
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	public String getClientId() {
		return clientId;
	}

	public String toString() {
		String clientInfo = '\n' + "Name: " + name + '\n' + "Address: " + address + '\n' 
				+ "Phone Number: " + phoneNumber + '\n' + "Balance: " + balance
					+ '\n' + "ID: " + clientId + '\n';
		return clientInfo;
	}

	public boolean isScheduled() {
		return isScheduled;
	}

	public void setScheduled(boolean isScheduled) {
		this.isScheduled = isScheduled;
	}
	
	
}
