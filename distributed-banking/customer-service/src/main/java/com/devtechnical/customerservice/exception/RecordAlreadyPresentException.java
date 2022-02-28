package com.devtechnical.customerservice.exception;

/**
 * @author Mohanksp
 */
public class RecordAlreadyPresentException extends RuntimeException {

  public RecordAlreadyPresentException(String msg) {
    super(msg);
  }
}
