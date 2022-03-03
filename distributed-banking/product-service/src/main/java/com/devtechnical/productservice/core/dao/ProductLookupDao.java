package com.devtechnical.productservice.core.dao;

import com.devtechnical.productservice.core.model.ProductLookup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohanksp
 */
@Repository
public interface ProductLookupDao extends JpaRepository<ProductLookup, String> {

  ProductLookup findByProductIdOrTitle(String productId, String title);
}
