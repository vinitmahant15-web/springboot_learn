package com.microservices.userservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.*;

/**
 * User Entity
 * 
 * Represents a user in the database.
 * 
 * @Entity - Maps this class to a database table
 * @Data - Lombok annotation that generates getters, setters, toString(), equals(), hashCode()
 * @NoArgsConstructor - Generates a no-argument constructor
 * @AllArgsConstructor - Generates a constructor with all fields as parameters
 */
@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String address;

}
