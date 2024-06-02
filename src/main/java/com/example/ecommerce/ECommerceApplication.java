package com.example.ecommerce;

import com.example.ecommerce.model.Product;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ECommerceApplication {

    public static void main(String[] args) {
        Product p = new Product();
        p.setTitle("Ecommerce");
        
        SpringApplication.run(ECommerceApplication.class, args);
    }

}
