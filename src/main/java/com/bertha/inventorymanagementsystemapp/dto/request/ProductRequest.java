package com.bertha.inventorymanagementsystemapp.dto.request;

import java.math.BigDecimal;

public record ProductRequest(String productCode,
         String productName,
         String productCategory,
         BigDecimal price,
                             SupplierRequest SupplierRequest, StockRequest stockRequestForProduct
        ){
}
