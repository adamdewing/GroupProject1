package com.metrostate.ics372.project.one;

public interface DataStorage {

	/**
	 * Saves all the data in the application
	 * @return A {@link Status} object with the the outcome of the save
	 */
	public Status saveData();
	

	/**
	 * Loads all the data for the application, clearing out any old data that may have existed.
	 * If there is unsaved data in the session, an error status will be returned
	 * @return {@link Status.OK} if everything loaded ok, otherwise an error {@linkStatus}.
	 */
	public Status loadData();
}
