package com.devtechnical.customerservice.controller;

import com.devtechnical.customerservice.exception.RecordAlreadyPresentException;
import com.devtechnical.customerservice.exception.RecordNotFoundException;
import com.devtechnical.customerservice.model.Customer;
import com.devtechnical.customerservice.service.impl.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohanksp
 */
@RestController
@RequestMapping("/customer")
public class CustomerController {

  @Autowired
  private Environment environment;

  @Autowired
  private CustomerService customerService;

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/status/check")
  public String getStatusAndLoadBalanceCheck() {
    logger.info("called getStatusAndLoadBalanceCheck() method");
    return "working on port : " + environment.getProperty("local.server.port");
  }

  @GetMapping("/read")
  public String checkConfigServerProperties() {
    logger.info("called checkConfigServerProperties() method");
    return "Reading value : " + environment.getProperty("read.value");
  }

  @PostMapping("/createCustomer")
  @ExceptionHandler(RecordAlreadyPresentException.class)
  public void addCustomer(@RequestBody Customer newCustomer) {
    customerService.createCustomer(newCustomer);
  }

  @GetMapping("/readAllCustomers")
  public Iterable<Customer> readAllCustomer() {
    return customerService.displayAllCustomer();
  }

  @PutMapping("/updateCustomer")
  @ExceptionHandler(RecordNotFoundException.class)
  public void updateCustomer(@RequestBody Customer updateCustomer) {
    customerService.updateCustomer(updateCustomer);
  }

  @GetMapping("/searchCustomer/{id}")
  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<?> searchCustomerByID(@PathVariable("id") Long customerId) {
    return customerService.findCustomerById(customerId);
  }

  @DeleteMapping("/deleteCustomer/{id}")
  @ExceptionHandler(RecordNotFoundException.class)
  public void deleteCustomerById(@PathVariable("id") Long customerId) {
    customerService.deleteCustomer(customerId);
  }


}
