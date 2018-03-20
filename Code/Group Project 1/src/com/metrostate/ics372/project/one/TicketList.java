package com.metrostate.ics372.project.one;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

public class TicketList implements DataAccess<Ticket, String>, Modified {

	private static boolean isModified = false;
	private static TicketList ticketList;
	public static List<Ticket> tickets = new ArrayList<Ticket>();

	public static TicketList instance() {
		if (ticketList == null) {
			return (ticketList = new TicketList());
		} else {
			return ticketList;
		}
	}
	
	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public void resetModifiedFlag() {
		isModified = false;

	}

	@Override
	public Ticket add(Ticket item) {
		tickets.add(item);
		return item;
	}

	@Override
	public Ticket find(String id) {
		for(Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			if(ticket.getId().equals(id)) {
				return ticket;
			}
		}
		return null;
	}

	@Override
	public List<Ticket> getAll() {
		return tickets;
	}

	@Override
	public Ticket remove(String id) {
		for (Iterator<Ticket> iterator = tickets.iterator(); iterator.hasNext();) {
			Ticket ticket = (Ticket) iterator.next();
			if (ticket.getId().equals(id)) {
				iterator.remove();
				isModified = true;
				return ticket;
			}
		}
		return null;
	}

	@Override
	public void removeAll() {
		tickets = new ArrayList<Ticket>();
		isModified = true;
	}

	/**
	 * Method which prints all tickets for a specific date.
	 * @param date Date parameter which represents the target date.
	 */
	public void printAllTicketsByDate(Date date) {
		Date targetDate;

		System.out.println("DEBUG printAllTicketsByDate for date:" + date);
		for(int i = 0; i < tickets.size(); i++){
			if(date.equals(tickets.get(i).getShowDate())){
				System.out.println(tickets.get(i).toString());
			}else {
				System.out.println("DEBUG - " + tickets.get(i).toString());
			}
		}
	}
}
