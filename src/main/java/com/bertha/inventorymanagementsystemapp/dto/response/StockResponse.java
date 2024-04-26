package com.bertha.inventorymanagementsystemapp.dto.response;

import com.bertha.inventorymanagementsystemapp.domain.enumClass.StockStatus;

public record StockResponse(Long stockId, Integer stockQuantity,
                             ProductResponse product) {
}

