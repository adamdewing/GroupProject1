package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Show implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String showName;
	private Date startDate;
	private Date endDate;
	private String clientId;
	private double ticketPrice;

	public Show(String name, String clientId, Date start, Date end, double ticketPrice) {
		this.ticketPrice = ticketPrice;
		this.showName = name;
		this.clientId = id;
		this.startDate = start;
		this.endDate = end;
		Random rndObj = new Random();
		int random = 10000 + rndObj.nextInt(100000 - 10000);
		id = name + random;
	}
	
	public String getClientId() {
		return clientId;
	};
	
	public Date getEndDate() {
		return endDate;
	}
	
	public String getId() {
		return id;
	}


	public String getShowName() {
		return showName;
	}
	
	public double getTicketPrice() {
		return ticketPrice;
	}

	public void setTicketPrice(double ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	public Date getStartDate() {
		return startDate;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}


	public void setId(String id) {
		this.id = id;
	}


	public void setShowName(String showName) {
		this.showName = showName;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	
	@Override
	public String toString() {
		return "Show [id=" + id + ", showName=" + showName + "ticketPrice" + ticketPrice + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", clientId=" + clientId + "]";
	}
}
