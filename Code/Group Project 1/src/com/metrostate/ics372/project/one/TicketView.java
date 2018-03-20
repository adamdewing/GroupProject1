package com.metrostate.ics372.project.one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class TicketView extends BaseView{

    private TicketList ticketList;

    public TicketView(){
        ticketList = TicketList.instance();
    }

    
    public void sellTickets(TicketType ticketType) {
    	Scanner scanner = new Scanner(System.in);
    	TheaterApplication.clearPage();
    	boolean validDataFlag = false;
    	String showId = null;
    	Date showDate = null;
    	int numberOfTickets = 0;
    	Show show = null;
    	
    	System.out.println("Sell " + ticketType);
    	do {
    		System.out.println("Enter in show id:");
    		showId = scanner.nextLine();
    		
    		show = ShowList.instance().find(showId);
    		if(show == null) {
    			System.out.println("Invalid show id entered!");
    			pressEnterKeyToContinue();
    		}else {
    			validDataFlag = true;
    		}
    	}while(!validDataFlag);
    	
    	
    	
    	validDataFlag = false;
    	do {
    		System.out.println(show.getShowName() + " runs from " + show.getStartDate() + " - " + show.getEndDate());
    		System.out.println("Enter in date(mm/dd/yyyy):");
    		String value = scanner.nextLine();
    		showDate = convertStringToDate(value);
    		if(showDate != null) {
    			if(isValidTicketDate(showDate, show)) {
    				validDataFlag = true;
    			}else {
    				System.out.println("Invalid show date entered!");
    				pressEnterKeyToContinue();
    			}
    		}
    	}while(!validDataFlag);
    	
    	validDataFlag = false;
    	do {
    		System.out.println("Enter number of tickets to sell:");
    		String value = scanner.nextLine();
    		try {
    			numberOfTickets = Integer.parseInt(value);
    			validDataFlag = true;
    		}catch (NumberFormatException nfe) {
    			System.out.println("Value must be a number!");
    		}
    	}while(!validDataFlag);
    	
    	Client client = ClientList.instance().find(show.getClientId());
    	//TODO probably should round to the nearest penny
    	double ticketCost = show.getTicketPrice() * ticketType.getPercent() / 100;
    	
    	for(int i = 0; i < numberOfTickets; i++) {
    		Ticket ticket = new Ticket(showId, showDate, ticketType);
    		ticketList.add(ticket);
    		client.setBalance(client.getBalance() + ticketCost / 2);
    		Theater.instance().setMoney(Theater.instance().getMoney() + ticketCost / 2);
    		System.out.println("Ticket with serial number " + ticket.getId() + " was created.");
    	}
    	pressEnterKeyToContinue();
    	return;
    }
    
    public void printAllTicketsByDate(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();

        System.out.println("Please enter a date (: ");
        String targetDate = scanner.nextLine();

        ticketList.printAllTicketsByDate(targetDate);
    }
    
    
    private Date convertStringToDate(String value) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		sdf.setLenient(false);
		Date date;
		try {
			date = sdf.parse(value);
		} catch (ParseException e) {
			System.out.println("Invalid date, please enter the date in the following format: mm/dd/yyyy ");
			pressEnterKeyToContinue();
			return null;
		}
		return date;
    }
    
    private boolean isValidTicketDate(Date date, Show show) {
    	if(date.equals(show.getStartDate()) || date.equals(show.getEndDate())
    			|| (date.after(show.getStartDate()) && date.before(show.getEndDate()))) {
    		return true;
    	}else {
    		return false;
    	}
    }
}
