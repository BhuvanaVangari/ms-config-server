package com.dnb.customerService.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dnb.customerService.dto.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer>{
	
	public Optional<Customer> findByCustomerContactNumber(String customerContactNumber);
	
	public List<Customer> findAllByCustomerContactNumber(String customerContactNumber);

}