package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * This class holds data relating to clients.
 *
 */
public class Client implements Serializable{

	private String name;
	private String address;
	private static String phoneNumber;
	private double balance;
	private static int iD = 100;
	private static String clientId;
	private Date performanceDate;
	private boolean scheduled;
	
	public Client() {};
	
	public Client(String name, String address, String phoneNumber) {
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.balance = 0;
		setClientId();
		setScheduled();
		iD++;
	};
	
	private static void setClientId() {
		Random rndObj = new Random();
		int random = 1000 + rndObj.nextInt(10000 - 1000);
		clientId = iD + random + phoneNumber.substring(3, 6);
		//System.out.println(clientId);
	}
	
	

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

	public static String getPhoneNumber() {
		return phoneNumber;
	}

	public static void setPhoneNumber(String phoneNumber) {
		Client.phoneNumber = phoneNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public static String getClientId() {
		return clientId;
	}

	public String toString() {
		String clientInfo = "Name: " + name + '\n' + "Address: " + address + '\n' 
				+ "Phone Number: " + phoneNumber + '\n' + "Balance: " + balance
					+ '\n' + "ID: " + clientId;
		return clientInfo;
	}
	
	public boolean setScheduled() {
		if (performanceDate != null) {
			scheduled = true;
		}
		else 
			scheduled = false;
		return scheduled;
	}
	public static void main(String args[]) {
		Client client1 = new Client("Clint Capela", "4123 Houston Way", "5082234584");
		Client client2 = new Client("Dan Haren", "1523 Marlin Street", "1522836875");
		
	}
}
