package com.bertha.inventorymanagementsystemapp.dto.response;

public record StockResponse(Long stockId, Integer stockQuantity, ProductResponse product) {
}

