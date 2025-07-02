package com.minsait.order_service.service;

import com.minsait.order_service.model.Order;
import com.minsait.order_service.model.Product;
import com.minsait.order_service.repository.OrderRepository;
import com.minsait.order_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    public Order addProductsToOrder(Long orderId, List<Long> productIds) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Orden no encontrada"));

        List<Product> products = productRepository.findAllById(productIds);
        order.setProducts(products);  // o puedes usar: order.getProducts().addAll(products);

        return orderRepository.save(order);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}



