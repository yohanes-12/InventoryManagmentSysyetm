package com.bertha.inventorymanagementsystemapp.service.imp;

import com.bertha.inventorymanagementsystemapp.domain.Product;
import com.bertha.inventorymanagementsystemapp.domain.Stock;
import com.bertha.inventorymanagementsystemapp.domain.Supplier;
import com.bertha.inventorymanagementsystemapp.dto.request.ProductRequest;
import com.bertha.inventorymanagementsystemapp.dto.request.SupplierRequestWithId;
import com.bertha.inventorymanagementsystemapp.dto.response.ProductResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.StockResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponseForProduct;
import com.bertha.inventorymanagementsystemapp.repository.ProductRepository;
import com.bertha.inventorymanagementsystemapp.repository.StockRepository;
import com.bertha.inventorymanagementsystemapp.repository.SupplierRepository;
import com.bertha.inventorymanagementsystemapp.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements ProductService {


    private ProductRepository   productRepository;

    private SupplierRepository supplierRepository;

    private StockRepository stockRepository;

    public ProductServiceImp(ProductRepository productRepository, SupplierRepository supplierRepository, StockRepository stockRepository) {
        this.productRepository = productRepository;
        this.supplierRepository = supplierRepository;
        this.stockRepository = stockRepository;
    }
    @Override
    public ProductResponse addProduct(ProductRequest productRequest) {
        if (   productRepository.findByProductCode(productRequest.productCode()).isPresent()){
            throw new RuntimeException("Product with code " + productRequest.productCode() + " already exists");
        }
        Supplier supplier =  new Supplier(null, productRequest.SupplierRequest().supplierName(), productRequest.SupplierRequest().supplierEmail(), productRequest.SupplierRequest().supplierPhone(), null);
        supplierRepository.save(supplier);

        Stock stock = new Stock(null, productRequest.stockRequestForProduct().stockQuantity(), null);
        stockRepository.save(stock);

        Product product = new Product(null, productRequest.productCode(), productRequest.productName(), productRequest.productCategory(), productRequest.price(), supplier,stock, null);
        productRepository.save(product);
        return new ProductResponse(product.getProductId(),  product.getProductCode(),product.getProductName(), product.getProductCategory(),
                product.getPrice(), new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(), product.getSupplier().getSupplierEmail(),
                product.getSupplier().getSupplierPhone()), new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null));

    }

    @Override
    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        if (products.size() == 0) {
            return null;
        }

        return products.stream().map(product -> new ProductResponse(product.getProductId(), product.getProductCode(), product.getProductName(), product.getProductCategory(),
                product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(),
                product.getSupplier().getSupplierEmail(),
                product.getSupplier().getSupplierPhone()),
                new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null))).toList();
    }

    @Override
    public ProductResponse getProductById(Long id) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        return new ProductResponse(product.getProductId(),product.getProductCode(), product.getProductName(),  product.getProductCategory(),
                product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(),
                product.getSupplier().getSupplierEmail(),
                product.getSupplier().getSupplierPhone()),
                new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null));
    }

    @Override
    public ProductResponse getProductByCode(String productCode) {
        Product product = productRepository.findByProductCode(productCode).orElse(null);
        if (product == null) {
            return null;
        }

        return new ProductResponse(product.getProductId(), product.getProductCode(),product.getProductName(),  product.getProductCategory(),
                product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(),
                product.getSupplier().getSupplierEmail(),
                product.getSupplier().getSupplierPhone()),
                new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null))    ;
    }



    @Override
    public ProductResponse getProductByName(String productName) {
        Product product = productRepository.findByProductName(productName).orElse(null);
        if (product == null) {
            return null;
        }
        return new ProductResponse(product.getProductId(), product.getProductCode(),product.getProductName(),  product.getProductCategory(),
                product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(),
                product.getSupplier().getSupplierEmail(),
                product.getSupplier().getSupplierPhone()),
                new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null));
    }



    @Override
    public String deleteProduct(Long id) {

        if (productRepository.findById(id).isEmpty()) {
            return "Product does not exist";
        }

        productRepository.deleteById(id);
        return "Product deleted successfully";

    }
    @Override
    public ProductResponse updateProduct(ProductRequest productRequest, Long id) {
           Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        } product.setProductName(productRequest.productName());
            product.setProductCode(productRequest.productCode());
            product.setProductCategory(productRequest.productCategory());
            product.setPrice(productRequest.price());

            productRepository.save(product);

            return new ProductResponse(product.getProductId(), product.getProductCode(),product.getProductName(),  product.getProductCategory(),
                    product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                    product.getSupplier().getSupplierName(),
                    product.getSupplier().getSupplierEmail(),
                    product.getSupplier().getSupplierPhone()),
                    new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null));
    }

    @Override
    public ProductResponse updateProductSupplier(ProductRequest productRequest, Long id, SupplierRequestWithId supplierRequest) {
        Product product = productRepository.findById(id).orElse(null);
        if (product == null) {
            return null;
        }
        product.setProductName(productRequest.productName());
        product.setProductCode(productRequest.productCode());
        product.setProductCategory(productRequest.productCategory());
        product.setPrice(productRequest.price());
        product.setSupplier(new Supplier( supplierRequest.id(), supplierRequest.supplierName(), supplierRequest.supplierEmail(), supplierRequest.supplierPhone(), null));
        productRepository.save(product);

        Supplier supplier = supplierRepository.findById(supplierRequest.id()).orElse(null);
        if (supplier != null) {
            supplier.setSupplierName(supplierRequest.supplierName());
            supplier.setSupplierEmail(supplierRequest.supplierEmail());
            supplier.setSupplierPhone(supplierRequest.supplierPhone());
            supplierRepository.save(supplier);
        }

        return new ProductResponse(product.getProductId(), product.getProductCode(),  product.getProductName(),product.getProductCategory(),
                product.getPrice(),  new SupplierResponseForProduct(product.getSupplier().getSupplierId(),
                product.getSupplier().getSupplierName(), supplier.getSupplierEmail(), supplier.getSupplierPhone()),
                new StockResponse(product.getStock().getStockId(), product.getStock().getStockQuantity(), null));
    }
}

