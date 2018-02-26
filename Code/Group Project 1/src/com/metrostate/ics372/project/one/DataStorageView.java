package com.metrostate.ics372.project.one;

/**
 * Displays the storage information to the user
 * @author adamv
 *
 */
public class DataStorageView {
	private DataStorage dataStorageController = new DiskStorageController();
	
	
	public void storeData() {
		Status status = dataStorageController.saveData();
		switch(status) {
		case OK:
			System.out.println("Save was successful");
			pressAnyKeyToContinue();
			break;
			
		default:
			System.out.println("Save was unsuccessful.  Uknown status of " + status + " was detected." );
			pressAnyKeyToContinue();
		}
	}
	
	public void retrieveData() {
		Status status = dataStorageController.loadData();
		switch(status) {
		case OK:
			System.out.println("Load was successful");
			pressAnyKeyToContinue();
			break;
			
		default:
			System.out.println("Load was unsuccessful.  Uknown status of " + status + " was detected." );
			pressAnyKeyToContinue();
		}
		
	}
	
	private void pressAnyKeyToContinue() {
		System.out.println("Press any key to continue...");
		try {
			System.in.read();
		}catch(Exception e) {
			//ignore any exceptions
		}
	}
	
	private void exit() {
		System.exit(0);
	}

}
