package com.devtechnical.productservice.core.model;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
/**
 * @author Mohanksp
 */


@Entity
@Table(name="Products")
@Data
public class Product implements Serializable {

  @Id
  @Column(unique = true)
  private String productId;
  @Column(unique = true)
  private String title;
  private Integer quantity;
  private BigDecimal price;

}
