package com.metrostate.ics372.project.one;

import java.awt.List;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;

import jdk.nashorn.internal.runtime.regexp.joni.SearchAlgorithm;

/**
 * This class displays the main menu for client functions 
 * 
 * @author xiong
 *
 */
public class ClientView implements Serializable {
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_REMOVED = 2;
	public static final int CLIENT_SHOW_SCHEDULED = 3;
	public static final int OPERATION_FAILED = 4;
	private Client client;
	private ClientList clientList;
	private ShowList showList;

	public ClientView() {
		showList = ShowList.instance();
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
			System.out.println("4.  Add Showing for existing Client");
			System.out.println("5.  List All Shows");
			System.out.println("6:  Return to Main Menu");
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Please type an option and hit enter:");

			option = scanner.next();

			if (option.equals("1")) {
				clientList.addClient();
				
			} 
			else if (option.equals("2")) {
				clientList.removeClients();
			} 
			else if (option.equals("3")) {
				clientList.listAllClients();
			}
			else if (option.equals("4")) {
				showList.addShow();
			}
			else if (option.equals("5")) {
				showList.listAllShows();
			}
			else if (option.equals("6")) {
				// Exit to the main menu
				return;
			} else {

			}

		} while (true);
	}

}
