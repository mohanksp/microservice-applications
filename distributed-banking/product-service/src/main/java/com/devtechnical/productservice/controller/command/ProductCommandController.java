package com.devtechnical.productservice.controller.command;

import com.devtechnical.productservice.controller.command.CreateProductCommand;
import com.devtechnical.productservice.core.model.Product;
import java.util.UUID;
import javax.validation.Valid;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohanksp
 */

@RestController
@RequestMapping("/product")
public class ProductCommandController {

  @Autowired
  private Environment env;
  @Autowired
  private CommandGateway commandGateway;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/status1")
  public String statusCheck(){
    return "Working on port "+env.getProperty("local.server.port");
  }

  @PostMapping("/create")
  public String createProduct(@Valid @RequestBody Product product){
    logger.info("Create product with "+product.getTitle());
    CreateProductCommand productCommand = CreateProductCommand.builder()
        .title(product.getTitle())
        .price(product.getPrice())
        .quantity(product.getQuantity())
        .productId(UUID.randomUUID().toString()).build();
    String returnValue;
    try {
      returnValue = commandGateway.sendAndWait(productCommand);
    } catch (Exception ex){
      returnValue = ex.getLocalizedMessage();
    }
    return returnValue;
  }

  @PutMapping("/update")
  public String updateProduct(@RequestBody Product product){
    return "Update product "+product.getPrice();
  }

  @DeleteMapping("/delete")
  public String deleteProduct(@RequestBody Product product){
    return "Delete product "+product.getTitle();
  }


}
