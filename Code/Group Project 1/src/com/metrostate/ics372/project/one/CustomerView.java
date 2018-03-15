/**
 * Description: This class serves as a central class to handle interactions
 * between the user and the system.
 */

package com.metrostate.ics372.project.one;

import java.util.List;
import java.util.Scanner;

public class CustomerView extends BaseView{

    private Customer customer;
    private CreditCard creditCard;
    private CustomerList customerList;
    private CreditCardList creditCardList;

    /**
     * Class constructor which creates instances of the CustomerList and
     * CreditCardList classes.
     */
    public CustomerView(){
        customerList = CustomerList.instance();
        creditCardList = CreditCardList.instance();
    }

    /**
     * Displays menu options to the user. This is a pass through method which
     * calls further methods based on the users input.
     */
    public void displayCustomerMenu(){
        Scanner scanner = new Scanner(System.in);
        String option;
        do{
            System.out.println("Theater Application");
            System.out.println(TheaterApplication.LINE_SEPARATER);
            System.out.println("Customer Menu");
            System.out.println("1:  Add Customer");
            System.out.println("2:  Remove Customer");
            System.out.println("3:  Add a Customer Credit Card");
            System.out.println("4:  Remove a Customer Credit Card");
            System.out.println("5:  List All Customers");
            System.out.println("6:  Return to Main Menu");
            System.out.println(TheaterApplication.LINE_SEPARATER);
            System.out.println("Please type an option and hit enter:");

            option = scanner.next();

            if(option.equals("1")){
                addCustomer();
            }else if (option.equals("2")){
                removeCustomer();
            }else if (option.equals("3")){
                addCreditCard();
            }else if (option.equals("4")){
                removeCreditCard();
            }else if (option.equals("5")){
                listAllCustomers();
            }else if (option.equals("6")){
                return;
            }
        }while(true);
    }

    /**
     * Method which calls the appropriate methods to add a new customer. Logic
     * for the success or failure display is handled within this method.
     */
    public void addCustomer(){
        Scanner scanner = new Scanner(System.in);

        TheaterApplication.clearPage();
        
        System.out.println("Enter first name: ");
        String customerFirstName = scanner.nextLine();

        System.out.println("Enter last name: ");
        String customerLastName = scanner.nextLine();

        System.out.println("Enter street address: ");
        String customerStreetAddress = scanner.nextLine();

        System.out.println("Enter city: ");
        String customerCity = scanner.nextLine();

        System.out.println("Enter state: ");
        String customerState = scanner.nextLine();

        System.out.println("Enter zip code: ");
        String customerZipCode = scanner.nextLine();

        System.out.println("Enter phone number including area code: ");
        String customerPhoneNumber = scanner.nextLine();

        System.out.println("Enter credit card number: ");
        String customerCreditCard = scanner.nextLine();

        System.out.println("Enter credit card expiration date: ");
        String customerCreditCardExpirationDate = scanner.nextLine();

        try {
            customer = new Customer(customerFirstName, customerLastName,
                    customerStreetAddress, customerCity, customerState, customerZipCode,
                    customerPhoneNumber);

            creditCard = new CreditCard(customer.getCustomerId(), customerCreditCard,
                    customerCreditCardExpirationDate);

            if(customerList.find(customer.getCustomerId()) != null) {
            	System.out.println("Cannot add customer as customer already exists!");
            	pressEnterKeyToContinue();
            	return;
            }else {
            	customerList.add(customer);
            }
            if(creditCardList.find(creditCard.getCreditCardNumber()) != null) {
            	System.out.println("Cannot add customer as credit card already exists in the system!");
            	pressEnterKeyToContinue();
            	return;
            }else {
            	creditCardList.add(creditCard);
            }

            System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                    "New customer added \n" + TheaterApplication.LINE_SEPARATER +
                    "\n" + customer.toString() + TheaterApplication.LINE_SEPARATER);
        } catch(Exception e) {
            System.out.println("Unable to add new customer. \nError: " + e);
        }
        pressEnterKeyToContinue();
    }

    /**
     * Method which calls the appropriate methods to remove a customer. Logic
     * for the success or failure display is handled within this method.
     */
    public void removeCustomer(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();
        System.out.println("Enter a Customer ID: ");
        String customerId = scanner.nextLine();
        Customer removedCustomer = customerList.remove(customerId);
        if(removedCustomer != null){
            System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                    "Successfully removed the following customer: \n" +
                    removedCustomer.toString() +
                    TheaterApplication.LINE_SEPARATER + "\n");
        }else{
            System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                    "Customer not found. Remove failed. \n" +
                    TheaterApplication.LINE_SEPARATER + "\n");
        }
        pressEnterKeyToContinue();
    }

    /**
     * Method which calls the appropriate methods to add a new credit card. Logic
     * for the success or failure display is handled within this method.
     */
    public void addCreditCard(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();

        System.out.println("Enter a Customer ID: ");
        String customerId = scanner.nextLine();

        System.out.println("Enter credit card number: ");
        String creditCardNumber = scanner.nextLine();

        System.out.println("Enter credit card expiration date: ");
        String creditCardExpirationDate = scanner.nextLine();

        creditCard = new CreditCard(customerId, creditCardNumber, creditCardExpirationDate);
        creditCard = creditCardList.add(creditCard);

        if(creditCard != null){
            System.out.println("Successfully added credit card");
        }else{
            System.out.println("Failed to add credit card");
        };

        System.out.println("Cards on File: \n");
        creditCardList.printAll();
        
        pressEnterKeyToContinue();
    }

    /**
     * Method which calls the appropriate methods to remove a credit card. Logic
     * for the success or failure display is handled within this method.
     */
    public void removeCreditCard(){
        Scanner scanner = new Scanner(System.in);
        TheaterApplication.clearPage();

        System.out.println("Enter a credit card number: ");
        String creditCardNumber = scanner.nextLine();

        creditCard = creditCardList.remove(creditCardNumber);

        if(creditCard != null){
            System.out.println("Successfully removed credit card");
        }else{
            System.out.println("Failed to remove credit card");
        }

        System.out.println("Cards on File: \n");
        creditCardList.printAll();
        
        pressEnterKeyToContinue();
    }

    /**
     * Method which calls the appropriate methods display all customers on file.
     */
    public void listAllCustomers(){
    	TheaterApplication.clearPage();
        System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                "Customer List\n" + TheaterApplication.LINE_SEPARATER);

        List<Customer> tempCustomerList = customerList.getAll();
        tempCustomerList.forEach(item -> System.out.print(item.toString()));

        System.out.println(TheaterApplication.LINE_SEPARATER);
        pressEnterKeyToContinue();
    }

}