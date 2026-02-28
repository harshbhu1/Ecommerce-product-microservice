# Ecommerce Product Microservice 

## Overview

This project is an Ecommerce Microservice built using Spring Boot and Hibernate, following Microservices Architecture principles. It provides scalable, maintainable, and modular backend services for managing ecommerce operations such as product management, order processing, and user management.

The system is designed using RESTful APIs and follows industry best practices including layered architecture, proper exception handling, and database abstraction using Hibernate ORM.

---

## Architecture

The application follows a layered architecture:

* Controller Layer – Handles HTTP requests and responses
* Service Layer – Contains business logic
* Repository Layer – Handles database operations using Hibernate and JPA
* Entity Layer – Represents database tables
* DTO Layer – Used for request/response data transfer

---

## Tech Stack

**Backend:**

* Java 17+
* Spring Boot
* Spring Web
* Spring Data JPA
* Hibernate

**Database:**

* MySQL / PostgreSQL

**Build Tool:**

* Maven

**Other Tools:**

* Lombok
* Postman (API testing)

---

## Features

* Product Management

  * Create product
  * Update product
  * Delete product
  * Get product by ID
  * Get all products

* Order Management

  * Create order
  * View order details
  * Update order status

* User Management

  * Create user
  * Get user details

* RESTful API design

* Exception handling

* Database integration using Hibernate ORM

---

## Project Structure

```
ecommerce-microservice
│
├── controller
├── service
├── repository
├── entity
├── dto
├── exception
├── config
└── EcommerceApplication.java
```

---

## Prerequisites

Make sure you have installed:

* Java 17 or above
* Maven
* MySQL or PostgreSQL
* IDE (IntelliJ IDEA / Eclipse / VS Code)

---

## Database Configuration

Update the `application.properties` file:

```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db
spring.datasource.username=your_username
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

---

## How to Run the Project

### Step 1: Clone the repository

```
git clone https://github.com/your-username/ecommerce-microservice.git
```

### Step 2: Navigate to project folder

```
cd ecommerce-microservice
```

### Step 3: Build the project

```
mvn clean install
```

### Step 4: Run the application

```
mvn spring-boot:run
```

Application will start at:

```
http://localhost:8080
```

---

## Sample API Endpoints

### Product APIs

Create Product

```
POST /api/products
```

Get All Products

```
GET /api/products
```

Get Product By ID

```
GET /api/products/{id}
```

Update Product

```
PUT /api/products/{id}
```

Delete Product

```
DELETE /api/products/{id}
```

---

## Exception Handling

Global exception handling is implemented using:

* @ControllerAdvice
* Custom exception classes

---

## Future Improvements

* Add authentication and authorization (JWT)
* Implement API Gateway
* Add Service Discovery (Eureka)
* Add Docker support
* Add Unit and Integration tests
* Add caching using Redis

---

## Testing

You can test APIs using:

* Postman
* Curl

---

## Author

Harsh Maurya
Backend Developer
Java | Spring Boot | Hibernate | Microservices

---

## License

This project is for learning and demonstration purposes.
