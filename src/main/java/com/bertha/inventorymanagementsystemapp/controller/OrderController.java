package com.bertha.inventorymanagementsystemapp.controller;


import com.bertha.inventorymanagementsystemapp.dto.request.OrderRequest;
import com.bertha.inventorymanagementsystemapp.dto.response.OrdersResponse2;
import com.bertha.inventorymanagementsystemapp.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ims/api/v1/order")
public class OrderController {


    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @PostMapping("/register")
    public ResponseEntity<?> registerOrder(@RequestBody @Valid OrderRequest orderRequest){
        return ResponseEntity.ok(orderService.registerOrder(orderRequest));
    }

    @GetMapping("/list")
    public ResponseEntity<List<OrdersResponse2>> getOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @GetMapping("/get/{customerId}")
    public ResponseEntity<OrdersResponse2> getOrdersByCustomerId(@PathVariable Long customerId){
        return ResponseEntity.ok(orderService.getOrdersByCustomerId(customerId));
    }

    @DeleteMapping("/delete/{orderId}")
    public ResponseEntity<?> deleteOrder(@PathVariable Long orderId){
        return ResponseEntity.ok(orderService.deleteOrder(orderId));
    }


}
