package com.bertha.inventorymanagementsystemapp.controller;

import com.bertha.inventorymanagementsystemapp.domain.Product;
import com.bertha.inventorymanagementsystemapp.dto.request.ProductRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestWithId;
import com.bertha.inventorymanagementsystemapp.dto.response.ProductResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponse;
import com.bertha.inventorymanagementsystemapp.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims/api/v1/product")
public class ProductController {

    private  ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }


    @PostMapping("/add")
    public ResponseEntity<ProductResponse> addProduct(@Valid @RequestBody ProductRequest productRequest){
        return ResponseEntity.ok(productService.addProduct(productRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<ProductResponse>> getProducts(){
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/getByID/{id}")
    public ResponseEntity<ProductResponse> getProductById(@PathVariable Long id){
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/getByProductCode/{productCode}")
    public ResponseEntity<ProductResponse> getProductByCode(@PathVariable String productCode){
        return ResponseEntity.ok(productService.getProductByCode(productCode));
    }

    @GetMapping("/getName/{productName}")
    public ResponseEntity<ProductResponse> getProductByName(@PathVariable String productName){
        return ResponseEntity.ok(productService.getProductByName(productName));
    }



    @PutMapping("/update/{id}")
    public ResponseEntity<ProductResponse> updateProduct(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id){
        return ResponseEntity.ok(productService.updateProduct(productRequest, id));
    }

    @PutMapping("/update/supplier/{id}")
    public ResponseEntity<ProductResponse> updateProductSupplier(@Valid @RequestBody ProductRequest productRequest, @PathVariable Long id, @RequestBody SupplierRequestWithId supplierRequest){
        return ResponseEntity.ok(productService.updateProductSupplier(productRequest, id, supplierRequest));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Long id){
        productService.deleteProduct(id);
        return ResponseEntity.ok(productService.deleteProduct(id));
    }


}
