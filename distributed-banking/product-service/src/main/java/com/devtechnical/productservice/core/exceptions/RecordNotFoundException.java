package com.devtechnical.productservice.core.exceptions;

/**
 * @author Mohanksp
 */
public class RecordNotFoundException extends RuntimeException {

  public RecordNotFoundException(String msg) {
    super(msg);
  }
}
