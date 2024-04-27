package com.bertha.inventorymanagementsystemapp.dto.request;

import java.math.BigDecimal;

public record ProductRequestForOrder(String productCode, String productName, String productCategory, BigDecimal price){
}
