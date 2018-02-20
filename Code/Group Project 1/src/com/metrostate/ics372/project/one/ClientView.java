package com.metrostate.ics372.project.one;

import java.awt.List;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

public class ClientView implements Serializable {
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_REMOVED = 2;
	public static final int CLIENT_SHOW_SCHEDULED = 3;
	public static final int OPERATION_FAILED = 4;
	private Client client;
	private ClientList clientList;

	public ClientView() {
		client = new Client();
		clientList = ClientList.instance();
	}

	public void displayClientMenu() {
		Scanner scanner = new Scanner(System.in);
		String option;
		do {
			System.out.println();
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Theater Application");
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Client Menu");
			System.out.println("1:  Add a Client");
			System.out.println("2:  Remove a Client");
			System.out.println("3.  List All Clients");
			System.out.println("4:  Return to Main Menu");
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Please type an option and hit enter:");

			option = scanner.next();

			if (option.equals("1")) {
				addClient();
				
			} 
			else if (option.equals("2")) {
				removeClients();
			} 
			else if (option.equals("3")) {
				listAllClients();
			}
			else if (option.equals("4")) {
				// Exit to the main menu
				return;
			} else {

			}

		} while (true);
	}

	public void addClient() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter client name: ");
		String newName = scanner.nextLine();
		System.out.println("Enter client address: ");
		String newAddress = scanner.nextLine();
		System.out.println("Enter client phone number (include area code): ");
		String newPhone = scanner.nextLine();
		Client newClient = new Client(newName, newAddress, newPhone);
		ClientList.insertClient(newClient);
		if (newClient == null) {
			System.out.println("Could not add member");
		}
		System.out.println(
				"New Client Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + newClient.toString());
	}

	public void removeClients() {
		int result;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter client ID: ");
		String id = scanner.nextLine();
		result = removeClient(id);
		switch (result) {
		case ClientView.CLIENT_NOT_FOUND:
			System.out.println("Client does not exist.");
			break;
		case ClientView.CLIENT_SHOW_SCHEDULED:
			System.out.println("Client has show scheduled and cannot be removed.");
			break;
		case ClientView.CLIENT_REMOVED:
			System.out.println("Client has been removed.");
			break;
		default:
			System.out.println("Sorry an error has occured, please try again.");
		}
	}

	public int removeClient(String id) {
		Client client = clientList.search(id);
		if (client == null) {
			return (CLIENT_NOT_FOUND);
		}
		if (client.setScheduled()) {
			return (CLIENT_SHOW_SCHEDULED);
		}
		if (clientList.deleteClient(id)) {
			return (CLIENT_REMOVED);
		}
		return OPERATION_FAILED;
	}
	
	public void listAllClients() {
		ClientList list = ClientList.instance();
		list.listClient();
	}
	//
}
