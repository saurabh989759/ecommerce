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

        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Category cat = categoryRepository.findByTitle(product.getCategory().getTitle());
        if(cat == null){
            Category newCat = new Category();
            newCat.setTitle(product.getCategory().getTitle());
            Category newRow =  categoryRepository.save(newCat);
            product.setCategory(newRow);
        }else {
            product.setCategory(cat);
        }
         Product savedProduct = productRepositery.save(product);
        return savedProduct;
    }

    @Override
    public Product updateProduct(Product product, Long productId)  {
        return null;
    }

    @Override
    public Product deleteProduct(Long productId) {

        return null;
    }

    @Override
    public List<Product> getCategory(String categoryName) {
        return List.of();
    }


    @Override
    public List<String> getAllCategories() {
        return List.of();
    }
}
