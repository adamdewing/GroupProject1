package com.metrostate.ics372.project.one;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class BaseView {

	
	public void pressEnterKeyToContinue() {
		System.out.println("Press ENTER key to continue...");
		try {
			System.in.read();
		}catch(Exception e) {
			//ignore any exceptions
		}
	}
	
	
    protected Date convertStringToDate(String value) {
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
}
