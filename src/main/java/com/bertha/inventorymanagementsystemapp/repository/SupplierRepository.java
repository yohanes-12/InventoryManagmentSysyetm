package com.bertha.inventorymanagementsystemapp.repository;

import com.bertha.inventorymanagementsystemapp.domain.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}
