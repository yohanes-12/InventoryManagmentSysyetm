package com.bertha.inventorymanagementsystemapp.service.imp;

import com.bertha.inventorymanagementsystemapp.domain.Product;
import com.bertha.inventorymanagementsystemapp.domain.Stock;
import com.bertha.inventorymanagementsystemapp.domain.Supplier;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestForStock;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponse;
import com.bertha.inventorymanagementsystemapp.repository.ProductRepository;
import com.bertha.inventorymanagementsystemapp.repository.StockRepository;
import com.bertha.inventorymanagementsystemapp.repository.SupplierRepository;
import com.bertha.inventorymanagementsystemapp.service.SupplierService;
import org.springframework.stereotype.Service;

@Service
public class SupplierServiceImp implements SupplierService {

    private final SupplierRepository supplierRepository;

    private ProductRepository productRepository;

    private final StockRepository stockRepository;

    public SupplierServiceImp(SupplierRepository supplierRepository, ProductRepository productRepository, StockRepository stockRepository) {
        this.supplierRepository = supplierRepository;
        this.productRepository = productRepository;
        this.stockRepository = stockRepository;
    }


    @Override
    public SupplierResponse registerSupplier(SupplierRequest supplierRequest) {
        Supplier supplier = new Supplier(null, supplierRequest.supplierName(), supplierRequest.supplierEmail(), supplierRequest.supplierPhone(), null);
        supplierRepository.save(supplier);
        return new SupplierResponse(supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierEmail(), supplier.getSupplierPhone());
    }

    @Override
    public String addStockProduct(SupplierRequestForStock supplierRequestForStock, Long id) {
        Supplier supplier = supplierRepository.findById(id).orElse(null);
        Product product = productRepository.findById(supplierRequestForStock.productId()).orElse(null);

        if(supplier == null || product == null){
            return "Supplier or product not found";
        }

        if(product.getStock() == null) {
            Stock stock = new Stock(supplierRequestForStock.quantity(), product);
//            stock = stockRepository.save(stock);

            product.setStock(stock);
            productRepository.save(product);
        } else {
            Stock stock = product.getStock();
            stock.setQuantity(stock.getStockQuantity() + supplierRequestForStock.quantity());
            stockRepository.save(stock);
        }

        return "successfully added stock to product";
    }
}
