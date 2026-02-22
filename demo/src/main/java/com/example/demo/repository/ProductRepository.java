package com.example.demo.repository;

import com.example.demo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
//import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import  java.util.List;
//
@Repository
public interface ProductRepository extends JpaRepository<Product,Long>/* JpaSpecificationExecutor<Product>*/{

// search query for
@Query("SELECT p FROM Product p WHERE " +
        "(:id IS NULL OR p.id = :id) AND " +
        "(:name IS NULL OR p.name = :name) AND " +
        "(:price IS NULL OR p.price = :price) AND " +
        "(:quantity IS NULL OR p.quantity = :quantity)")
List<Product> searchProducts(
        @Param("id") Long id,
        @Param("name") String name,
        @Param("price") Double price,
        @Param("quantity") Integer quantity);

// both queries are same  to different I have created two queries

//    @Query("SELECT p FROM Product p WHERE " +
//            "(:id IS NULL OR p.id = :id) AND " +
//            "(:name IS NULL OR p.name = :name) AND " +
//            "(:price IS NULL OR p.price = :price) AND " +
//            "(:quantity IS NULL OR p.quantity = :quantity)")
//    List<Product> findProductsToUpdate(
//            @Param("id") Long id,
//            @Param("name") String name,
//            @Param("price") Double price,
//            @Param("quantity") Integer quantity
//
//    );

    // update query end here


}
