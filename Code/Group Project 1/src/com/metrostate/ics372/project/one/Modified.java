package com.metrostate.ics372.project.one;

/**
 * Will keep track if something is modified.
 *
 */
public interface Modified {

	/**
	 * Used to determine if data has been modified
	 * @return true if data has been modified (add/update/delete), otherwise false.
	 */
	public boolean isModified();
	
	/**
	 * Resets the modified flag to false.
	 */
	public void resetModifiedFlag();
}
