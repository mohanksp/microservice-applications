package com.devtechnical.productservice.core.service;

import com.devtechnical.productservice.core.dao.ProductDao;
import com.devtechnical.productservice.core.exceptions.RecordAlreadyPresentException;
import com.devtechnical.productservice.core.exceptions.RecordNotFoundException;
import com.devtechnical.productservice.core.model.Product;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

/**
 * @author Mohanksp
 */
@Service
public class ProductService {

  @Autowired
  private ProductDao productDao;

  public Product getProductById(String productId) {
    Optional<Product> productOptional = productDao.findById(productId);
    if (productOptional.isEmpty()) {
      throw new RecordNotFoundException("Product isn't exist with productId : " + productId);
    }
    return productOptional.get();
  }

  public ResponseEntity<?> createProduct(Product newProduct) {
    Optional<Product> findProductById = productDao.findById(newProduct.getProductId());
    try {
      if (findProductById.isEmpty()) {
        productDao.save(newProduct);
        return new ResponseEntity<>(newProduct, HttpStatus.OK);
      } else {
        throw new RecordAlreadyPresentException(
            "Product with Id: " + newProduct.getProductId() + " already exists!!");
      }
    } catch (RecordAlreadyPresentException e) {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
  }

  public List<Product> getAllProducts() {
    return productDao.findAll();
  }
}
