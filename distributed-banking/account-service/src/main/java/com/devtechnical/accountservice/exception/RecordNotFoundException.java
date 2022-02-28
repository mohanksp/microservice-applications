package com.devtechnical.accountservice.exception;

/**
 * @author Mohanksp
 */
public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String msg) {
    super(msg);
  }
}
