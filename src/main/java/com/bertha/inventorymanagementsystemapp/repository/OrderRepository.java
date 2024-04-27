package com.bertha.inventorymanagementsystemapp.repository;

import com.bertha.inventorymanagementsystemapp.domain.ProductOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<ProductOrder, Long> {

    @Query("SELECT o FROM ProductOrder o WHERE o.customer.customerId = ?1")
    ProductOrder findByCustomer_CustomerId(Long customerId);

    @Query("SELECT o FROM ProductOrder o WHERE o.orderId = ?1")
    ProductOrder findByOrderId(Long orderId);
}
