package com.devtechnical.accountservice.dao;

import com.devtechnical.accountservice.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohanksp
 */
@Repository
public interface AccountDao extends JpaRepository<Account, Long> {

  Optional<Account> findAccountByCustomerId(Long customerId);
}
