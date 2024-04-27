package com.bertha.inventorymanagementsystemapp.repository;

import com.bertha.inventorymanagementsystemapp.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Query("SELECT s FROM Stock s JOIN FETCH s.product p WHERE p.productCode = :s")
    Optional<Stock> findByProductCode(String s);
}
