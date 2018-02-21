package com.metrostate.ics372.project.one;

import java.util.ArrayList;
import java.util.List;

public class CustomerList implements DataAccess<Customer, Integer> {

	private static List<Customer> customers = new ArrayList<Customer>();
	
	@Override
	public void add(Customer item) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Customer> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer remove(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeAll() {
		// TODO Auto-generated method stub
		
	}


}
