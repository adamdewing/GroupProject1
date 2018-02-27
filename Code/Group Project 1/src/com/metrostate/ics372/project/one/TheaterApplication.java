package com.metrostate.ics372.project.one;

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

	// Flags --------------------------------------------------------
	public static boolean isDebugMode = false;
	public static void clearPage() {
		for(int i = 0; i < 30; i++) {
			System.out.println(System.lineSeparator());
		}
	}
	public static void main(String[] args) {
		TheaterApplication app = new TheaterApplication();
		app.startApplication();
	}

	private CustomerView customerView = new CustomerView();

	private ClientView clientView = new ClientView();

	private DataStorageView dataStorageView = new DataStorageView();
	private void exit() {
		System.exit(0);
	}

	private void pressEnterKeyToContinue() {
		System.out.println("Press the ENTER key to continue...");
		try {
			System.in.read();
		} catch (Exception e) {
			// ignore any exceptions
		}
	}

	private void startApplication() {
		dataStorageView.retrieveOnStartup();
		
		Scanner scanner = new Scanner(System.in);
		String option;
		while(true) {
			clearPage();
			System.out.println("Theater Application");
			System.out.println(LINE_SEPARATER);
			System.out.println("0:  Exit Application.");
			System.out.println("1:  Add " + CLIENT + ":");
			System.out.println("2:  Remove  " + CLIENT + ":");
			System.out.println("3:  List all  " + CLIENT + "s:");
			System.out.println("4:  Add  " + CUSTOMER + ":");
			System.out.println("5:  Remove  " + CUSTOMER + ":");
			System.out.println("6:  Add a  " + CREDIT_CARD + ":");
			System.out.println("7:  Remove a " + CREDIT_CARD + ":");
			System.out.println("8:  List all " + CUSTOMER + "s:");
			System.out.println("9:  Add a " + SHOW + ":");
			System.out.println("10:  List all " + SHOW + "s:");
			System.out.println("11:  Store " + DATA + ":");
			System.out.println("12:  Retrieve " + DATA + ":");
			System.out.println("13:  Help:");
			System.out.println(LINE_SEPARATER);
			System.out.println(LINE_SEPARATER);
			System.out.println("Please type an option and hit enter:");

			option = scanner.next();
			if (option.equals("0")) {
				exit();
			} else if (option.equals("1")) {
				// Add Client
				clientView.addClient();
			} else if (option.equals("2")) {
				// Remove Client
				clientView.removeClients();
			} else if (option.equals("3")) {
				// List all Clients
				clientView.listAllClients();
			} else if (option.equals("4")) {
				// Add customer
				customerView.addCustomer();
			} else if (option.equals("5")) {
				// Remove Customer
				customerView.removeCustomer();
			} else if (option.equals("6")) {
				// Add Credit Card
				customerView.addCreditCard();
			} else if (option.equals("7")) {
				// Remove Credit Card
				customerView.removeCreditCard();
			} else if (option.equals("8")) {
				// List all Customers
				customerView.listAllCustomers();
			} else if (option.equals("9")) {
				// Add Show/Play
				clientView.addShow();
			} else if (option.equals("10")) {
				// List all Shows
				clientView.listAllShows();
			} else if (option.equals("11")) {
				// Store Data
				dataStorageView.storeData();
			} else if (option.equals("12")) {
				// Load Data
				dataStorageView.retrieveData();
			} else if (option.equals("13")) {
				// Help
			} else {
				System.out.println("The entry of " + option + " is an invalid option.");
				pressEnterKeyToContinue();
			}
		}

	}

}
