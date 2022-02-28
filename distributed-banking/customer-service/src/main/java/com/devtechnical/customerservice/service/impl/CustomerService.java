package com.devtechnical.customerservice.service.impl;

import com.devtechnical.customerservice.dao.CustomerDao;
import com.devtechnical.customerservice.exception.RecordAlreadyPresentException;
import com.devtechnical.customerservice.exception.RecordNotFoundException;
import com.devtechnical.customerservice.model.Customer;
import com.devtechnical.customerservice.service.ICustomerService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mohanksp
 */
@Service
public class CustomerService implements ICustomerService {

  @Autowired
  private CustomerDao customerDao;

  @Override
  public ResponseEntity<?> createCustomer(Customer newCustomer) {
    // TODO Auto-generated method stub
    Optional<Customer> findCustomerById = customerDao.findByLoginId(newCustomer.getLoginId());
    try {
      if (findCustomerById.isEmpty()) {
        customerDao.save(newCustomer);
        return new ResponseEntity<>(newCustomer, HttpStatus.OK);
      } else {
        throw new RecordAlreadyPresentException(
            "Customer with Id: " + newCustomer.getLoginId() + " already exists!!");
      }
    } catch (RecordAlreadyPresentException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public Customer updateCustomer(Customer updateCustomer) {
    // TODO Auto-generated method stub
    Optional<Customer> findCustomerById = customerDao.findByLoginId(updateCustomer.getLoginId());
    if (findCustomerById.isPresent()) {
      customerDao.save(updateCustomer);
    } else {
      throw new RecordNotFoundException(
          "Customer with Id: " + updateCustomer.getLoginId() + " not exists!!");
    }
    return updateCustomer;
  }

  @Override
  public String deleteCustomer(Long customerId) {
    // TODO Auto-generated method stub
    Optional<Customer> findCustomerById = customerDao.findById(customerId);
    if (findCustomerById.isPresent()) {
      customerDao.deleteById(customerId);
      return "Customer Deleted!!";
    } else {
      throw new RecordNotFoundException("Customer not found for the entered customerID");
    }
  }

  @Override
  public Iterable<Customer> displayAllCustomer() {
    return customerDao.findAll();
  }

  @Override
  public ResponseEntity<Customer> findCustomerById(Long customerId) {
    // TODO Auto-generated method stub
    Optional<Customer> findById = customerDao.findById(customerId);
    try {
      if (findById.isPresent()) {
        Customer findCustomer = findById.get();
        return new ResponseEntity<>(findCustomer, HttpStatus.OK);
      } else {
        throw new RecordNotFoundException("No record found with ID " + customerId);
      }
    } catch (RecordNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }
}
