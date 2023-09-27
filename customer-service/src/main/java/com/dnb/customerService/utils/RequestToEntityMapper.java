package com.dnb.customerService.utils;

import org.springframework.stereotype.Component;

import com.dnb.customerService.dto.Customer;
import com.dnb.customerService.payload.request.CustomerRequest;

@Component
public class RequestToEntityMapper {
//	public Account getAccountEntityObject(AccountRequest accountRequest) {
//		Account account=new Account();
//		account.setAccountHolderName(accountRequest.getAccountHolderName());
//		account.setAccountType(accountRequest.getAccountType());
//		account.setAddress(accountRequest.getAddress());
//		account.setBalance(accountRequest.getBalance());
//		account.setContactNumber(accountRequest.getContactNumber());
//		account.setDob(accountRequest.getDob());
//		
//		Customer customer= new Customer();
//		customer.setCustomerId(accountRequest.getCustomerId());
//		account.setCustomer(customer);
//		
////		account.setCustomerId(accountRequest.getCustomerId());
//		return account;
//	}
	
	public Customer getCustomerEntityObject(CustomerRequest customerRequest) {
		Customer customer=new Customer();
		customer.setCustomerName(customerRequest.getCustomerName());
		customer.setCustomerAddress(customerRequest.getCustomerAddress());
		customer.setCustomerContactNumber(customerRequest.getCustomerContactNumber());
		customer.setCustomerPAN(customerRequest.getCustomerPAN());
		customer.setCustomerUUID(customerRequest.getCustomerUUID());
		return customer;
	}
}
