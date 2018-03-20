package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

/**
 * 	Holds ticket information.
 *
 */
public class Ticket implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String id;
	private String customerId;
	private String showId;
	private Date showDate;
	private TicketType type;
	
	public Ticket(String customerId, String showId, Date showDate, TicketType type) {
		Random rndObj = new Random();
		int random = 10000 + rndObj.nextInt(100000 - 10000);
		this.id = String.valueOf(random);
		this.customerId = customerId;
		this.showId = showId;
		this.showDate = showDate;
		this.type = type;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getShowId() {
		return showId;
	}
	public void setShowId(String showId) {
		this.showId = showId;
	}
	public Date getShowDate() {
		return showDate;
	}
	public void setShowDate(Date showDate) {
		this.showDate = showDate;
	}
	public TicketType getType() {
		return type;
	}
	public void setType(TicketType type) {
		this.type = type;
	}
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	@Override
	public String toString() {
		return "Ticket [id=" + id + ", customerId=" + customerId + ", showId=" + showId + ", showDate=" + showDate
				+ ", type=" + type + "]";
	}
	
	
}
