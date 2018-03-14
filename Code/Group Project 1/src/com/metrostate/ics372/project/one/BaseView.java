package com.metrostate.ics372.project.one;

public abstract class BaseView {

	
	public void pressEnterKeyToContinue() {
		System.out.println("Press ENTER key to continue...");
		try {
			System.in.read();
		}catch(Exception e) {
			//ignore any exceptions
		}
	}
	
}
