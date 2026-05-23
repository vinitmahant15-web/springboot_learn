# Spring Boot Microservices Learning Project

A comprehensive learning project for Spring Boot Microservices with 4 independent services.

## 📚 Project Structure

```
ecommerce-microservices/
├── user-service/           (Port 8001)
├── product-service/        (Port 8002)
├── order-service/          (Port 8003)
├── api-gateway/            (Port 8080)
└── pom.xml                 (Parent POM)
```

## 🎯 Services Included

### 1. User Service (Port 8001)
- REST API for user management
- CRUD operations
- Service discovery enabled
- Database: H2 (in-memory)

### 2. Product Service (Port 8002)
- REST API for product management
- CRUD operations
- Service discovery enabled
- Database: H2 (in-memory)

### 3. Order Service (Port 8003)
- REST API for order management
- Inter-service communication (calls User & Product Services)
- Service discovery enabled
- Database: H2 (in-memory)

### 4. API Gateway (Port 8080)
- Single entry point for all clients
- Routes requests to appropriate services
- Load balancing
- Service discovery integration

## 🚀 How to Run

### Prerequisites
- Java 17+
- Maven 3.6+

### Start Services (Open 4 Terminals)

```bash
# Terminal 1: User Service
cd user-service
mvn spring-boot:run

# Terminal 2: Product Service
cd product-service
mvn spring-boot:run

# Terminal 3: Order Service
cd order-service
mvn spring-boot:run

# Terminal 4: API Gateway
cd api-gateway
mvn spring-boot:run
```

## 📡 API Endpoints

### User Service (Port 8001)
```
GET    /api/users              - Get all users
GET    /api/users/{id}         - Get user by ID
POST   /api/users              - Create user
PUT    /api/users/{id}         - Update user
DELETE /api/users/{id}         - Delete user
GET    /api/users/check/{id}   - Check if user exists
```

### Product Service (Port 8002)
```
GET    /api/products           - Get all products
GET    /api/products/{id}      - Get product by ID
POST   /api/products           - Create product
PUT    /api/products/{id}      - Update product
DELETE /api/products/{id}      - Delete product
```

### Order Service (Port 8003)
```
GET    /api/orders             - Get all orders
GET    /api/orders/{id}        - Get order by ID
POST   /api/orders             - Create order
PUT    /api/orders/{id}        - Update order
DELETE /api/orders/{id}        - Delete order
```

## 🧪 Testing Examples

### Create a User
```bash
curl -X POST http://localhost:8001/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "John Doe",
    "email": "john@example.com",
    "phone": "9876543210",
    "address": "123 Main St"
  }'
```

### Get All Users
```bash
curl http://localhost:8001/api/users
```

### Create a Product
```bash
curl -X POST http://localhost:8002/api/products \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Laptop",
    "description": "Dell XPS",
    "price": 50000.0,
    "stock": 10
  }'
```

### Create an Order
```bash
curl -X POST http://localhost:8003/api/orders \
  -H "Content-Type: application/json" \
  -d '{
    "userId": 1,
    "productId": 1,
    "quantity": 2
  }'
```

## 🎓 Key Concepts Covered

- ✅ Microservices Architecture
- ✅ Service Discovery (Eureka)
- ✅ REST APIs
- ✅ Inter-Service Communication
- ✅ API Gateway
- ✅ Layered Architecture
- ✅ Database per Service
- ✅ Dependency Injection (IoC)
- ✅ Spring Data JPA
- ✅ H2 Database
- ✅ HTTP Methods & Status Codes

## 📚 Learning Path

1. **Week 1:** Understand User Service
2. **Week 2:** Understand Product Service
3. **Week 3:** Learn Inter-Service Communication (Order Service)
4. **Week 4:** Implement API Gateway
5. **Week 5:** Add Eureka Server for Service Discovery
6. **Week 6:** Add Unit & Integration Tests
7. **Week 7:** Dockerize Services
8. **Week 8:** Deploy to Kubernetes

## 🛠️ Technologies Used

- Spring Boot 3.1.5
- Spring Data JPA
- Spring Cloud 2022.0.4
- H2 Database
- Maven
- Java 17
- Lombok

## 📝 Project Details

Each service includes:
- Entity models
- Repository layer (Data Access)
- Service layer (Business Logic)
- Controller layer (REST API)
- Configuration files
- Maven POM

## 🤝 Contributing

This is a learning project. Feel free to modify and extend it.

## 📄 License

MIT License