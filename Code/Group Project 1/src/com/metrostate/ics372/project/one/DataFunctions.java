package com.metrostate.ics372.project.one;

import java.util.Scanner;

/**
 * This class is used for Data Functions, such as loading and saving data.
 *
 */
public class DataFunctions {

	public void start() {
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
				saveData();
			} else if (option.equals("2")) {
				// TODO
				System.out.println("Time to load some data!!");
				loadData();
			} else if (option.equals("3")) {
				// Exit to the main menu
				return;
			} else {

			}

		} while (true);
	}

	private void saveData() {

	}

	private void loadData() {

	}
}
