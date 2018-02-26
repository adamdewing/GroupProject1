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
		case FILE_NOT_FOUND:
			System.out.println("Save was unsuccessful!  The data file was not found.");
			pressAnyKeyToContinue();
			break;
		case ERROR_WRITING_TO_DISK:
			System.out.println("Save was unsuccessful!  Could not save to the disk.");
			pressAnyKeyToContinue();
			break;
		case ERROR_EMPTYING_DATA_FILE:
			System.out.println("Save was unsuccessful!  Could empty the data file of old data.");
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
		case FILE_NOT_FOUND:
			System.out.println("Load was unsuccessful!  The data file was not found.");
			pressAnyKeyToContinue();
			break;
		case ERROR_LOADING_FROM_DISK:
			System.out.println("Load was unsuccessful!  Could not read data from the disk.");
			pressAnyKeyToContinue();
			break;
		case UNKNOWN_CLASS:
			System.out.println("Load was unsuccessful!  Unknown data type was found in the file.");
			pressAnyKeyToContinue();
			break;
		case UNKNOWN_OBJECT_TYPE_FROM_DISK:
			System.out.println("Load was unsuccessful!  Data was loading from disk, but could not determine the type of data.");
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
