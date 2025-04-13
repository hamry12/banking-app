# 💸 Transactions Microservice

This microservice is responsible for handling **balance inquiries**, **fund transfers**, and **transaction histories**. It supports different transfer mechanisms such as **IMPS**, **NEFT**, etc., and is built using **design principles and patterns** such as the **Strategy Pattern**, **Open/Closed Principle**, and **Observer Pattern**.

---

## 📑 Table of Contents

- [Features](#-features)
- [Design Principles & Patterns](#-design-principles--patterns)
- [API Documentation](#-api-documentation)
- [Endpoints](#-endpoints)
- [Transfer Strategy Example](#-transfer-strategy-example)
- [Entity Design](#-entity-design)
- [Constants Used](#-constants-used)
- [Api Communication](#-api-communication)
- [Future Enhancements](#-future-enhancements)

---

## ✅ Features

- 🔍 Check account balance
- 💸 Fund transfer with IMPS/NEFT and charge calculation
- 🧠 Strategy pattern used for flexible transfer methods
- 🧾 Transaction logging and fee breakdown
- 📬 Notification system for updates
- 📃 Statement request (based on days or months)
- 🔌 Swagger/OpenAPI documentation

---

## 🧠 Design Principles & Patterns

- **Strategy Pattern**: Used to support dynamic selection of transfer methods like IMPS, NEFT, RTGS.
- **Open/Closed Principle (SOLID)**: Adding new transfer types without modifying existing logic.
- **Observer Pattern**: For sending notifications post-transfer (email, SMS, etc.).

---

## 📚 API Documentation

The APIs are documented using **Swagger UI** and follow **OpenAPI Specification**.

🔗 **[Swagger UI - Click Here](http://localhost:8081/swagger-ui/index.html#)**  
Or open in browser: `http://localhost:8081/swagger-ui/index.html#`

---

### 🧱 Entity Design

1. **external_accounts:** Stores details of beneficiaries from other banks.
   
```SQL
    CREATE TABLE `external_accounts` (
   `external_account_id` bigint NOT NULL,
   `account_holder_name` varchar(255) DEFAULT NULL,
   `created_at` datetime(6) DEFAULT NULL,
   `ifsc_code` varchar(255) DEFAULT NULL,
   `transaction_id` varchar(255) NOT NULL,
   PRIMARY KEY (`external_account_id`),
   UNIQUE KEY `UKlmvy8acfh4kb71jf3v2vvfo5y` (`transaction_id`),
   CONSTRAINT `FKpba8joqs4v0wqx1cc1a16riks`
   FOREIGN KEY (`transaction_id`) REFERENCES `transactions` (`transaction_id`)
   ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
2. **transactions:** Stores transaction details.

```SQL
    CREATE TABLE `transactions` (
  `transaction_id` varchar(255) NOT NULL,
  `from_account_id` bigint DEFAULT NULL,
  `to_account_id` bigint DEFAULT NULL,
  `transaction_date` timestamp DEFAULT CURRENT_TIMESTAMP,
  `transaction_type` tinyint DEFAULT NULL,
  `transaction_status` tinyint DEFAULT NULL,
  `updated_at` timestamp DEFAULT CURRENT_TIMESTAMP,
  `transaction_amount` decimal(38,2) DEFAULT NULL,
  `transaction_fee` decimal(38,2) DEFAULT NULL,
  `total_amount` decimal(38,2) DEFAULT NULL,
  `is_same_bank` bit(1) NOT NULL,
  `transaction_direction` tinyint DEFAULT NULL,
  PRIMARY KEY (`transaction_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;   
```
3. **transaction_type:** Admin-managed table for configuring charges and validity of transfer types.

```SQL
CREATE TABLE `transaction_type` (
  `transaction_type_id` int NOT NULL AUTO_INCREMENT,
  `transaction_type` enum('NEFT','IMPS','RTGS') NOT NULL,
  `charges` bigint NOT NULL,
  `effective_from` timestamp DEFAULT CURRENT_TIMESTAMP,
  `effective_till` timestamp DEFAULT NULL,
  PRIMARY KEY (`transaction_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
```
4. **account_balance:** Stores account balance details

```SQL
CREATE TABLE `account_balance` (
  `account_id` bigint NOT NULL,
  `balance` decimal(38,2) DEFAULT NULL,
  `updated_at` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci
```
---
## 📌 Endpoints

### 1. `GET /balance/{accountId}`
**Description:** Get the current balance of an account.  
**Response:** JSON with balance, currency, and timestamp.

---

### 2. `POST /balance/transfer`
**Description:** Transfer amount from one account to another based on selected method.

---

### 🧾 Additional Functional Features
✅ Notification System
Implemented using the Observer Design Pattern
Sends alerts upon successful/failed transactions (SMS, Email)

📃 Statement Requests
API for generating account statements based on:
Days (last 7/30 days)
Specific months
Custom date range (future scope)

---

### 🔗 API Communication
To enable seamless and declarative inter-service communication, this project uses OpenFeign, a REST client developed by Netflix and now part of the Spring Cloud ecosystem. It allows microservices to communicate with each other in a clean, interface-driven way without manually handling HTTP requests.

### 🛠 Why OpenFeign?
- Declarative Syntax: Define HTTP clients using Java interfaces and annotations.
- Spring Cloud Integration: Works out of the box with Spring Boot and Spring Cloud for service discovery (Eureka, Consul).
- Simplified Codebase: Eliminates boilerplate RestTemplate or WebClient code.

Built-in Load Balancing: Works with Ribbon or Spring Cloud LoadBalancer to distribute calls across instances.
```Snippet Java
@FeignClient("accounts")
public interface AccountFeignClients {

    @GetMapping("/api/requests/accounts/{id}")
    public ResponseEntity<AccountResponseDto> getAccountDetails(@PathVariable("id") Long accountId);
}
```
---

### 🚧 Future Enhancements
Feature	Description
🔐 Security	Integrate Spring Security, OAuth2, JWT

📈 Logging	Add centralized structured logging with ELK or Loki

🐳 Docker	Containerize the microservice for environment portability

☸️ Kubernetes	Enable deployment on Kubernetes with readiness/liveness probes

🧪 Testing	Implement unit and integration tests for robustness
