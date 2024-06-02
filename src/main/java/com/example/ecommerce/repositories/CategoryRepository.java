package com.example.ecommerce.repositories;

import com.example.ecommerce.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
   // Category saveCategory(Category category) ;
    Category findByTitle(String title);
}
