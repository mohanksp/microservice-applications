package com.devtechnical.productservice.core.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mohanksp
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ProductLookup")
public class ProductLookup implements Serializable {

  @Id
  private String productId;
  @Column(unique = true)
  private String title;
}
