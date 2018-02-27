package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Show implements Serializable{
	private String showName;
	private Date startDate;
	private Date endDate;
	private String clientId;
	private static final long serialVersionUID = 1L;
	
	public Show() {};
	
	public Show(String name, String id, Date start, Date end) {
		this.showName = name;
		this.clientId = id;
		this.startDate = start;
		this.endDate = end;
	}
	
	public String getClientId() {
		return clientId;
	}


	public void setClientId(String clientId) {
		this.clientId = clientId;
	}



	public String getShowName() {
		return showName;
	}


	public void setShowName(String showName) {
		this.showName = showName;
	}


	public Date getStartDate() {
		return startDate;
	}


	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}


	public Date getEndDate() {
		return endDate;
	}


	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	
	public String toString() {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String showInfo = '\n' + "Name: " + showName + '\n' + "Client ID: " + clientId + '\n' 
				+ "Show Dates: " + sdf.format(startDate) + " - " + sdf.format(endDate) 
					+ '\n';
		return showInfo;
	}
}
