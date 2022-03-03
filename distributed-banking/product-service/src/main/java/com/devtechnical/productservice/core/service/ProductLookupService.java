package com.devtechnical.productservice.core.service;

import com.devtechnical.productservice.core.dao.ProductLookupDao;
import com.devtechnical.productservice.core.model.ProductLookup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mohanksp
 */
@Service
public class ProductLookupService {

  @Autowired
  private ProductLookupDao productLookupDao;

  public void save(ProductLookup productLookup) {
    productLookupDao.save(productLookup);
  }

  public ProductLookup findByProductIdOrTitle(String productId, String title){
    return productLookupDao.findByProductIdOrTitle(productId, title);
  }
}
