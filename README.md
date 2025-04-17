# 🃏 Cards Microservice

The **Cards Microservice** is a Spring Boot-based service designed to manage credit and debit card operations. It supports the addition of new cards by administrators and allows customers to apply for cards against their accounts.

---

## 📌 Core Responsibilities

- **Admin Role**:
    - Add new credit/debit cards.
    - Support multiple network types: `RUPAY`, `VISA`, `MASTERCARD`.

- **Customer Role**:
    - Apply for credit/debit cards against their bank accounts.

---

## 💡 Design Patterns Used

### 🧩 Strategy Pattern — *Card Network Handling*

This pattern is used to abstract the behavior for various card networks such as:

- **RUPAY**
- **VISA**
- **MASTERCARD**

Each strategy implementation returns:
- `issuanceCharge`
- `cardType` (credit/debit)

> This design promotes **open/closed principle**: new card networks can be added without modifying existing logic.

---

## 🚀 Technology Stack & Features

| Feature                     | Description                                                  |
|----------------------------|--------------------------------------------------------------|
| ✅ Spring Boot             | Base framework for developing microservice                   |
| ✅ Spring Validation       | Ensures request input integrity                              |
| ✅ Global Exception Handler| Centralized error response and management                    |
| ✅ Lombok                  | Reduces boilerplate code via annotations                     |
| ✅ External Config Server  | Centralized configuration management                         |

---

## 🛠️ Project Structure

Will be added soon

---


---

## 📦 APIs

| Endpoint                    | Method | Description                        | Access       |
|-----------------------------|--------|------------------------------------|--------------|
| `/api/requests/cards/`      | POST   | Add a new card (credit/debit)     | Admin Only   |
| `/api/requests/cards/apply` | POST   | Apply card against account         | Customer     |

---

## 📚 Future Enhancements

| Feature                       | Description                                                                 |
|------------------------------|-----------------------------------------------------------------------------|
| 🔐 Security Implementation   | Add role-based authentication and authorization                            |
| 📣 Notification Service      | Use **Kafka** for event-driven notifications (Observer Pattern)             |

---

## ⚙️ Prerequisites

- Java 17+
- Maven
- Spring Cloud Config Server
- Kafka (upcoming)
- Configured database (MySQL/Postgres/etc.)

---

## 🧪 Testing

- Unit and integration tests to be added.
- Swagger/OpenAPI integration is recommended for testing and documentation.

---

## 📫 Contact

For any issues or contributions, please raise an issue or submit a pull request.

---

## 📄 License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

