package com.metrostate.ics372.project.one;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;


/**
 * This class displays the main menu for client functions 
 * 
 * @author xiong
 *
 */
public class ClientView{
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_REMOVED = 2;
	public static final int CLIENT_SHOW_SCHEDULED = 3;
	public static final int OPERATION_FAILED = 4;
	private Client client;
	private ClientList clientList;
	private Show show;
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
				addClient();
				
			} 
			else if (option.equals("2")) {
				removeClients();
			} 
			else if (option.equals("3")) {
				listAllClients();
			}
			else if (option.equals("4")) {
				addShow();
			}
			else if (option.equals("5")) {
				listAllShows();
			}
			else if (option.equals("6")) {
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
		clientList.add(newClient);
		System.out.println("New Client Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + newClient.toString());
	}

	public void removeClients() {
		int result;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter client ID: ");
		String id = scanner.nextLine();
		result = clientList.removeClient(id);
		switch (result) {
		case CLIENT_NOT_FOUND:
			System.out.println("Client does not exist.");
			break;
		case CLIENT_SHOW_SCHEDULED:
			System.out.println("Client has show scheduled and cannot be removed.");
			break;
		case CLIENT_REMOVED:
			System.out.println("Client has been removed.");
			break;
		default:
			System.out.println("Sorry an error has occured, please try again.");
		}
	}

    public void listAllClients(){
        System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                "Client List\n" + TheaterApplication.LINE_SEPARATER);

        ArrayList<Client>tempClientList = (ArrayList<Client>) clientList.getAll();
        tempClientList.forEach(item -> System.out.print(item.toString()));

        System.out.println(TheaterApplication.LINE_SEPARATER);
    }

	public void addShow() {
		Date startDate;
		Date endDate;
		String name;
		Scanner scanner = new Scanner(System.in);
		System.out.println("Client ID: ");
		String id = scanner.nextLine();
		Client client = clientList.search(id);
		if (client == null) {
			System.out.println("Client does not exist.");
			return;
		} else {

			System.out.println("Enter name of show for " + client.getName() + ": ");
			name = scanner.nextLine();

			do {
				System.out.println("Enter start date(mm/dd/yyyy): ");
				String date1 = scanner.nextLine();
				startDate = validateDate(date1);

			} while (startDate == null);
			do {
				System.out.println("Enter end date(mm/dd/yyyy): ");
				String date2 = scanner.nextLine();
				endDate = validateInputDates(date2, startDate);

			} while (endDate == null);

		}
		Show show = new Show(name, id, startDate, endDate);
		client.setScheduled(true);
		showList.add(show);
		System.out.println("Show Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + show.toString());
		
	}

	public void listAllShows() {
        System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                "Show List\n" + TheaterApplication.LINE_SEPARATER);

        ArrayList<Show>tempShowList = (ArrayList<Show>) showList.getAll();
        tempShowList.forEach(item -> System.out.print(item.toString()));

        System.out.println(TheaterApplication.LINE_SEPARATER);
		
	}
	
	public Date validateDate(String date) {
		try {
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date inputDate = sdf.parse(date);
			if(inputDate.before(currentDate)) {
				System.out.println("Please enter a valid future date.");
			}
			else{
			return inputDate;
			}
			return null;
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			return null;
		}
	}

	public Date validateInputDates(String date, Date startDate) {
		try {
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date inputDate = sdf.parse(date);
			if(inputDate.before(currentDate)) {
				System.out.println("Please enter a valid future date.");
			}
			if(inputDate.before(startDate)) {
				System.out.println("End date cannot be before start date.");
			}
			else{
			return inputDate;
			}
			return null;
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			return null;
		}
		
	}
}

