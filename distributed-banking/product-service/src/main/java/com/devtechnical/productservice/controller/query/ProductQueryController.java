package com.devtechnical.productservice.controller.query;

import com.devtechnical.productservice.core.model.Product;
import com.devtechnical.productservice.controller.query.FindProductQuery;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mohanksp
 */
@RestController
@RequestMapping("/product")
public class ProductQueryController {

  @Autowired
  private Environment env;
  @Autowired
  private QueryGateway queryGateway;

  private Logger logger = LoggerFactory.getLogger(this.getClass());

  @GetMapping("/status2")
  public String statusCheck(){
    return "Working on port "+env.getProperty("local.server.port");
  }

  @GetMapping("/get")
  public List<Product> getProduct(){
    FindProductQuery findProductQuery = new FindProductQuery();
    List<Product> products = queryGateway.query(findProductQuery,
                    ResponseTypes.multipleInstancesOf(Product.class)).join();
    return products;
  }


}
