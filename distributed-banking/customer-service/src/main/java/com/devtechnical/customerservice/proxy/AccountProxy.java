package com.devtechnical.customerservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Mohanksp
 */
@FeignClient(name = "account-ws")
public interface AccountProxy {

  @GetMapping("/getAccount/{customerId}")
  public ResponseEntity<?> searchAccountByCustomerId(@PathVariable("customerId") Long customerId);


}
