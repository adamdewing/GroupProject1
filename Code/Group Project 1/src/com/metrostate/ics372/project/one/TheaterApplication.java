package com.metrostate.ics372.project.one;

import java.util.Scanner;

/**
 * This is the main class to start the theater application up. This class is in
 * charge of the startup of the application and the main (top) menu.
 *
 */
public class TheaterApplication extends BaseView {

	public static final String CLIENT = "Client";
	public static final String CREDIT_CARD = "Credit Card";
	public static final String CUSTOMER = "Customer";
	public static final String LINE_SEPARATER = "--------------------------------------------------";
	public static final String SHOW = "Show";
	public static final String DATA = "Data";

	// Flags --------------------------------------------------------
	public static boolean isDebugMode = false;

	public static void clearPage() {
		for (int i = 0; i < 30; i++) {
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

	private TicketView ticketView = new TicketView();

	private void exit() {
		System.exit(0);
	}

	/**
	 * Displays a help menu to the user.
	 */
	private void help() {
		clearPage();
		System.out.println("Exit the Application: Store the data on disk and quit the application");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Add Client: The system accepts the name, address, and phone number of the client. The system\r\n"
						+ "generates a unique id and sets the balance to 0.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Remove Client. Remove a client with the given id. If a show is scheduled for the current or a\r\n"
						+ "future date for this client, the client cannot be removed.");
		System.out.println(LINE_SEPARATER);
		System.out.println("List all Clients: Print information about every client.");
		System.out.println(LINE_SEPARATER);
		System.out.println("Add Customer: The system accepts the name, address, phone number, and the number and\r\n"
				+ "expiry date of exactly one credit card. The system generates a unique id for the customer.");
		System.out.println(LINE_SEPARATER);
		System.out.println("Remove Customer: Remove a customer with the given id. All credit cards related to the\r\n"
				+ "customer are also deleted.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Add a Credit Card: The system accepts the customer id, credit card number, and expiry date and\r\n"
						+ "remembers that the credit card belongs to this customer");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Remove a Credit Card: The system accepts the credit card number and removes the information\r\n"
						+ "related to the credit card. If this is the only credit card for the customer, it refuses to delete the\r\n"
						+ "credit card information.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"List all Customers: Print information about every client, including credit card information.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Add a Show/Play: Add a new show for a client. The values accepted are the name of the show,\r\n"
						+ "the client id, the period for which the client wants the theater for this play, and the regular ticket price. The entire range\r\n"
						+ "of dates should be available, or the process fails.");
		System.out.println(LINE_SEPARATER);
		System.out.println("List all Shows: List the names and dates of all shows");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Store Data: Store all data related to the theater (everything, including customers, shows,\r\n"
						+ "clients, etc.) on disk");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Retrieve Data: Retrieve all information related to the theater. This may be done at the start of\r\n"
						+ "any session. If stored data is found, the user has the option to use it. The user may also invoke\r\n"
						+ "a command to load data, provided he/she has not yet issued any data-related commands in\r\n"
						+ "that session.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Sell regular tickets: Accept the quantity, customer id, credit card number, and the date of the show. ");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Sell advance tickets: Accept the quantity, customer id, credit card number, and the date of the show.");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Sell student advance tickets: Accept the quantity, customer id, credit card number, and the date of the show. ");
		System.out.println(LINE_SEPARATER);
		System.out.println(
				"Pay client: Accept the client id and display the balance. Ask for the amount to be paid to the client (which must be verified to be no more than the balance) and update the client balance.");
		System.out.println(LINE_SEPARATER);
		System.out.println("Print all tickets for a certain day: All fields of all tickets will be displayed.");
		System.out.println(LINE_SEPARATER);
		pressEnterKeyToContinue();

	}

	private void startApplication() {
		dataStorageView.retrieveOnStartup();

		Scanner scanner = new Scanner(System.in);
		String option;
		while (true) {
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
			System.out.println("13:  Sell regular tickets:");
			System.out.println("14:  Sell advance tickets:");
			System.out.println("15:  Sell student advance tickets:");
			System.out.println("16:  Pay client:");
			System.out.println("17:  Print all tickets for a certain day:");
			System.out.println("18:  Help:");
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
				// Sell regular tickets
			} else if (option.equals("14")) {
				// Sell advance tickets
			} else if (option.equals("15")) {
				// Sell advance student tickets
			} else if (option.equals("16")) {
				clientView.payClient();
			} else if (option.equals("17")) {
				ticketView.printAllTicketsByDate();
			} else if (option.equals("18")) {
				// Help
				help();
			} else {
				System.out.println("The entry of " + option + " is an invalid option.");
				pressEnterKeyToContinue();
			}
		}

	}

}
