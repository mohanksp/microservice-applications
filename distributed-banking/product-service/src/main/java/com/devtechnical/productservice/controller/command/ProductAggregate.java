package com.devtechnical.productservice.controller.command;

import com.devtechnical.productservice.core.events.ProductCreatedEvent;
import java.math.BigDecimal;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

/**
 * @author Mohanksp
 */
@Aggregate
public class ProductAggregate {
  @AggregateIdentifier
  private String productId;
  private String title;
  private Integer quantity;
  private BigDecimal price;

  public ProductAggregate() { }

  @CommandHandler
  public ProductAggregate(CreateProductCommand productCommand) {
    //validate create product command
    if(productCommand.getPrice().compareTo(BigDecimal.ZERO) <= 0){
      throw new IllegalArgumentException("price can't be less or equal then zero");
    }

    if(productCommand.getTitle() == null || productCommand.getTitle().isEmpty()){
      throw new IllegalArgumentException("title can't be empty");
    }

    ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
    BeanUtils.copyProperties(productCommand, productCreatedEvent);
    AggregateLifecycle.apply(productCreatedEvent);
  }

  @EventSourcingHandler
  public void on(ProductCreatedEvent productCreatedEvent){
    this.productId = productCreatedEvent.getProductId();
    this.title = productCreatedEvent.getTitle();
    this.quantity = productCreatedEvent.getQuantity();
    this.price = productCreatedEvent.getPrice();
  }
}
