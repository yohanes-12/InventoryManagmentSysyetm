package com.bertha.inventorymanagementsystemapp.service;

import com.bertha.inventorymanagementsystemapp.dto.request.ProductRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestForStock;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponse;
import org.springframework.http.ResponseEntity;

public interface SupplierService {
    SupplierResponse registerSupplier(SupplierRequest supplierRequest);

    String addStockProduct(SupplierRequestForStock supplierRequestForStock, Long id);
}
