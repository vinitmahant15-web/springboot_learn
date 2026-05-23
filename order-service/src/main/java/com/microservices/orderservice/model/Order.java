package com.microservices.orderservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

/**
 * Order Entity
 * 
 * Note: This doesn't have foreign keys to User and Product.
 * Instead, it stores IDs and calls other services to get details.
 * This is the "database per service" pattern.
 */
@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;      // Reference to User Service

    @Column(nullable = false)
    private Long productId;    // Reference to Product Service

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private String status;     // PENDING, CONFIRMED, CANCELLED

    @Column(name = "created_at")
    private String createdAt;

}
