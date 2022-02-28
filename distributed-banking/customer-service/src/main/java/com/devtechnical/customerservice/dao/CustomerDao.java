package com.devtechnical.customerservice.dao;

import com.devtechnical.customerservice.model.Customer;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohanksp
 */
@Repository
public interface CustomerDao extends JpaRepository<Customer, Long> {

  Optional<Customer> findByLoginId(String loginId);
}
