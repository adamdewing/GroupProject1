package com.metrostate.ics372.project.one;

import java.io.IOException;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.text.ParseException;

public class ShowList implements Serializable {

	private static final long serialVersionUID = 1L;
	private static List shows = new LinkedList();
	private static ShowList showList;
	private static List clients = new LinkedList();
	private static ClientList clientList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ShowList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ShowList instance() {
		if (showList == null) {
			return (showList = new ShowList());
		} else {
			return showList;
		}
	}

	public void addShow() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Client ID: ");
		//Show show = new Show();
		String id = scanner.nextLine();
		Date startDate;
		Date endDate;
		for (Iterator iterator = clients.iterator(); iterator.hasNext();) {
			Client client = (Client) iterator.next();
			if (client.getClientId().equals(id)) {
				System.out.println("Enter name of show for " + client.getName() + ": ");
				String name = scanner.nextLine();
				do {
				System.out.println("Enter start date(mm-dd-yyyy): ");
				String strDate = scanner.nextLine();
				startDate = validateDate(strDate);
			}while (startDate == null);
				do {
				System.out.println("Enter end date(mm-dd-yyyy): ");
				String strDate = scanner.nextLine();
				endDate = validateDate(strDate);
			}while (endDate == null);
				Show show = new Show(name, id, startDate, endDate);
				client.setIsScheduled(true);
				shows.add(show);
		}
		}
	}


	/*
	 * System.out.println("Enter client address: "); String newAddress =
	 * scanner.nextLine();
	 * System.out.println("Enter client phone number (include area code): "); String
	 * newPhone = scanner.nextLine(); Client newClient = new Client(newName,
	 * newAddress, newPhone); ClientList.insertClient(newClient);
	 * System.out.println("New Client Information" + '\n' +
	 * TheaterApplication.LINE_SEPARATER + '\n' + newClient.toString()); }
	 */

	public Date validateDate(String date){
				
				/*if (date.trim().equals("")) {
					return false;
				}
				else {*/
					try {
						SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
						sdf.setLenient(false);
						Date inputDate = sdf.parse(date);
						return inputDate;
					}
					catch(ParseException e) {
						System.out.println("Invalid date, please enter the date in the following format: mm-dd-yyyy ");
						return null;
					}	
				}
	}
	



