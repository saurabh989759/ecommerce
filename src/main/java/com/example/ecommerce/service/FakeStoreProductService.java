package com.example.ecommerce.service;

import com.example.ecommerce.dto.FakeStoreProductDto;
import com.example.ecommerce.exceptions.ProductNotFoundExceptions;
import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStore")
public class FakeStoreProductService implements ProductService {

    private final RestTemplate restTemplate;
    public FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate ;
    }
    @Override
    public Product getSingleProduct(Long productId) throws ProductNotFoundExceptions {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId ,
                FakeStoreProductDto.class
        );
        if(fakeStoreProductDto == null) {
            throw  new ProductNotFoundExceptions("Product not found");
        }
        return fakeStoreProductDto.toProduct();
    }

    @Override
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products", FakeStoreProductDto[].class
        );
        //assert res != null;
        for(FakeStoreProductDto fakeStoreProductDto : res) {
            products.add(fakeStoreProductDto.toProduct());
        }
        return products ;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProductDto fs = new FakeStoreProductDto();
        fs.setId(product.getId());
        fs.setTitle(product.getTitle());
        fs.setDescription(product.getDescription());
        fs.setPrice(product.getPrice());
        fs.setCategory(product.getCategory().getTitle());
        fs.setImage(product.getImageUrl());
        FakeStoreProductDto resp = restTemplate.postForObject(
                "https://fakestoreapi.com/products", fs, FakeStoreProductDto.class
        );
        return resp.toProduct();
    }

    @Override
    public Product updateProduct(Product product, Long productId) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + productId ,
                FakeStoreProductDto.class
        );

        fakeStoreProductDto.setTitle(product.getTitle());
        fakeStoreProductDto.setDescription(product.getDescription());
        fakeStoreProductDto.setPrice(product.getPrice());
        fakeStoreProductDto.setCategory(product.getCategory().getTitle());
        fakeStoreProductDto.setImage(product.getImageUrl());
        restTemplate.put(
                "https://fakestoreapi.com/products/" + productId ,
                fakeStoreProductDto);


        return fakeStoreProductDto.toProduct();
    }





    @Override
    public Product deleteProduct(Long productId) {
        ResponseEntity<FakeStoreProductDto> response = restTemplate.exchange(
                "https://fakestoreapi.com/products/" + productId,
                HttpMethod.DELETE,
                null,
                FakeStoreProductDto.class
        );

        return response.getBody().toProduct();
    }

    @Override
    public List<Product> getCategory(String categoryName) {
        Category category = new Category();
        List<Product> products = new ArrayList<>();
        FakeStoreProductDto[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products/category/" + categoryName,
                FakeStoreProductDto[].class
        );
        for(FakeStoreProductDto fakeStoreProductDto : res) {
            products.add(fakeStoreProductDto.toProduct());
        }

        return products;
    }

    @Override
    public List<String> getAllCategories() {
        List<String> categories = new ArrayList<>();
        String[] res = restTemplate.getForObject(
                "https://fakestoreapi.com/products/categories", String[].class
        );
        for(String str : res) {
            categories.add(str);
        }
        return categories;
    }
}
