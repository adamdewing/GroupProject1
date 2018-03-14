package com.metrostate.ics372.project.one;

import java.util.List;

/**
 * Common interface for manipulating data.
 *
 * @param <E>
 *            the type of the element. EG Client, Customer, etc...
 * @param <K>
 *            the type of the key for the element. EG int, long, etc...
 */
public interface DataAccess<E, K> {

	/**
	 * Adds the item to the collection of items.
	 * @param item
	 */
	public E add(E item);

	/**
	 * Finds the item in the collection using the id of the item.
	 * @param id
	 * @return item or null if there were no matches.
	 */
	public E find(K id);
	
	/**
	 * Returns a List of all objects of type E.
	 * @return
	 */
	public List<E> getAll();

	/**
	 * Finds the specified object, removes it from the collection, and returns it.
	 * @param id
	 * @return returns either the object or null if the object is not found.
	 */
	public E remove(K id);

	/**
	 * Removes all the objects of this type.
	 */
	public void removeAll();
}
