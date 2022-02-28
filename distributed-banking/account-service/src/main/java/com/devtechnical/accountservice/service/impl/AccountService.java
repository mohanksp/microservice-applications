package com.devtechnical.accountservice.service.impl;

import com.devtechnical.accountservice.dao.AccountDao;
import com.devtechnical.accountservice.exception.RecordAlreadyPresentException;
import com.devtechnical.accountservice.exception.RecordNotFoundException;
import com.devtechnical.accountservice.model.Account;
import com.devtechnical.accountservice.service.IAccountService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mohanksp
 */
@Service
public class AccountService implements IAccountService {

  @Autowired
  private AccountDao accountDao;

  @Override
  public ResponseEntity<?> createAccount(Account newAccount) {
    // TODO Auto-generated method stub
    Optional<Account> findAccountById = accountDao.findById(newAccount.getAccountId());
    try {
      if (findAccountById.isEmpty()) {
        accountDao.save(newAccount);
        return new ResponseEntity<>(newAccount, HttpStatus.OK);
      } else {
        throw new RecordAlreadyPresentException(
            "Account with Id: " + newAccount.getAccountId() + " already exists!!");
      }
    } catch (RecordAlreadyPresentException e) {

      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public Account updateAccount(Account updateAccount) {
    // TODO Auto-generated method stub
    Optional<Account> findAccountById = accountDao.findById(updateAccount.getAccountId());
    if (findAccountById.isPresent()) {
      accountDao.save(updateAccount);
    } else {
      throw new RecordNotFoundException(
          "Account with Id: " + updateAccount.getAccountId() + " not exists!!");
    }
    return updateAccount;
  }

  @Override
  public String deleteAccount(Long AccountId) {
    // TODO Auto-generated method stub
    Optional<Account> findBookingById = accountDao.findById(AccountId);
    if (findBookingById.isPresent()) {
      accountDao.deleteById(AccountId);
      return "Account Deleted!!";
    } else {
      throw new RecordNotFoundException("Account not found for the entered AccountID");
    }
  }

  @Override
  public Iterable<Account> displayAllAccount() {
    // TODO Auto-generated method stub
    return accountDao.findAll();
  }

  @Override
  public ResponseEntity<Account> findAccountById(Long accountId) {
    // TODO Auto-generated method stub
    Optional<Account> findById = accountDao.findById(accountId);
    try {
      if (findById.isPresent()) {
        Account findAccount = findById.get();
        return new ResponseEntity<>(findAccount, HttpStatus.OK);
      } else {
        throw new RecordNotFoundException("No record found with ID " + accountId);
      }
    } catch (RecordNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  @Override
  public ResponseEntity<Account> findAccountByCustomerId(Long customerId) {
    // TODO Auto-generated method stub
    Optional<Account> findByCustomerId = accountDao.findAccountByCustomerId(customerId);
    try {
      if (findByCustomerId.isPresent()) {
        Account findAccount = findByCustomerId.get();
        return new ResponseEntity<>(findAccount, HttpStatus.OK);
      } else {
        throw new RecordNotFoundException("No record found with ID " + customerId);
      }
    } catch (RecordNotFoundException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

}
