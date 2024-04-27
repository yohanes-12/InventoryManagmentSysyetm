package com.bertha.inventorymanagementsystemapp.service.imp;

import com.bertha.inventorymanagementsystemapp.domain.Customer;
import com.bertha.inventorymanagementsystemapp.domain.Product;
import com.bertha.inventorymanagementsystemapp.domain.ProductOrder;
import com.bertha.inventorymanagementsystemapp.domain.Stock;
import com.bertha.inventorymanagementsystemapp.dto.request.OrderRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.CustomerResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.OrdersResponse2;
import com.bertha.inventorymanagementsystemapp.dto.response.ProductResponse;
import com.bertha.inventorymanagementsystemapp.dto.response.SupplierResponseForProduct;
import com.bertha.inventorymanagementsystemapp.repository.CustomerRepository;
import com.bertha.inventorymanagementsystemapp.repository.OrderRepository;
import com.bertha.inventorymanagementsystemapp.repository.ProductRepository;
import com.bertha.inventorymanagementsystemapp.repository.StockRepository;
import com.bertha.inventorymanagementsystemapp.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderServiceImp implements OrderService {

    private final OrderRepository orderRepository;

    private final ProductRepository productRepository;
    CustomerRepository customerRepository;
    private final StockRepository stockRepository;

   public OrderServiceImp(
           OrderRepository orderRepository,
           ProductRepository productRepository,
           StockRepository stockRepository,
           CustomerRepository customerRepository
   ) {
       this.orderRepository = orderRepository;
       this.productRepository = productRepository;
       this.stockRepository = stockRepository;
       this.customerRepository = customerRepository;
   }
     @Override
    public String registerOrder(OrderRequest orderRequest) {
       Customer customer = customerRepository.findById(orderRequest.customerId()).orElse(null);
       if(customer == null) return "Customer not found";

       ProductOrder newOrder  = new ProductOrder();
       newOrder.setOrderDate(orderRequest.orderDate());
       newOrder.setOrderQuantity(orderRequest.orderQuality());
       newOrder.setOrderPrice(orderRequest.price());
       newOrder.setCustomer(customer);

       List<Product> products = orderRequest.productId().stream().map(
               productRepository::findByProductId
       ).toList();

       products.stream().forEach(
               product -> {
                   Stock stock = product.getStock();
                   stock.setQuantity(stock.getStockQuantity() - 1);
                   product.setStock(stock);
               }
       );

       newOrder.setProducts(products);
       orderRepository.save(newOrder);

       return "Order registered successfully";
    }

    @Override
    public List<OrdersResponse2> getAllOrders() {
        List<ProductOrder> orders = orderRepository.findAll();
        List<OrdersResponse2> ordersResponse2List = orders.stream().map(order ->
                new OrdersResponse2(order.getOrderId(), order.getOrderDate(), order.getOrderPrice(), order.getOrderQuantity(),
                order.getProducts().stream().map(product ->
                        new ProductResponse(product.getProductId(), product.getProductCode(), product.getProductName(), product.getProductCategory(), product.getPrice(),
                                new SupplierResponseForProduct(product.getSupplier().getSupplierId(), product.getSupplier().getSupplierName(), product.getSupplier().getSupplierEmail(), product.getSupplier().getSupplierPhone()), null)).toList(),
                        new CustomerResponse(order.getCustomer().getCustomerId(), order.getCustomer().getCustomerName(), order.getCustomer().getCustomerEmail(), order.getCustomer().getCustomerPhone()))).toList();

        return ordersResponse2List;
    }

        @Override
    public String deleteOrder(Long orderId) {
        if(!orderRepository.existsById(orderId)){
            return "Order not found";
        }
        orderRepository.deleteById(orderId);
        return "Order deleted successfully";
    }


    @Override
    public OrdersResponse2 getOrdersByCustomerId(Long customerId) {
        ProductOrder order = orderRepository.findByCustomer_CustomerId(customerId);
        if (order == null){
            return null;
        }


        return new OrdersResponse2(order.getOrderId(), order.getOrderDate(), order.getOrderPrice(), order.getOrderQuantity(),
                order.getProducts().stream().map(product -> new ProductResponse(product.getProductId(), product.getProductCode(), product.getProductName(), product.getProductCategory(), product.getPrice(), new SupplierResponseForProduct(product.getSupplier().getSupplierId(), product.getSupplier().getSupplierName(), product.getSupplier().getSupplierEmail(), product.getSupplier().getSupplierPhone()), null)).toList(), new CustomerResponse(order.getCustomer().getCustomerId(), order.getCustomer().getCustomerName(), order.getCustomer().getCustomerEmail(), order.getCustomer().getCustomerPhone()));
    }
}
