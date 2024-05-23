package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) ;
    List<Product> getAllProducts();
    Product createProduct(Product product);
}
