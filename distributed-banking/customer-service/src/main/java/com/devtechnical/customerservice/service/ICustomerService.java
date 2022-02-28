package com.devtechnical.customerservice.service;

import com.devtechnical.customerservice.model.Customer;
import org.springframework.http.ResponseEntity;

/**
 * @author Mohanksp
 */
public interface ICustomerService {

  public ResponseEntity<?> createCustomer(Customer newCustomer);

  public Customer updateCustomer(Customer newCustomer);

  public String deleteCustomer(Long customerId);

  public Iterable<Customer> displayAllCustomer();

  public ResponseEntity<Customer> findCustomerById(Long customerId);

}
