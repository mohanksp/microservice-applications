package com.devtechnical.productservice.controller.query;

import com.devtechnical.productservice.core.model.Product;
import com.devtechnical.productservice.core.service.ProductService;
import java.util.List;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mohanksp
 */
@Component
public class ProductQueryHandler {

  @Autowired
  private ProductService productService;

  @QueryHandler
  public List<Product> findProducts(FindProductQuery query){
    return productService.getAllProducts();
  }
}
