package com.metrostate.ics372.project.one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

/**
 * This class displays the main menu for client functions
 * 
 * @author xiong
 *
 */
public class ClientView {
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_REMOVED = 2;
	public static final int CLIENT_SHOW_SCHEDULED = 3;
	public static final int OPERATION_FAILED = 4;
	private ClientList clientList;
	private ShowList showList;

	public ClientView() {
		showList = ShowList.instance();
		clientList = ClientList.instance();
	}

	public void addClient() {
		TheaterApplication.clearPage();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter client name: ");
		String newName = scanner.nextLine();
		System.out.println("Enter client address: ");
		String newAddress = scanner.nextLine();
		System.out.println("Enter client phone number (include area code): ");
		String newPhone = scanner.nextLine();
		Client newClient = new Client(newName, newAddress, newPhone);
		clientList.add(newClient);
		System.out.println(
				"New Client Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + newClient.toString());
		pressEnterKeyToContinue();
	}

	public void addShow() {
		Date startDate;
		Date endDate;
		boolean isConflicted = true;
		String name;
		
		TheaterApplication.clearPage();
		Scanner scanner = new Scanner(System.in);
		System.out.println("Client ID: ");
		String id = scanner.nextLine();
		Client client = clientList.find(id);
		if (client == null) {
			System.out.println("Client does not exist.");
			return;
		} else {

			System.out.println("Enter name of show for " + client.getName() + ": ");
			name = scanner.nextLine();

			do {
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
				
				Show show = showDuringSamePeriod(startDate, endDate);
				if (show != null) {
					System.out.println("The entered dates conflict with the following show:");
					System.out.println(show.toString());
					System.out.println("Please pick different dates.");
				}else {
					isConflicted = false;
				}

			} while (isConflicted);

		}
		Show show = new Show(name, id, startDate, endDate);
		client.setScheduled(true);
		showList.add(show);
		System.out.println("Show Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + show.toString());
		pressEnterKeyToContinue();

	}


	/**
	 * Checks to see if there is another show playing during the same time period as
	 * the passed in show. If there is, the conflicting show is returned back to the
	 * calling method.
	 * 
	 * @param startDate
	 *            starting date of the show to be checked.
	 * @param endDate
	 *            ending date of the show to be checked.
	 * @return If no conflicts null will be returned, otherwise the conflicting show
	 *         will be returned back.
	 */
	private Show showDuringSamePeriod(Date startDate, Date endDate) {
		List<Show> shows = ShowList.instance().getAll();
		for(Show show : shows) {
			if(startDate.after(show.getStartDate()) && startDate.before(show.getEndDate())) {
				return show;
			}
			if(endDate.after(show.getStartDate()) && endDate.before(show.getEndDate())) {
				return show;
			}
		}
		return null;
	}

	public void listAllClients() {
		TheaterApplication.clearPage();
		System.out.println(
				TheaterApplication.LINE_SEPARATER + "\n" + "Client List\n" + TheaterApplication.LINE_SEPARATER);

		ArrayList<Client> tempClientList = (ArrayList<Client>) clientList.getAll();
		tempClientList.forEach(item -> System.out.print(item.toString()));

		System.out.println(TheaterApplication.LINE_SEPARATER);
		pressEnterKeyToContinue();
	}

	public void listAllShows() {
		TheaterApplication.clearPage();
		System.out
				.println(TheaterApplication.LINE_SEPARATER + "\n" + "Show List\n" + TheaterApplication.LINE_SEPARATER);

		ArrayList<Show> tempShowList = (ArrayList<Show>) showList.getAll();
		tempShowList.forEach(item -> System.out.print(item.toString()));

		System.out.println(TheaterApplication.LINE_SEPARATER);
		pressEnterKeyToContinue();

	}
	
	private void pressEnterKeyToContinue() {
		System.out.println("Press ENTER key to continue...");
		try {
			System.in.read();
		}catch(Exception e) {
			//ignore any exceptions
		}
	}

	public void removeClients() {
		int result;
		TheaterApplication.clearPage();
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
		pressEnterKeyToContinue();
	}

	/**
	 * Checks to make sure that the date is valid and in the future
	 * 
	 * @param date
	 * @return null if the date fails validation, otherwise a {@link Date} object.
	 */
	private Date validateDate(String date) {
		try {
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date inputDate = sdf.parse(date);
			if (inputDate.before(currentDate)) {
				System.out.println("Please enter a valid future date.");
			} else {
				return inputDate;
			}
			return null;
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			return null;
		}
	}

	/**
	 * Checks to make sure the String can be converted into a date that is after the
	 * startDate, and is in the future.
	 * 
	 * @param date
	 *            String to be converted to a date,
	 * @param startDate
	 *            the date of the start of the show.
	 * @return
	 */
	private Date validateInputDates(String date, Date startDate) {
		try {
			Date currentDate = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date inputDate = sdf.parse(date);

			// Check that date is not in the past
			if (inputDate.before(currentDate)) {
				System.out.println("Please enter a valid future date.");
				return null;
			}

			// Check to make sure end date is after start date
			if (inputDate.before(startDate)) {
				System.out.println("End date cannot be before start date.");
				return null;
			}
			return inputDate;
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			return null;
		}

	}
}
