package com.example.ecommerce.repositories;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepositery extends JpaRepository<Product , Long> {
     Product save(Product product);
     //to insert
    //Product Product(String title);
    Product findById(long id);
    Product findByTitle(String name);

    @Query(value = "select * from product" , nativeQuery = true)
    List<Product> findAllProducts();
    //select * from product where title = {}
    Product findByDescription(String description);

    @Query(value = "delete  from product where product.id =: id" , nativeQuery = true)
    Product deleteProductById(@Param("id") Long id);
    //how to implement the HQL query
    @Query("select p from Product p where p.category.id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "selct * from  Product p where p.category_id = :categoryId" , nativeQuery = true)
    List<Product> findByCategoryIdNative(@Param("categoryId") Long categoryId);
}
