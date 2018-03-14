package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.List;

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

}
