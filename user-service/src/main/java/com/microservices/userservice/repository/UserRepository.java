package com.microservices.userservice.repository;

import com.microservices.userservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User Repository
 * 
 * Spring Data JPA Repository for User entity.
 * Provides CRUD operations automatically:
 * - save() - Create/Update
 * - findById() - Read
 * - findAll() - Read all
 * - delete() - Delete
 * 
 * JpaRepository<Entity, PrimaryKeyType>
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
}
