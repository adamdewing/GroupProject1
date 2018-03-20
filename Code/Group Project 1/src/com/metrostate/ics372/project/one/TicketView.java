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

    
    private double calculateTicketPrice(Show show, TicketType ticketType) {
    	return show.getTicketPrice() * ticketType.getPercent() / 100;
    }
    
    private String getCustomerId(Scanner scanner) {
    	String customerId = null;
    	boolean validDataFlag = false;
    	do {
    		System.out.println("Enter in customer id or leave blank to see a list of customers:");
    		customerId = scanner.nextLine();
    		if(customerId.equals("")) {
    	        List<Customer> tempCustomerList = CustomerList.instance().getAll();
    	        tempCustomerList.forEach(item -> System.out.print(item.toString()));
    	        System.out.println(TheaterApplication.LINE_SEPARATER);
    		}else {
    			if(CustomerList.instance().find(customerId) == null) {
    				System.out.println("Invalid customer id entered!");
    				pressEnterKeyToContinue();
    				
    			}else {
    				validDataFlag = true;
    			}
    		}
    		
    	}while(!validDataFlag);
    	
    	return customerId;
    }
    
    
    private int getNumberOfTicketsToSell(Scanner scanner) {
    	int numberOfTickets = 0;
    	boolean validDataFlag = false;
    	
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
    	
    	return numberOfTickets;
    }
    
    private Show getShow(Scanner scanner) {
    	String showId = null;
    	Show show = null;
    	boolean validDataFlag = false;
    	
    	validDataFlag = false;
    	do {
    		System.out.println("Enter in show id or leave blank to see a list of shows:");
    		showId = scanner.nextLine();
    		
    		if(showId.equals("")) {
    			List<Show> showList = ShowList.instance().getAll();
    			showList.forEach(item -> System.out.println(item.toString()));
    			System.out.println(TheaterApplication.LINE_SEPARATER);
    		}else {
    			show = ShowList.instance().find(showId);
    			if(show == null) {
    				System.out.println("Invalid show id entered!");
    				pressEnterKeyToContinue();
    			}else {
    				validDataFlag = true;
    			}
    		}
    	}while(!validDataFlag);
    	
    	return show;
    }
    
    private Date getTicketDate(Show show, Scanner scanner) {
    	Date ticketDate = null;
    	boolean validDataFlag = false;
    	
    	validDataFlag = false;
    	do {
    		System.out.println(show.getShowName() + " runs from " + show.getStartDate() + " - " + show.getEndDate());
    		System.out.println("Enter in date(mm/dd/yyyy):");
    		String value = scanner.nextLine();
    		ticketDate = convertStringToDate(value);
    		if(ticketDate != null) {
    			if(isValidTicketDate(ticketDate, show)) {
    				validDataFlag = true;
    			}else {
    				System.out.println("Invalid show date entered!");
    				pressEnterKeyToContinue();
    			}
    		}
    	}while(!validDataFlag);
    	
    	return ticketDate;
    }
    
    private boolean isValidTicketDate(Date date, Show show) {
    	if(date.equals(show.getStartDate()) || date.equals(show.getEndDate())
    			|| (date.after(show.getStartDate()) && date.before(show.getEndDate()))) {
    		return true;
    	}else {
    		return false;
    	}
    }
    
    public void printAllTicketsByDate(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();

        System.out.println("Please enter a date (mm/dd/yyyy): ");
        String targetDate = scanner.nextLine();
        
        Date date = convertStringToDate(targetDate);
        if(date == null) {
        	System.out.println("Invalid date entered!");
        }else {
        	ticketList.printAllTicketsByDate(date);
        }
        pressEnterKeyToContinue();
    }
    
    public void sellTickets(TicketType ticketType) {
    	String customerId = null;
    	Date ticketDate = null;
    	int numberOfTickets = 0;
    	Show show = null;
    	
    	Scanner scanner = new Scanner(System.in);
    	TheaterApplication.clearPage();
    	
    	System.out.println("Sell " + ticketType + " tickets");
    	System.out.println(TheaterApplication.LINE_SEPARATER);

    	// Get information from the user
    	customerId = getCustomerId(scanner);
    	show = getShow(scanner);
    	ticketDate = getTicketDate(show, scanner);
    	numberOfTickets = getNumberOfTicketsToSell(scanner);
    	
    	Client client = ClientList.instance().find(show.getClientId());
    	//TODO probably should round to the nearest penny
    	double ticketCost = calculateTicketPrice(show, ticketType);
    	
    	for(int i = 0; i < numberOfTickets; i++) {
    		Ticket ticket = new Ticket(customerId, show.getId(), ticketDate, ticketType);
    		ticketList.add(ticket);
    		client.updateBalance(ticketCost / 2);
    		Theater.instance().setMoney(Theater.instance().getMoney() + ticketCost / 2);
    		System.out.println("Ticket with serial number " + ticket.getId() + " was created.");
    	}
    	
    	if(ticketType == TicketType.STUDENT) {
    		System.out.println("Must show valid student id.");
    	}
    	
    	pressEnterKeyToContinue();
    	return;
    }
}
