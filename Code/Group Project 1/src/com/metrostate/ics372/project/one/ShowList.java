package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShowList implements DataAccess<Show, String>, Modified {

	private static boolean isModified = false;
	private static List<Show> shows = new ArrayList<Show>();
	private static ShowList showList;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ShowList() {
	}

	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ShowList instance() {
		if (showList == null) {
			return (showList = new ShowList());
		} else {
			return showList;
		}
	}
	
	@Override
	public Show add(Show item) {
		shows.add(item);
		isModified = true;
		return item;
	}

	@Override
	public List<Show> getAll() {
		return shows;
	}

	@Override
	public Show remove(String showName) {
		for (Iterator iterator = shows.iterator(); iterator.hasNext();) {
			Show show = (Show) iterator.next();
			if (show.getShowName().equals(showName)) {
				isModified = true;
				return show;
			}
		}
		return null;
	}

	@Override
	public void removeAll() {
		shows = new ArrayList<Show>();
		isModified = true;
	}

	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public void resetModifiedFlag() {
		isModified = false;
	}

	@Override
	public Show find(String id) {
//TODO
		return null;
	}
}
