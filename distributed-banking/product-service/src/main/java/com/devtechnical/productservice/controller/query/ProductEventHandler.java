package com.devtechnical.productservice.controller.query;

import com.devtechnical.productservice.core.events.ProductCreatedEvent;
import com.devtechnical.productservice.core.model.Product;
import com.devtechnical.productservice.core.service.ProductService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mohanksp
 */
@Component
@ProcessingGroup("product-group")
public class ProductEventHandler {

  @Autowired
  ProductService productService;

  @EventHandler
  public void on(ProductCreatedEvent productCreatedEvent){
    Product newProduct = new Product();
    BeanUtils.copyProperties(productCreatedEvent, newProduct);
    productService.createProduct(newProduct);
  }
}
