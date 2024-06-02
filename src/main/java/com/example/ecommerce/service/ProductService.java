package com.example.ecommerce.service;

import com.example.ecommerce.exceptions.ProductNotFoundExceptions;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;

import java.util.List;

public interface ProductService {
    Product getSingleProduct(Long productId) throws ProductNotFoundExceptions;
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Product product, Long productId) ;
    Product deleteProduct(Long productId);
    List<Product> getCategory(String categoryName);
    List<String> getAllCategories();
}
