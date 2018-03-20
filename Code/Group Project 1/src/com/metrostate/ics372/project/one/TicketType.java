package com.metrostate.ics372.project.one;

/**
 * 	Holds the different types of Tickets.
 *
 */
public enum TicketType {
	REGULAR(100),
	ADVANCE(70),
	STUDENT(50);
	
	private final int percent;
	
	private TicketType(int percent) {
		this.percent = percent;
	}
	
	public int getPercent() {
		return percent;
	}
}
