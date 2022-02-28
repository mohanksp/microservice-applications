package com.devtechnical.accountservice.controller;

import com.devtechnical.accountservice.exception.RecordAlreadyPresentException;
import com.devtechnical.accountservice.exception.RecordNotFoundException;
import com.devtechnical.accountservice.model.Account;
import com.devtechnical.accountservice.service.impl.AccountService;
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
@RequestMapping("/account")
public class AccountController {

  @Autowired
  private Environment environment;

  @Autowired
  private AccountService accountService;

  @GetMapping("/status/check")
  public String getStatus() {
    return "working on port : " + environment.getProperty("local.server.port");
  }

  @PostMapping("/createAccount")
  @ExceptionHandler(RecordAlreadyPresentException.class)
  public void addAccount(@RequestBody Account newAccount) {
    accountService.createAccount(newAccount);
  }

  @GetMapping("/readAllAccounts")
  public Iterable<Account> readAllAccount() {
    return accountService.displayAllAccount();
  }

  @PutMapping("/updateAccount")
  @ExceptionHandler(RecordNotFoundException.class)
  public void updateAccount(@RequestBody Account updateAccount) {
    accountService.updateAccount(updateAccount);
  }

  @GetMapping("/searchAccount/{id}")
  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<?> searchAccountByID(@PathVariable("id") Long accountId) {
    return accountService.findAccountById(accountId);
  }

  @GetMapping("/getAccount/{customerId}")
  @ExceptionHandler(RecordNotFoundException.class)
  public ResponseEntity<?> searchAccountByCustomerId(@PathVariable("customerId") Long customerId) {
    return accountService.findAccountByCustomerId(customerId);
  }

  @DeleteMapping("/deleteAccount/{id}")
  @ExceptionHandler(RecordNotFoundException.class)
  public void deleteAccountById(@PathVariable("id") Long accountId) {
    accountService.deleteAccount(accountId);
  }


}
