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

