# Spring Boot Microservices Learning Guide

## 📚 Week-by-Week Learning Path

### Week 1: Understanding User Service

#### Concepts to Learn:
1. **Spring Boot Application**
   - `@SpringBootApplication` annotation
   - Main method and application startup

2. **Layered Architecture**
   - Entity Layer (Model)
   - Repository Layer (Data Access)
   - Service Layer (Business Logic)
   - Controller Layer (REST API)

3. **REST API Basics**
   - HTTP Methods: GET, POST, PUT, DELETE
   - Request/Response cycle
   - HTTP Status Codes: 200, 201, 204, 404

4. **Spring Data JPA**
   - Entity mapping with `@Entity`
   - Repository interface extending `JpaRepository`
   - CRUD operations

#### Hands-On Tasks:
```bash
# 1. Run User Service
cd user-service
mvn spring-boot:run

# 2. Test Create User
curl -X POST http://localhost:8001/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "address": "123 Main St"
  }'

# 3. Test Get All Users
curl http://localhost:8001/api/users

# 4. Test Get User by ID
curl http://localhost:8001/api/users/1

# 5. Test Update User
curl -X PUT http://localhost:8001/api/users/1 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Jane Doe",
    "email": "jane@example.com",
    "phone": "1234567890",
    "address": "456 Oak Ave"
  }'

# 6. Test Delete User
curl -X DELETE http://localhost:8001/api/users/1
```

#### Key Files to Study:
- `UserServiceApplication.java` - Application entry point
- `User.java` - Entity model
- `UserRepository.java` - Data access
- `UserService.java` - Business logic
- `UserController.java` - REST API endpoints
- `application.yml` - Configuration

---

### Week 2: Understanding Product Service

#### Concepts to Learn:
1. **Microservice Independence**
   - Separate database (H2 instance)
   - Separate port (8002)
   - Own configuration

2. **Same Architecture Pattern**
   - Entity → Repository → Service → Controller
   - Consistency across services

3. **Database Isolation**
   - Each service has its own database
   - No shared data sources
   - No direct database access between services

#### Hands-On Tasks:
```bash
# 1. Run Product Service
cd product-service
mvn spring-boot:run

# 2. Create Products
curl -X POST http://localhost:8002/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "Dell XPS",
    "price": 50000.0,
    "stock": 10
  }'

# 3. Get All Products
curl http://localhost:8002/api/products
```

#### Key Learning Points:
- Product Service has identical structure to User Service
- Different database (productdb vs userdb)
- Different port (8002 vs 8001)
- Both are completely independent

---

### Week 3: Inter-Service Communication

#### Concepts to Learn:
1. **RestTemplate**
   - Making HTTP calls to other services
   - Synchronous communication
   - Error handling

2. **Service Discovery (Eureka)**
   - Services register by name
   - Use service name instead of hardcoded URLs
   - Load balancing

3. **Order Service Pattern**
   - Validates data from multiple services
   - Uses service names: `http://user-service/...`
   - Fails gracefully if dependencies unavailable

#### Hands-On Tasks:
```bash
# 1. Run all services
# Terminal 1
cd user-service && mvn spring-boot:run

# Terminal 2
cd product-service && mvn spring-boot:run

# Terminal 3
cd order-service && mvn spring-boot:run

# 2. Create a user first
curl -X POST http://localhost:8001/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"John","email":"john@test.com","phone":"123","address":"123 Main"}'

# 3. Create a product
curl -X POST http://localhost:8002/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Laptop","description":"Dell","price":50000,"stock":10}'

# 4. Create order (calls User Service and Product Service)
curl -X POST http://localhost:8003/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"productId":1,"quantity":2}'

# 5. Observe logs - you'll see service calls
```

#### Key Learning Points:
- Order Service calls User Service to verify user
- Order Service calls Product Service to verify product
- Uses `RestTemplate` for HTTP calls
- Service names discovered through Eureka (when configured)

---

### Week 4: API Gateway

#### Concepts to Learn:
1. **Gateway Pattern**
   - Single entry point for all clients
   - Routes requests to appropriate services
   - Centralized configuration

2. **Routing Rules**
   - Path-based routing
   - Service discovery integration
   - Load balancing

3. **Benefits**
   - Clients only need to know gateway URL
   - Services can move/scale without client changes
   - Centralized security, logging, etc.

#### Hands-On Tasks:
```bash
# 1. Run API Gateway
cd api-gateway
mvn spring-boot:run

# 2. Create user through gateway
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{"name":"Jane","email":"jane@test.com","phone":"456","address":"456 Oak"}'

# 3. Get users through gateway
curl http://localhost:8080/api/users

# 4. Create product through gateway
curl -X POST http://localhost:8080/api/products \
  -H "Content-Type: application/json" \
  -d '{"name":"Phone","description":"iPhone","price":80000,"stock":5}'

# 5. Create order through gateway
curl -X POST http://localhost:8080/api/orders \
  -H "Content-Type: application/json" \
  -d '{"userId":1,"productId":1,"quantity":1}'
```

