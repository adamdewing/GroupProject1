package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.Date;

public class Ticket implements Serializable {

	private String id;
	private String showId;
	private Date showDate;
	private TicketType type;
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
	@Override
	public String toString() {
		return "Ticket [id=" + id + ", showId=" + showId + ", showDate=" + showDate + ", type=" + type + "]";
	}
	
	
}
