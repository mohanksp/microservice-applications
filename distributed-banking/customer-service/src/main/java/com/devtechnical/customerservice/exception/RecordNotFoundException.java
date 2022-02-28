package com.devtechnical.customerservice.exception;

/**
 * @author Mohanksp
 */
public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String msg) {
    super(msg);
  }
}
