package com.example.ecommerce.repositories;

import com.example.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   // Category saveCategory(Category category) ;
    Category findByTitle(String title);

    @Query(value = "select title from category" , nativeQuery = true)
    List<String> findAllTitles();
}
