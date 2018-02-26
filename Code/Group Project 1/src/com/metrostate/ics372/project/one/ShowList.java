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

public class ShowList implements DataAccess<Show, String>, Serializable {

	private static List<Show> shows = new LinkedList();
	private static ShowList showList;
	private static List clients = new LinkedList();
	private static ClientList clientList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ShowList() {
		clientList = ClientList.instance();
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

	/*public Client searchId(String clientId) {
		for (Iterator iterator = clients.iterator(); iterator.hasNext();) {
			Client client = (Client) iterator.next();
			if (client.getClientId().equals(clientId)) {
				return client;
			}
		}
		return null;
	}*/
	
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
			}
			else {
	
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
					endDate = validateDate(date2);
					
				} while (endDate == null);

				
			}

			Show show = new Show(name, id, startDate, endDate);
			client.setIsScheduled(true);
			shows.add(show);
			client.addToShowList(show);
			System.out.println(
					"Show Information" + '\n' + TheaterApplication.LINE_SEPARATER + '\n' + show.toString());
		}
	

	public void listAllShows() {
		for (Iterator iterator = shows.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			System.out.println(show.toString());
		}
	}

	public Date validateDate(String date) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			sdf.setLenient(false);
			Date inputDate = sdf.parse(date);
			return inputDate;
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			return null;
		}
	}

	@Override
	public Show add(Show item) {
		shows.add(item);
		return item;
	}

	@Override
	public List<Show> getAll() {
		return shows;
	}

	@Override
	public Show remove(String showName) {
		for (Iterator iterator = shows.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			if (show.getShowName().equals(showName)) {
				return show;
			}
		}
		return null;
	}

	@Override
	public void removeAll() {
		shows = new LinkedList();
	}
}
