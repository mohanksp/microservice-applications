package com.devtechnical.productservice.controller.command;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @author Mohanksp
 */
@Builder
@Data
public class CreateProductCommand {
  @TargetAggregateIdentifier
  private final String productId;
  private final String title;
  private final Integer quantity;
  private final BigDecimal price;

}
