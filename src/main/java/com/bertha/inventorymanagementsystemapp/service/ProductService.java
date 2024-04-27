package com.bertha.inventorymanagementsystemapp.service;

import com.bertha.inventorymanagementsystemapp.dto.request.ProductRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestWithId;
import com.bertha.inventorymanagementsystemapp.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {
    ProductResponse addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();

    ProductResponse getProductById(Long id);

    ProductResponse getProductByCode(String productCode);


    ProductResponse getProductByName(String productName);


    String deleteProduct(Long id);

    ProductResponse updateProduct(ProductRequest productRequest, Long id);

    ProductResponse updateProductSupplier(ProductRequest productRequest, Long id, SupplierRequestWithId supplierRequest);
}
