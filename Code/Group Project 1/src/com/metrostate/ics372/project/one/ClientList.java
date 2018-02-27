package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class ClientList implements DataAccess<Client, String>, Modified {

	private static boolean isModified = false;
	private static List<Client> clients = new ArrayList<Client>();
	private static ClientList clientList;
	public static final int CLIENT_NOT_FOUND = 1;
	public static final int CLIENT_REMOVED = 2;
	public static final int CLIENT_SHOW_SCHEDULED = 3;
	public static final int OPERATION_FAILED = 4;

	/*
	 * Private constructor for singleton pattern
	 * 
	 */
	private ClientList() {
	
	}
	
	/**
	 * Supports the singleton pattern
	 * 
	 * @return the singleton object
	 */
	public static ClientList instance() {
		if (clientList == null) {
			return (clientList = new ClientList());
		} else {
			return clientList;
		}
	}

	/**
	 * Checks whether a client with a given client id exists.
	 * 
	 * @param clientId
	 *            the id of the client
	 * @return 
	 * 
	 */
	public Client search(String clientId) {
		for(int i = 0; i < clients.size(); i++){
			if(clients.get(i).getClientId().equals(clientId)){
				return clients.get(i);
			}
		}
		return null;
	}

	public boolean deleteClient(String id) {
		Client client = search(id);
		if (id == null) {
			return false;
		} else {
			isModified = true;
			return clients.remove(client);
		}
	}
	
	/**
	 * String form of the collection
	 * 
	 */
	@Override
	public String toString() {
		return clients.toString();
	}

	public int removeClient(String id) {
		Client client = clientList.search(id);
		
		if (client == null) {
			return (CLIENT_NOT_FOUND);
		}
		if (client.isScheduled == true) {
			return (CLIENT_SHOW_SCHEDULED);
		}
		if (clientList.deleteClient(id)) {
			isModified = true;
			return (CLIENT_REMOVED);
		}
		return OPERATION_FAILED;
	}

	@Override
	public Client add(Client item) {
		clients.add(item);
		isModified = true;
		return item;
	}

	@Override
	public List<Client> getAll() {
		return clients;
	}

	@Override
	public Client remove(String id) {
		deleteClient(id);
		return null;
	}

	@Override
	public void removeAll() {
		clients = new ArrayList<Client>();
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

}
