package com.dnb.customerService.controller;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dnb.customerService.dto.Customer;
import com.dnb.customerService.service.CustomerService;
import com.dnb.customerService.utils.RequestToEntityMapper;
import com.dnb.customerService.exceptions.DataNotFoundException;
import com.dnb.customerService.exceptions.IdNotFoundException;
import com.dnb.customerService.exceptions.InvalidContactNumberException;
import com.dnb.customerService.payload.request.CustomerRequest;

import jakarta.validation.Valid;

//@CrossOrigin("*")
@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	CustomerService customerService;
	@Autowired
	RequestToEntityMapper mapper;
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<?> deleteCustomerById(@PathVariable("customerId") int customerId) throws  IdNotFoundException{
		if(customerService.customerExistsById(customerId)) {
			customerService.deleteCustomerById(customerId);
			return (ResponseEntity<?>) ResponseEntity.noContent().build();
		}
		else {
			throw new IdNotFoundException("Customer id not valid");
		}
	}
	
	@GetMapping("/cn/{customerContactNumber:^[0-9]{10}$}")
	public ResponseEntity<?>getCustomerByContactNumber(@PathVariable("customerContactNumber")String customerContactNumber) throws InvalidContactNumberException{
		Optional<Customer>optional=customerService.getCustomerByCustomerContactNumber(customerContactNumber);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new InvalidContactNumberException("Contact number is invalid");
		}
	}
	
	@GetMapping("/allCustomers/{customerContactNumber}")
	public ResponseEntity<?> getAllCustomersByCustomerContactNumber(@PathVariable("customerContactNumber")String customerContactNumber) throws InvalidContactNumberException, DataNotFoundException{
		List<Customer>list=(List<Customer>)customerService.getAllCustomersByCustomerContactNumber(customerContactNumber);
		if(list.isEmpty()) {
			throw new DataNotFoundException("No Data Found");
		}
		else {
			return ResponseEntity.ok(list);
		}
	}
	
	@GetMapping("/ci/{customerId}")
	public ResponseEntity<?> getCustomerById(@PathVariable("customerId")int customerId) throws IdNotFoundException{
		System.out.println(customerId);
		Optional<Customer>optional=customerService.getCustomerById(customerId);
		if(optional.isPresent()) {
			return ResponseEntity.ok(optional.get());
		}
		else {
			throw new IdNotFoundException("Customer id is not valid");
		}
		
	}
	
	@PostMapping("/create")
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerRequest customerRequest)throws IdNotFoundException{
		Customer customer2=mapper.getCustomerEntityObject(customerRequest);
		Customer customer=customerService.createCustomer(customer2);
		return new ResponseEntity(customer,HttpStatus.CREATED);
	}
}
