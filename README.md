# Accounts Microservice - README

## 1. What is a Microservice?
Microservice architecture is a software development approach where a large application is divided into small, independently deployable services. Each service is responsible for a specific business function and communicates with other services over lightweight protocols like HTTP.

### Difference between Microservice, Monolithic, and SOA Architecture:
- **Monolithic Architecture:**
  - All components are combined into a single program and deployed together.
  - Difficult to scale and maintain over time.
  - A failure in one module can potentially bring down the entire application.

- **Service-Oriented Architecture (SOA):**
  - Components are separated into services, but often share enterprise-level infrastructure such as ESBs (Enterprise Service Bus).
  - More heavyweight and complex compared to microservices.

- **Microservices Architecture:**
  - Services are fine-grained, independently deployable, and loosely coupled.
  - Promotes continuous delivery and scalability.
  - Each service can use a different tech stack and database.

## 2. Advantages and Bottlenecks of Microservices

### Advantages:
- Independent deployment and scaling.
- Better fault isolation.
- Easier to understand, develop, and maintain.
- Enables continuous delivery and integration.
- Allows use of different technologies and databases per service.

### Bottlenecks:
- Increased complexity in service communication.
- Requires robust DevOps practices.
- Difficulties in managing distributed systems (e.g., debugging, tracing, consistency).
- Network latency and failures.
- Needs proper handling of inter-service security and data management.

## 3. Standards for REST API Design

To ensure consistency, reusability, and maintainability, the following standards should be followed while designing REST APIs in the `accounts-microservice`:

### a) Endpoint Definition:
- Use **nouns** to define endpoints, not verbs.
- Follow a hierarchical structure based on resources.

**Examples:**
```
GET /accounts/{accountId}
POST /accounts
PUT /accounts/{accountId}
DELETE /accounts/{accountId}
```

### b) Layered Architecture:
- **Controller Layer**: Responsible for handling HTTP requests and mapping them to appropriate service methods.
- **Service Layer**: Contains business logic, validation, and orchestration logic.
- **Repository Layer**: Handles interaction with the database using Spring Data JPA or MongoRepositories.

### c) Global Exception Handling:
Use `@ControllerAdvice` to manage exceptions centrally.

**Example:**
```java
@ResponseStatus(HttpStatus.NOT_FOUND)
@ExceptionHandler(AccountNotFoundException.class)
public ErrorResponse handleAccountNotFound(AccountNotFoundException ex) {
    return new ErrorResponse("ACCOUNT_NOT_FOUND", ex.getMessage());
}
```

### d) Input Validation:
Use `spring-boot-starter-validation` with annotations like `@NotBlank`, `@Size`, `@Pattern`, etc.

**Example Request DTO:**
```java
public class AccountRequest {
    @NotBlank(message = "Mobile number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotBlank(message = "Name is mandatory")
    private String name;
}
```

### e) Necessity of Documentation using OpenAPI Specification:
- **Standardization**: Provides a well-defined contract for API consumers.
- **Automation**: Helps generate client SDKs, API documentation, and server stubs automatically.
- **Discoverability**: Developers can explore API capabilities through tools like Swagger UI.
- **Consistency**: Ensures consistent documentation across services.
- **Validation**: Helps validate API requests and responses against defined schemas.

**Example OpenAPI Specification (YAML Format):**
```yaml
openapi: 3.0.0
info:
  title: Accounts Microservice API
  version: 1.0.0
tags:
  - name: Accounts
paths:
  /accounts:
    get:
      summary: Retrieve all accounts
      operationId: getAccounts
      responses:
        '200':
          description: Successfully retrieved accounts
```
In order to see the entire list of APIs, you can visit the [Swagger UI](http://localhost:8080/swagger-ui/index.html).

## 4. Database Entities
### Entity Relationship Diagram
Below is the ER diagram representing the relationships between **customers**, **accounts**, and **addresses**:

![Database Schema](images/eer-diagram.png)

### Customers Table
Stores customer details.
```sql
CREATE TABLE `customers` (
  `customer_id` varchar(255) NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `mobile` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

### Add payee table
Stores payee details linked to account
```
CREATE TABLE `add_payee` (
  `payee_id` bigint NOT NULL AUTO_INCREMENT,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `account_holder_name` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `is_bank_same_as_sender` bit(1) NOT NULL,
  `receiver_account_id` bigint DEFAULT NULL,
  `account_id` bigint NOT NULL,
  PRIMARY KEY (`payee_id`),
  UNIQUE KEY `UKpw2pf63c0ba5xjdrijkbschmy` (`account_id`),
  CONSTRAINT `FK8a7de012gb612mk3n0iqepfuk` FOREIGN KEY (`account_id`) REFERENCES `accounts` (`account_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

### Accounts Table
Stores account details linked to customers.
```sql
CREATE TABLE `accounts` (
  `account_id` bigint NOT NULL,
  `created_at` datetime(6) DEFAULT NULL,
  `created_by` varchar(255) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  `updated_by` varchar(255) DEFAULT NULL,
  `account_status` enum('ACTIVE','INACTIVE','PENDING') DEFAULT NULL,
  `account_type` enum('CURRENT','SAVINGS') DEFAULT NULL,
  `branch_address` varchar(255) DEFAULT NULL,
  `ifsc_code` varchar(255) DEFAULT NULL,
  `customer_id` varchar(255) NOT NULL,
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `UKd7wccbpluupn8cbm0o7nc1mhj` (`customer_id`),
  CONSTRAINT `FKn6x8pdp50os8bq5rbb792upse` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`customer_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```

### Address Table
Stores address details linked to customers.
```sql
CREATE TABLE address (
    address_id BIGINT PRIMARY KEY,
    created_at DATETIME(6),
    created_by VARCHAR(255),
    updated_at DATETIME(6),
    updated_by VARCHAR(255),
    address_type ENUM(...),
    city VARCHAR(255),
    country VARCHAR(255),
    first_line VARCHAR(255),
    second_line VARCHAR(255),
    state VARCHAR(255),
    zip VARCHAR(255),
    customer_id VARCHAR(255),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);
```

### Entity Relationships
- One **customer** can have multiple **accounts**.
- One **customer** can have multiple **addresses**.

Further sections will include details on:
- Security
- Logging
- AOP
- Retry Mechanisms
- Fault Tolerance
- Caching
- Testing strategy
- Deployment strategy

_Stay tuned for more updates._

