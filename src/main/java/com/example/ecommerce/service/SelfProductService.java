package com.example.ecommerce.service;

import com.example.ecommerce.exceptions.ProductNotFoundExceptions;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repositories.CategoryRepository;
import com.example.ecommerce.repositories.ProductRepositery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("dbService")
public class SelfProductService implements ProductService {

    private ProductRepositery productRepositery;
    private CategoryRepository categoryRepository;
    public SelfProductService(ProductRepositery productRepositery, CategoryRepository categoryRepository) {
        this.productRepositery = productRepositery;
        this.categoryRepository = categoryRepository;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundExceptions {
        Optional<Product> product = productRepositery.findById(productId);//optional is there to prevent the npe
        if(product.isPresent()){// isPresent is the part of the optional class here
            return product.get();
        }else {
            throw new ProductNotFoundExceptions("Product not found");
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepositery.findAllProducts();
    }

    @Override
    public Product createProduct(Product product) {
        if (product == null || product.getCategory().getTitle() == null) {
            throw new IllegalArgumentException("Product and its category must not be null");
        }
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        System.out.println(cat);
        if(cat == null){
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow =  categoryRepository.save(newCat);
            product.setCategory(newRow);
        }else {
            product.setCategory(cat);
        }
         Product savedProduct = productRepositery.save(product);
        //System.out.println(savedProduct);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product, Long productId)  {
        Optional<Product> p = productRepositery.findById(productId);
        if(p.isPresent()){
          Product oldProduct = p.get();
          oldProduct.setTitle(product.getTitle());
          return productRepositery.save(oldProduct);
        }
        else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public Product deleteProduct(Long productId) {
        Optional<Product> p = productRepositery.findById(productId);
        if(p.isPresent()){
            return productRepositery.deleteProductById(productId);
        }
        else {
            throw new IllegalArgumentException("Product not found");
        }
    }

    @Override
    public List<Product> getCategory(String categoryName) {
        return List.of();
    }


    @Override
    public List<String> getAllCategories() {
        return categoryRepository.findAllTitles();
    }
}
