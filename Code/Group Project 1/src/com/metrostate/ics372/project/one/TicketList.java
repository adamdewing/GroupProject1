package com.metrostate.ics372.project.one;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TicketList implements DataAccess<Ticket, String>, Modified {

	private static TicketList ticketList;
	public static List<Ticket> tickets = new ArrayList<Ticket>();

	public static TicketList instance() {
		if (tickets == null) {
			return (ticketList = new TicketList());
		} else {
			return ticketList;
		}
	}
	
	@Override
	public boolean isModified() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void resetModifiedFlag() {
		// TODO Auto-generated method stub

	}

	@Override
	public Ticket add(Ticket item) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket find(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Ticket> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ticket remove(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Method which prints all tickets for a specific date.
	 * @param date Date parameter which represents the target date.
	 */
	public void printAllTicketsByDate(String date) {
		Date targetDate;

		try {
			targetDate = new SimpleDateFormat("mm/dd/yyyy").parse(date);

			for(int i = 0; i < tickets.size(); i++){
				if(targetDate.equals(tickets.get(i).getShowDate())){
					System.out.println(tickets.get(i).toString());
				}
			}

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
