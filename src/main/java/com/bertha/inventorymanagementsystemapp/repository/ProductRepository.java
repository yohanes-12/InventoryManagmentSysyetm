package com.bertha.inventorymanagementsystemapp.repository;

import com.bertha.inventorymanagementsystemapp.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.productCode = :productCode")
    Optional<Product> findByProductCode(String productCode);

    @Query("SELECT p FROM Product p WHERE p.productName LIKE %:searchString% OR p.productCode LIKE %:searchString% OR p.productCategory LIKE %:searchString%")
    List<Product> searchProduct(String searchString);

    @Query("SELECT p FROM Product p WHERE p.productName = :productName")
    Optional<Product> findByProductName(String productName);

    @Query("SELECT p FROM Product p WHERE p.productCategory = :productCategory")
    Optional<Product> findByProductCategory(String productCategory);


    @Query("SELECT p FROM Product p WHERE p.productId = :pId")
    Product findByProductId(Long pId);
}
