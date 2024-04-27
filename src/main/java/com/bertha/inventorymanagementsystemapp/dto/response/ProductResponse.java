package com.bertha.inventorymanagementsystemapp.dto.response;

import java.math.BigDecimal;

public record ProductResponse (
        Long productId,
        String productCode,
        String productName,
        String productCategory,
        BigDecimal price,
        SupplierResponseForProduct supplierResponseForProduct,
        StockResponse stockResponseForProduct
) { }
