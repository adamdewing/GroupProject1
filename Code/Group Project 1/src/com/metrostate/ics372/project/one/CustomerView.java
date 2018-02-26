package com.metrostate.ics372.project.one;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerView {

    private Customer customer;
    private CreditCard creditCard;
    private CustomerList customerList;
    private CreditCardList creditCardList;

    public CustomerView(){
        customerList = CustomerList.instance();
        creditCardList = CreditCardList.instance();
    }

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

    public void addCustomer(){
        Scanner scanner = new Scanner(System.in);

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

        customer = new Customer(customerFirstName, customerLastName,
            customerStreetAddress, customerCity, customerState, customerZipCode,
            customerPhoneNumber);

        creditCard = new CreditCard(customer.getCustomerId(), customerCreditCard,
                customerCreditCardExpirationDate);

        customerList.add(customer);
        creditCardList.add(creditCard);

        try {
            System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                    "New customer added \n" + TheaterApplication.LINE_SEPARATER +
                    "\n" + customer.toString() + TheaterApplication.LINE_SEPARATER);
        } catch(Exception e) {
            System.out.println("Unable to add new customer. \nError: " + e);
        }
    }

    public void removeCustomer(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter Customer ID: ");
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
    }

    public void addCreditCard(){
        Scanner scanner = new Scanner(System.in);
    }

    public void removeCreditCard(){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter a credit card number: ");
        String creditCardNumber = scanner.nextLine();

        System.out.println(customer.removeCreditCard(creditCardNumber) +
                TheaterApplication.LINE_SEPARATER);
    }

    public void listAllCustomers(){
        System.out.println(TheaterApplication.LINE_SEPARATER + "\n" +
                "Customer List\n" + TheaterApplication.LINE_SEPARATER);

        List<Customer> tempCustomerList = customerList.getAll();
        tempCustomerList.forEach(item -> System.out.print(item.toString()));

        System.out.println(TheaterApplication.LINE_SEPARATER);
    }
}
