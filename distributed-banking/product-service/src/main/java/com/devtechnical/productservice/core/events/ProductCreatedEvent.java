package com.devtechnical.productservice.core.events;

import java.math.BigDecimal;
import lombok.Data;

/**
 * @author Mohanksp
 */

@Data
public class ProductCreatedEvent {
  private String productId;
  private String title;
  private Integer quantity;
  private BigDecimal price;
}
