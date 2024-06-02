package com.example.ecommerce.dto;

import com.example.ecommerce.model.Category;
import com.example.ecommerce.model.Product;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FakeStoreProductDto {
    private Long id;
    private String title ;
    private double price ;
    private String description ;
    private String image;
    private String category ;
    public Product toProduct(){
        Product p = new Product();
        p.setId(id);
        p.setTitle(title);
        p.setPrice(price);
        p.setDescription(description);
        p.setImageUrl(image);

        Category cat = new Category();
        cat.setTitle(category) ;
        p.setCategory(cat);
        return p ;
    }
    @Override
    public String toString() {
        return "FakeStoreProductDto{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}