#### Key Learning Points:
- All requests go through gateway on port 8080
- Gateway routes to correct service based on path
- Users don't know about individual service ports
- Easy to add authentication, logging, rate limiting

---

### Week 5: Dependency Injection & IoC

#### Concepts to Learn:
1. **IoC Container**
   - Spring manages object creation
   - `ApplicationContext` holds all beans
   - Automatic dependency injection

2. **@Autowired**
   - Constructor injection
   - Setter injection
   - Field injection

3. **Loose Coupling**
   - Services don't create dependencies
   - Dependencies injected from outside
   - Easy to swap implementations

#### Hands-On Example:
```java
// Without IoC (Tightly Coupled)
public class OrderService {
    private UserService userService = new UserService();  // Creates directly
}

// With IoC (Loosely Coupled)
@Service
public class OrderService {
    @Autowired
    private UserService userService;  // Spring injects this
}
```

---

### Week 6: Unit Testing

#### Concepts to Learn:
1. **Mocking**
   - Creating fake objects
   - Controlling mock behavior

2. **Testing Services**
   - Isolate service from dependencies
   - Test business logic only
   - Use `@ExtendWith(MockitoExtension.class)`

3. **Assertions**
   - Verify expected behavior
   - Check return values
   - Verify mock calls

#### Example Test:
```java
@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    @InjectMocks
    private UserService userService;
    
    @Test
    public void testGetUserById() {
        // Arrange
        User mockUser = new User(1L, "John", "john@test.com", "123", "123 Main");
        when(userRepository.findById(1L)).thenReturn(Optional.of(mockUser));
        
        // Act
        Optional<User> result = userService.getUserById(1L);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals("John", result.get().getName());
    }
}
```

---

### Week 7: Integration Testing

#### Concepts to Learn:
1. **@SpringBootTest**
   - Loads entire Spring context
   - Tests with real beans
   - Slower but more realistic

2. **TestRestTemplate**
   - Makes HTTP calls in tests
   - Tests REST endpoints
   - Integration testing

3. **Test Database**
   - In-memory database (H2)
   - Isolated test data
   - Auto cleanup

---

### Week 8: Containerization & Deployment

#### Concepts to Learn:
1. **Docker**
   - Containerize services
   - Docker Compose for local testing
   - Environment configuration

2. **Kubernetes** (Optional)
   - Deploy containers
   - Service discovery
   - Scaling

---

## 🎯 Key Microservices Patterns

### 1. Database per Service
```
┌─────────────────┐
│  User Service   │
│  ┌───────────┐  │
│  │ UserDB    │  │
│  └───────────┘  │
└─────────────────┘

┌─────────────────┐
│ Product Service │
│ ┌───────────┐   │
│ │ ProductDB │   │
│ └───────────┘   │
└─────────────────┘
```

### 2. Service Discovery
```
┌────────────────┐
│ Eureka Server  │
│ (Port 8761)    │
└────────────────┘
       ↑
   ┌───┼───┐
   ↓   ↓   ↓
 User Product Order
Service Service  Service
```

### 3. API Gateway Pattern
```
┌────────┐
│ Client │
└────┬───┘
     ↓
┌──────────────┐
│ API Gateway  │ (Port 8080)
│ (Port 8080)  │
└─┬──────┬───┬─┘
  ↓      ↓   ↓
 User Product Order
Service Service  Service
```

---

## 📝 Important Files Reference

### Configuration Files
- `application.yml` - Spring Boot configuration
- `pom.xml` - Maven dependencies and plugins

### Source Code Locations
- `UserService` - User business logic
- `UserController` - User REST endpoints
- `UserRepository` - User data access
- `User.java` - User entity/model

### Test Files
- `*ServiceTest.java` - Unit tests
- `*ControllerTest.java` - Integration tests

---

## 🚀 Next Steps After This Learning

1. **Add Authentication**
   - Spring Security
   - JWT tokens
   - Role-based access

2. **Add Caching**
   - Redis
   - Spring Cache abstractions

3. **Add Message Queue**
   - RabbitMQ
   - Kafka
   - Asynchronous communication

4. **Add Monitoring**
   - Prometheus
   - Grafana
   - Spring Boot Actuators

5. **Add Distributed Tracing**
   - Sleuth
   - Zipkin
   - Request tracking

---

## 🎓 Summary of Concepts

✅ Microservices Architecture  
✅ Independent Services  
✅ Database per Service Pattern  
✅ REST APIs  
✅ Inter-Service Communication  
✅ API Gateway  
✅ Service Discovery  
✅ Dependency Injection (IoC)  
✅ Layered Architecture  
✅ Unit Testing with Mocks  
✅ Spring Boot  
✅ Spring Data JPA  
✅ H2 Database  

Great job learning microservices! 🚀