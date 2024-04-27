package com.bertha.inventorymanagementsystemapp.service;

import com.bertha.inventorymanagementsystemapp.dto.request.OrderRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.OrdersResponse2;

import java.util.List;

public interface OrderService {
    String registerOrder(OrderRequest orderRequest);

    List<OrdersResponse2> getAllOrders();

    String deleteOrder(Long orderId);


    OrdersResponse2 getOrdersByCustomerId(Long customerId);
}
