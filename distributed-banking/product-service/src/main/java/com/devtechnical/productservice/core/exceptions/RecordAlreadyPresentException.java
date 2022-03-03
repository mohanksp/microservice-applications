package com.devtechnical.productservice.core.exceptions;

/**
 * @author Mohanksp
 */
public class RecordAlreadyPresentException extends RuntimeException {

  public RecordAlreadyPresentException(String msg) {
    super(msg);
  }
}
