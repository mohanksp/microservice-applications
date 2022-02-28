package com.devtechnical.accountservice.service;

import com.devtechnical.accountservice.model.Account;
import org.springframework.http.ResponseEntity;

/**
 * @author Mohanksp
 */
public interface IAccountService {

  public ResponseEntity<?> createAccount(Account newAccount);

  public Account updateAccount(Account newAccount);

  public String deleteAccount(Long accountId);

  public Iterable<Account> displayAllAccount();

  public ResponseEntity<Account> findAccountById(Long accountId);

  public ResponseEntity<Account> findAccountByCustomerId(Long customerId);

}
