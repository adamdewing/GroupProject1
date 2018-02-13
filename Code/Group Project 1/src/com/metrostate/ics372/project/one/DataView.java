package com.metrostate.ics372.project.one;

import java.util.Scanner;

/**
 * This class is used for presenting data related options, such as loading and
 * saving data, to the user.
 *
 */
public class DataView {

	private DataController dataController;

	public DataView() {
		dataController = new DataController();
	}

	public void displayDataMenu() {
		Scanner scanner = new Scanner(System.in);
		String option;
		do {
			System.out.println("Theater Application");
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Data Menu");
			System.out.println("1:  Save Data.");
			System.out.println("2:  Load Data");
			System.out.println("3:  Return to Main Menu.");
			System.out.println(TheaterApplication.LINE_SEPARATER);
			System.out.println("Please type an option and hit enter:");

			option = scanner.next();

			if (option.equals("1")) {
				// TODO
				System.out.println("Time to save some data!!");
				dataController.saveData();
			} else if (option.equals("2")) {
				// TODO
				System.out.println("Time to load some data!!");
				dataController.loadData();
			} else if (option.equals("3")) {
				// Exit to the main menu
				return;
			} else {

			}

		} while (true);
	}

}
