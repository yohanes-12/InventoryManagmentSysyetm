package com.bertha.inventorymanagementsystemapp.dto.response;

import org.hibernate.engine.spi.Status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrdersResponse2 (Long orderId, LocalDate orderDate,  BigDecimal price, Integer quantity, List<ProductResponse> products, CustomerResponse customer) {
}