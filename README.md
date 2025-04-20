# Spring Cloud Gateway

## Overview

This repository implements **Spring Cloud Gateway**, a powerful and flexible API Gateway solution for routing requests to various backend services, including **Accounts**, **Transactions**, and **Cards** microservices. The gateway acts as a single entry point for all incoming requests, providing features such as routing, load balancing, and security.

**Spring Cloud Gateway** will route incoming API calls to the respective backend services using **Spring Cloud Discovery Client** for service registration and discovery. This approach ensures that your application is scalable, fault-tolerant, and secure.

## What is an API Gateway?

An **API Gateway** is an architectural pattern that acts as an entry point for clients to interact with backend microservices. It is responsible for request routing, load balancing, API composition, security, and monitoring. Instead of clients calling multiple services directly, all requests are routed through a single API Gateway, which then delegates the request to the appropriate service.

### Why is an API Gateway needed?

- **Single Entry Point**: Clients can interact with a single API Gateway instead of making multiple calls to different services.
- **Routing and Load Balancing**: API Gateway can route requests to appropriate services and provide load balancing across different instances of microservices.
- **Security**: It can act as a security layer, providing centralized authentication and authorization mechanisms.
- **Monitoring and Logging**: API Gateway can aggregate logs and metrics, making monitoring and debugging easier.
- **Cross-Cutting Concerns**: It handles common functionalities like rate limiting, request validation, and retry mechanisms, reducing the burden on each microservice.

## Architecture

In this implementation, the **Spring Cloud Gateway** will route requests to the following microservices:

- **Accounts Microservice**: Manages account-related operations.
- **Transaction Microservice**: Handles transaction-related operations.
- **Cards Microservice**: Deals with card-related operations.

The Spring Cloud Gateway uses **Spring Cloud Discovery Client** for internal service discovery, allowing dynamic resolution of service instances.

## Features of Spring Cloud Gateway

- **Routing**: Routes requests to various microservices based on predefined rules (e.g., URL path, headers, etc.).
- **Load Balancing**: Supports load balancing between multiple instances of services using Ribbon.
- **Filters**: Spring Cloud Gateway provides pre and post filters to modify request and response behaviors.
- **Rate Limiting**: Allows rate limiting for APIs to protect backend services.
- **Circuit Breaker**: Implements resilience patterns using circuit breakers.
- **Security**: Supports security mechanisms like OAuth2, JWT, etc., to secure endpoints.
- **Service Discovery**: Uses Spring Cloud Discovery Client to dynamically discover service instances.

## How It Works

### Request Flow

1. **Client Request**: A client sends an HTTP request to the Spring Cloud Gateway.
2. **Routing**: Based on the request, the gateway uses routing rules to forward the request to the appropriate microservice (Accounts, Transactions, or Cards).
3. **Service Discovery**: The gateway uses the Spring Cloud Discovery Client to find the correct instance of the microservice.
4. **Response**: After receiving the response from the backend microservice, the gateway returns the response to the client.

### Microservices Interaction

- **Accounts Microservice**: Responsible for operations related to user accounts.
- **Transaction Microservice**: Handles financial transactions.
- **Cards Microservice**: Manages card operations (e.g., card issuance, details).

The gateway will route requests to these services, but the internal communication between the gateway and the services will be done via the **Spring Cloud Discovery Client**.

## Future Enhancements

1. **Securing the Gateway using OpenID Connect**:
    - The next phase of development will include securing the Spring Cloud Gateway using **OpenID Connect** (OIDC).
    - This will ensure that all incoming requests are authenticated and authorized via OpenID Connect, allowing seamless integration with identity providers like Google, Auth0, or Keycloak.
