package com.devtechnical.productservice.core.dao;

import com.devtechnical.productservice.core.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mohanksp
 */
@Repository
public interface ProductDao extends JpaRepository<Product, String> {

}
