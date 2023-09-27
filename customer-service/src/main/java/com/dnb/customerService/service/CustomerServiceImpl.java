package com.dnb.customerService.service;

import java.util.List;
import java.util.Optional;

import javax.naming.InvalidNameException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dnb.customerService.dto.Customer;
import com.dnb.customerService.exceptions.IdNotFoundException;
import com.dnb.customerService.repo.CustomerRepository;

@Service("customerServiceImpl")
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Override
	public Customer createCustomer(Customer customer) {
		// TODO Auto-generated method stub
		return customerRepository.save(customer);
	}

	@Override
	public Optional<Customer> getCustomerById(int customerId) {//throws InvalidNameException, InvalidCustomerIdException, InvalidContactNumberException, InvalidAddressException, InvalidGovtIdException {
		// TODO Auto-generated method stub
		return customerRepository.findById(customerId);
	}

	@Override
	public Iterable<Customer> getAllCustomers() {//throws InvalidNameException, InvalidCustomerIdException, InvalidContactNumberException, InvalidAddressException, InvalidGovtIdException {
		// TODO Auto-generated method stub
		return customerRepository.findAll();
	}

	@Override
	public boolean deleteCustomerById(int customerId)throws IdNotFoundException {
		// TODO Auto-generated method stub
		if(customerRepository.existsById(customerId)) {
			customerRepository.deleteById(customerId);
			if(customerRepository.existsById(customerId)) {
				return false;
			}
			return true;
		}
		else {
			throw new IdNotFoundException("ID Not Found");
		}
	}

	@Override
	public boolean customerExistsById(int customerId) {
		// TODO Auto-generated method stub
		if(customerRepository.existsById(customerId))return true;
		else return false;
	}

	@Override
	public Optional<Customer> getCustomerByCustomerContactNumber(String customerContactNumber) {
		// TODO Auto-generated method stub
		return customerRepository.findByCustomerContactNumber(customerContactNumber);
	}

	@Override
	public Iterable<Customer> getAllCustomersByCustomerContactNumber(String customerContactNumber) {
		// TODO Auto-generated method stub
		return customerRepository.findAllByCustomerContactNumber(customerContactNumber);
	}

}
