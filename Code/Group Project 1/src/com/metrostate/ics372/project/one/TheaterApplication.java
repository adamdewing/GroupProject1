package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is the main class to start the theater application up. This class is in
 * charge of the startup of the application and the main (top) menu.
 *
 */
public class TheaterApplication {

	public static final String CLIENT = "Client";
	public static final String CREDIT_CARD = "Credit Card";
	public static final String CUSTOMER = "Customer";
	public static final String LINE_SEPARATER = "--------------------------------------------------";
	public static final String SHOW = "Show";
	public static final String DATA = "Data";

	private List<Client> clients = new ArrayList<Client>();
	private List<CreditCard> creditCards = new ArrayList<CreditCard>();
	private List<Customer> customers = new ArrayList<Customer>();
	

	public static void main(String[] args) {
		TheaterApplication app = new TheaterApplication();
		app.startApplication();
	}

	private void startApplication() {
		Scanner scanner = new Scanner(System.in);
		String option;
		do {
			System.out.println("Theater Application");
			System.out.println(LINE_SEPARATER);
			System.out.println("Main Menu");
			System.out.println("1:  " + CLIENT + " functions.");
			System.out.println("2:  " + CUSTOMER + " functions.");
			System.out.println("3:  " + DATA + " functions.");
			System.out.println("4:  Exit Application.");
			System.out.println(LINE_SEPARATER);
			System.out.println("Please type an option and hit enter:");

			option = scanner.next();
			
			if (option.equals("1")) {
				ClientView cv = new ClientView();
				cv.displayClientMenu();
			} else if (option.equals("2")) {

			} else if (option.equals("3")) {
				DataView df = new DataView();
				df.displayDataMenu();
			} else if (option.equals("4")) {
				System.out.println("Exiting Application");
				System.exit(0);
			} else {

			}
		} while (true);

	}

	public List<Client> getClients() {
		return clients;
	}

	public List<CreditCard> getCreditCards() {
		return creditCards;
	}
	
	public List<Customer> getCustomers() {
		return customers;
	}
}
