package com.minsait.order_service.controller;

import com.minsait.order_service.model.Order;
import com.minsait.order_service.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map; //nuevo

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // GET /orders
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    // POST /orders/{orderId}/products
    @PostMapping("/{orderId}/products")
    public Order addProductsToOrder(@PathVariable Long orderId, @RequestBody Map<String, List<Long>> body) {
        List<Long> productIds = body.get("productIds");
        return orderService.addProductsToOrder(orderId, productIds);
    }

    // GET /orders/{id}
    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    // POST /orders
    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    // DELETE /orders/{id}
    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
