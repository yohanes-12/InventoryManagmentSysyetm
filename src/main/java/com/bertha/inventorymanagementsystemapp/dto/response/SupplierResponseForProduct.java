package com.bertha.inventorymanagementsystemapp.dto.response;

import java.util.List;

public record SupplierResponseForProduct(
        Long supplierId,
        String supplierName,
        String supplierEmail,
        String supplierPhone
) { }

