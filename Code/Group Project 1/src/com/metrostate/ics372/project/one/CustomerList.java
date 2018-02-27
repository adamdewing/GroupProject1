/**
 * Description: This class serves as a means to store Customer objects. This
 * class is used to retrieve any of the existing customers in the system. It should
 * be noted that this class implements the Data Access and Modified interfaces.
 */

package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.List;

public class CustomerList implements DataAccess<Customer, String>, Modified{

	private static boolean isModified = false;
	private static CustomerList customerList;
	public static List<Customer> customers = new ArrayList<Customer>();

	public static CustomerList instance() {
		if (customerList == null) {
			return (customerList = new CustomerList());
		} else {
			return customerList;
		}
	}

	@Override
	public Customer add(Customer item) {
		customers.add(item);
		isModified = true;
		return item;
	}

	public Customer findCustomer(String id){
		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getCustomerId().equals(id)){
				return customers.get(i);
			}
		}
		return null;
	}

	@Override
	public List<Customer> getAll() {
		return customers;
	}

	@Override
	public boolean isModified() {
		return isModified;
	}

	@Override
	public Customer remove(String id) {
		Customer targetCustomer = null;

		for(int i = 0; i < customers.size(); i++){
			if(customers.get(i).getCustomerId().equals(id)){
				targetCustomer = customers.get(i);
				customers.remove(i);
			}
		}
		isModified = true;
		return targetCustomer;
	}

	@Override
	public void removeAll() {
		customers = new ArrayList<Customer>();
		isModified = true;
	}

	@Override
	public void resetModifiedFlag() {
		isModified = false;
	}
}
