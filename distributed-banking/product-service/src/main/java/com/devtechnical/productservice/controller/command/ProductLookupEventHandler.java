package com.devtechnical.productservice.controller.command;

import com.devtechnical.productservice.core.events.ProductCreatedEvent;
import com.devtechnical.productservice.core.model.ProductLookup;
import com.devtechnical.productservice.core.service.ProductLookupService;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Mohanksp
 */
@Component
@ProcessingGroup("product-group")
public class ProductLookupEventHandler {

  @Autowired
  private ProductLookupService productLookupService;

  @EventHandler
  public void on(ProductCreatedEvent event) {
    ProductLookup productLookup = new ProductLookup(event.getProductId(), event.getTitle());
    productLookupService.save(productLookup);

  }

}
