package com.bertha.inventorymanagementsystemapp.controller;

import com.bertha.inventorymanagementsystemapp.dto.request.ProductRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestForStock;
import com.bertha.inventorymanagementsystemapp.service.SupplierService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ims/api/v1/supplier")
public class SupplierController {

    private SupplierService supplierService;


    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerSupplier(@RequestBody @Valid SupplierRequest supplierRequest){
        return ResponseEntity.ok(supplierService.registerSupplier(supplierRequest));
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> addStockProduct(@RequestBody SupplierRequestForStock supplierRequestForStock, @PathVariable Long id){
        return ResponseEntity.ok(supplierService.addStockProduct(supplierRequestForStock, id));
    }




}
