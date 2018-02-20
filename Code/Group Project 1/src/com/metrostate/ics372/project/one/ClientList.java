package com.metrostate.ics372.project.one;

import java.io.IOException;
import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class ClientList implements Serializable{

	  private static final long serialVersionUID = 1L;
	  private static List clients = new LinkedList();
	  private static ClientList clientList;
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
	   * @param clientId the id of the client
	   * @return true if client exists
	   * 
	   */
	  public Client search(String clientId) {
	    for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
	      Client client = (Client) iterator.next();
	      if (client.getClientId().equals(clientId)) {
	        return client;
	      }
	    }
	    return null;
	  }
	  /**
	   * Inserts a client into the collection
	   * @param client the client to be inserted
	   * @return true iff the client could be inserted. Currently always true
	   */
	  public static boolean insertClient(Client client) {
	    clients.add(client);
	    return true;
	  }
	  /**
	   * Removes client from clients collection
	   * @param bookId
	   * @return
	   */
	  
	  public boolean deleteClient(String id) {
		    Client client = search(id);
		    if (id == null) {
		      return false;
		    } else {
		      return clients.remove(client);
		    }
		  }
	  /*
	   * Supports serialization
	   * @param output the stream to be written to
	   */
	  private void writeObject(java.io.ObjectOutputStream output) {
	    try {
	      output.defaultWriteObject();
	      output.writeObject(clientList);
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    }
	  }
	  /*
	   * Supports serialization
	   *  @param input the stream to be read from
	   */
	  private void readObject(java.io.ObjectInputStream input) {
	    try {
	      if (clientList != null) {
	        return;
	      } else {
	        input.defaultReadObject();
	        if (clientList == null) {
	          clientList = (ClientList) input.readObject();
	        } else {
	          input.readObject();
	        }
	      }
	    } catch(IOException ioe) {
	      ioe.printStackTrace();
	    } catch(ClassNotFoundException cnfe) {
	      cnfe.printStackTrace();
	    }
	  }
	  /** String form of the collection
	  * 
	  */
	  @Override
	  public String toString() {
	    return clients.toString();
	  }
	  
		public void listClient() {
			for (Iterator iterator = clients.iterator(); iterator.hasNext(); ) {
			      Client client = (Client) iterator.next();
			      System.out.println(client.toString());
			      }
			    }
		
}
