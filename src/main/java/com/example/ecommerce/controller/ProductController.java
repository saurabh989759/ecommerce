package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ErrorDto;
import com.example.ecommerce.exceptions.ProductNotFoundExceptions;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

   private ProductService productService;
   public ProductController(@Qualifier("dbService") ProductService productService){
       this.productService = productService;
   }
   @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Product postedProduct = productService.createProduct(product);
        return postedProduct;
   }

   @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long productId) throws ProductNotFoundExceptions {
       //System.out.println("productId: " + productId);
       Product currentProduct = productService.getSingleProduct(productId);
       ResponseEntity<Product> res = new ResponseEntity<>(
               currentProduct , HttpStatus.OK);
       return res;
   }
   @GetMapping("/products")
    public void getAllProducts(){
       productService.getAllProducts();
   }
   @GetMapping("products/category")
     public ResponseEntity<List<String>> getAllProductsByCategory(){
     //  ResponseEntity
       List<String> list = productService.getAllCategories();
       ResponseEntity<List<String>> response = new ResponseEntity<>(
         list ,HttpStatus.OK
       );
       return response;
   }
   @DeleteMapping("/products/{id}")
    public ResponseEntity<ErrorDto> deleteProduct(@PathVariable("id") Long productId) throws ProductNotFoundExceptions {
       productService.deleteProduct(productId);
       return null;
   }

   //categories
    @GetMapping("/products/category/{title}")
    public ResponseEntity<List<Product>> getAllProductByCategory(@PathVariable("title") String title){
      List<Product> prod = productService.getCategory(title);
      ResponseEntity <List<Product>> res = new ResponseEntity<>(
              prod ,HttpStatus.OK
      );
      return res ;
    }
    @PostMapping("/products/{id}")
    public ResponseEntity<Product> updateProduct(@RequestBody Product product , @PathVariable("id") Long productId) throws ProductNotFoundExceptions {
           Product prod = productService.updateProduct(product, productId);
           return ResponseEntity.ok(prod);
    }
   @ExceptionHandler(ProductNotFoundExceptions.class)
   public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
       ErrorDto errorDto = new ErrorDto();
       errorDto.setMessage(e.getMessage());
       return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
   }

}
