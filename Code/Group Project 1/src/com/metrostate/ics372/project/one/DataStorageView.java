package com.metrostate.ics372.project.one;

import java.util.Scanner;

/**
 * Displays the storage information to the user
 * @author adamv
 *
 */
public class DataStorageView {
	private DataStorage dataStorageController = new DiskStorageController();
	
	
	private void pressEnterKeyToContinue() {
		System.out.println("Press ENTER key to continue...");
		try {
			System.in.read();
		}catch(Exception e) {
			//ignore any exceptions
		}
	}
	
	public void retrieveData() {
		Status status = dataStorageController.loadData();
		TheaterApplication.clearPage();
		switch(status) {
		case OK:
			System.out.println("Load was successful");
			pressEnterKeyToContinue();
			break;
		case FILE_NOT_FOUND:
			System.out.println("Load was unsuccessful!  The data file was not found.");
			pressEnterKeyToContinue();
			break;
		case ERROR_LOADING_FROM_DISK:
			System.out.println("Load was unsuccessful!  Could not read data from the disk.");
			pressEnterKeyToContinue();
			break;
		case UNKNOWN_CLASS:
			System.out.println("Load was unsuccessful!  Unknown data type was found in the file.");
			pressEnterKeyToContinue();
			break;
		case UNKNOWN_OBJECT_TYPE_FROM_DISK:
			System.out.println("Load was unsuccessful!  Data was loading from disk, but could not determine the type of data.");
			pressEnterKeyToContinue();
			break;
		default:
			System.out.println("Load was unsuccessful.  Uknown status of " + status + " was detected." );
			pressEnterKeyToContinue();
		}
		
	}
	
	public void retrieveOnStartup() {
		if(dataStorageController.doesDataExist()) {
			Scanner scanner = new Scanner(System.in);
			String option;
			while(true) {
				TheaterApplication.clearPage();
				System.out.println("Data was found to load.  Do you want to load the data? (y or n)");
				System.out.println("Please type an option and hit enter:");
				
				option = scanner.next();
				if(option.equalsIgnoreCase("Y")) {
					retrieveData();
					return;
				}else if(option.equalsIgnoreCase("N")) {
					return;
				}else {
					System.out.println("The entry of " + option + " is an invalid option.");
					pressEnterKeyToContinue();
				}
			}
		}
	}
	
	public void storeData() {
		Status status = dataStorageController.saveData();
		TheaterApplication.clearPage();
		switch(status) {
		case OK:
			System.out.println("Save was successful");
			pressEnterKeyToContinue();
			break;
		case FILE_NOT_FOUND:
			System.out.println("Save was unsuccessful!  The data file was not found.");
			pressEnterKeyToContinue();
			break;
		case ERROR_WRITING_TO_DISK:
			System.out.println("Save was unsuccessful!  Could not save to the disk.");
			pressEnterKeyToContinue();
			break;
		case ERROR_EMPTYING_DATA_FILE:
			System.out.println("Save was unsuccessful!  Could empty the data file of old data.");
			pressEnterKeyToContinue();
			break;
			
		default:
			System.out.println("Save was unsuccessful.  Uknown status of " + status + " was detected." );
			pressEnterKeyToContinue();
		}
	}

}
