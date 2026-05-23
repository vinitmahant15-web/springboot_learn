package com.microservices.orderservice.service;

import com.microservices.orderservice.model.Order;
import com.microservices.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;

/**
 * Order Service - Business Logic Layer
 * 
 * This service demonstrates inter-service communication.
 * It calls User Service and Product Service to validate orders.
 */
@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

    private static final String USER_SERVICE_URL = "http://user-service/api/users";
    private static final String PRODUCT_SERVICE_URL = "http://product-service/api/products";

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Optional<Order> getOrderById(Long id) {
        return orderRepository.findById(id);
    }

    /**
     * Create order with validation
     * 
     * This demonstrates inter-service communication:
     * 1. Check if user exists (call User Service)
     * 2. Check if product exists (call Product Service)
     * 3. Create order if both exist
     */
    public Order createOrder(Order order) {
        try {
            // Call User Service to verify user exists
            Boolean userExists = restTemplate.getForObject(
                USER_SERVICE_URL + "/check/" + order.getUserId(),
                Boolean.class
            );

            if (userExists == null || !userExists) {
                throw new RuntimeException("User not found");
            }

            // Call Product Service to verify product exists
            Boolean productExists = restTemplate.getForObject(
                PRODUCT_SERVICE_URL + "/check/" + order.getProductId(),
                Boolean.class
            );

            if (productExists == null || !productExists) {
                throw new RuntimeException("Product not found");
            }

            // Both validations passed, create order
            order.setStatus("CONFIRMED");
            return orderRepository.save(order);

        } catch (Exception e) {
            order.setStatus("FAILED");
            throw new RuntimeException("Order creation failed: " + e.getMessage());
        }
    }

    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        
        order.setUserId(orderDetails.getUserId());
        order.setProductId(orderDetails.getProductId());
        order.setQuantity(orderDetails.getQuantity());
        order.setStatus(orderDetails.getStatus());
        
        return orderRepository.save(order);
    }

    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
