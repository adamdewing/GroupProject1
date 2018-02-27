package com.metrostate.ics372.project.one;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class DiskStorageController implements DataStorage {

	private static final String FILE_NAME = "theater.data";

	@Override
	public boolean doesDataExist() {
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			fis.close();
		} catch (FileNotFoundException e) {
			return false;
		} catch (IOException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
	
	/**
	 * Empties the file used for data storage
	 * @return status of the operations
	 */
	private Status emptyFile() {
		FileInputStream fis;
		Status status = Status.OK;
		
		try {
			fis = new FileInputStream(FILE_NAME);
			fis.close();
		} catch (FileNotFoundException e) {
			status = Status.FILE_NOT_FOUND;
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
		} catch (IOException e) {
			status = Status.ERROR_EMPTYING_DATA_FILE;
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
		}
		return status;
	}

	/**
	 * Checks to see if any of the data has been modified.
	 * @return true if the data has been modified in the session.
	 */
	private boolean isDataModified() {
		if(CustomerList.instance().isModified() || CreditCardList.instance().isModified()) {
			return true;
		}
		//TODO check for Clients, and Shows
		return false;
	}

	@Override
	public Status loadData() {
		Status status = null;
		ObjectInputStream ois = null;
		boolean isInputStreamClosed = false;
		
		if(isDataModified()) {
			return Status.UNSAVED_DATA_IN_SESSION;
		}

		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			List<Object> list = readFromDisk(ois);
			status = loadObjectsIntoMemory(list);
			ois.close();
			isInputStreamClosed = true;

		} catch (FileNotFoundException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			status = Status.FILE_NOT_FOUND;
		} catch (IOException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			status = Status.ERROR_LOADING_FROM_DISK;
		} catch (ClassNotFoundException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			status = Status.UNKNOWN_CLASS;
		} finally {
			if (ois != null && !isInputStreamClosed) {
				try {
					ois.close();
				} catch (IOException e) {
					// Ignore this case, because if we got this far there was already an error and
					// it was already handled.
				}
			}
		}
		resetIsModifiedFlags();
		return status;
	}

	private Status loadObjectsIntoMemory(List<Object> list) {
		List<Customer> customers = new ArrayList<Customer>();
		List<CreditCard> creditCards = new ArrayList<CreditCard>();
		List<Client> clients = new ArrayList<Client>();
		List<Show> shows = new ArrayList<Show>();

		// Put items in lists first. That way if there is an error, we don't partially
		// load data into memeory
		for (Object obj : list) {
			if (obj.getClass() == Customer.class) {
				customers.add((Customer) obj);
			} else if (obj.getClass() == CreditCard.class) {
				creditCards.add((CreditCard) obj);
			} else if (obj.getClass() == Client.class) {
				clients.add((Client) obj);
			} else if (obj.getClass() == Show.class) {
				shows.add((Show) obj);
			} else {
				return Status.UNKNOWN_OBJECT_TYPE_FROM_DISK;
			}
		}

		//Clear out data in memeory
		CustomerList.instance().removeAll();
		ClientList.instance().removeAll();
		CreditCardList.instance().removeAll();
		ShowList.instance().removeAll();
		
		//Load data into memory now that we have it loaded from disk
		//Add Customer objects
		for(Customer customer : customers) {
			CustomerList.instance().add(customer);
		}
		
		//Add Client objects
		for(Client client : clients) {
			ClientList.instance().add(client);
		}
		
		//Add CreditCard objects
		for(CreditCard creditCard : creditCards) {
			CreditCardList.instance().add(creditCard);
			System.out.println(creditCard.toString());
		}
		
		//Add Show objects
		for(Show show : shows) {
			ShowList.instance().add(show);
		}
		
		return Status.OK;
	}

	/**
	 * Read all objects from disk and place them in a {@link List};
	 * 
	 * @param ois
	 * @return
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private List<Object> readFromDisk(ObjectInputStream ois) throws ClassNotFoundException, IOException {
		List<Object> list = new ArrayList<Object>();
		Object obj = null;
		boolean isEndOfFile = false;

		do {
			try {
				obj = ois.readObject();
				list.add(obj);
			} catch (EOFException e) {
				//normal exception when the end of file is reached.
				isEndOfFile = true;
			}
		} while (!isEndOfFile);

		return list;
	}

	
	private void resetIsModifiedFlags() {
		CustomerList.instance().resetModifiedFlag();
		CreditCardList.instance().resetModifiedFlag();
	}

	@Override
	public Status saveData() {
		Status status;
		ObjectOutputStream oos = null;
		boolean isOutputStreamClosed = false;
		
		emptyFile();
		
		try {
			List<Customer> customers = CustomerList.instance().getAll();
			List<CreditCard> creditCards = CreditCardList.instance().getAll();
			List<Client> clients = ClientList.instance().getAll();
			List<Show> shows = ShowList.instance().getAll();

			// Write objects to disk
			FileOutputStream fos = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fos);
			writeToDisk(customers, oos);
			writeToDisk(creditCards, oos);
			writeToDisk(clients, oos);
			writeToDisk(shows, oos);
			oos.close();
			isOutputStreamClosed = true;
			status = Status.OK;
		} catch (FileNotFoundException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			status = Status.FILE_NOT_FOUND;
		} catch (IOException e) {
			if(TheaterApplication.isDebugMode) {
				e.printStackTrace();
			}
			status = Status.ERROR_WRITING_TO_DISK;
		} finally {
			if (oos != null && !isOutputStreamClosed) {
				try {
					oos.close();
				} catch (IOException e) {
					// Ignore this case, because if we got this far there was already an error and
					// it was already handled.
				}
			}
		}

		resetIsModifiedFlags();
		return status;
	}

	private <E> void writeToDisk(List<E> list, ObjectOutputStream oos) throws IOException {
		for (E item : list) {
			oos.writeObject(item);
		}
	}
}
