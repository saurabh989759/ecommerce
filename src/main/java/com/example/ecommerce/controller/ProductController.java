package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ErrorDto;
import com.example.ecommerce.exceptions.ProductNotFoundExceptions;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

   private ProductService productService;
   public ProductController(ProductService productService){
       this.productService = productService;
   }
   @PostMapping("/products")
    public Product createProduct(@RequestBody Product product){
        Product postedProduct = productService.createProduct(product);
        return postedProduct;
   }

   @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long productId){
       Product currentProduct = productService.getSingleProduct(productId);
       ResponseEntity<Product> res = new ResponseEntity<>(
               currentProduct , HttpStatus.OK);
       return res;
   }
   @GetMapping("/products")
    public void getAllProducts(){
       productService.getAllProducts();
   }

   @ExceptionHandler(ProductNotFoundExceptions.class)
   public ResponseEntity<ErrorDto> handleProductNotFoundException(Exception e){
       ErrorDto errorDto = new ErrorDto();
       errorDto.setMessage(e.getMessage());
       return new ResponseEntity<>(errorDto, HttpStatus.NOT_FOUND);
   }
}
