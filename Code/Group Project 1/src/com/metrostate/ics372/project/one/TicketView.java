package com.metrostate.ics372.project.one;

import java.util.List;
import java.util.Scanner;

public class TicketView extends BaseView{

    private TicketList ticketList;

    public TicketView(){
        ticketList = TicketList.instance();
    }

    public void printAllTickets(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();

        System.out.println("Please enter a date: ");
        String targetDate = scanner.nextLine();

        
    }
}
