package com.metrostate.ics372.project.one;

import java.io.Serializable;

/**
 * Contains information about the theater
 *
 */
public class Theater implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Theater theater = null;
	private double money;

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static Theater instance() {
		if (theater == null) {
			return (theater = new Theater());
		} else {
			return theater;
		}
	}
	
	public double getMoney() {
		return money;
	}

	public void setMoney(double money) {
		this.money = money;
	}
	
	
	
}
