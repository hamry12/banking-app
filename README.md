# Spring Cloud Config Server

## Overview

The **Spring Cloud Config Server** is a central place to manage external properties for applications across all environments. With it, you can manage application configurations from a Git repository (or other backends) and serve them to client applications (microservices) at runtime.

In a microservice architecture, maintaining consistent configuration across multiple services can become cumbersome. Spring Cloud Config Server solves this by allowing you to manage all configurations in one centralized repository.

## Why Spring Cloud Config Server?

- **Centralized Configuration**: Manage all your microservice configurations in a single place (like a Git repo).
- **Dynamic Updates**: Supports refreshing properties at runtime without restarting the services (with Spring Cloud Bus or manually).
- **Environment-Specific Configs**: Easily maintain profiles like `dev`, `qa`, `prod` using `application-dev.yml`, etc.
- **Version Control**: Use Git to version control your configuration changes.
- **Secure and Scalable**: Works well in distributed systems and supports encryption/decryption of sensitive data.

## Project Structure

This config server is set up to serve configuration properties for two microservices:

1. **Account Microservice**
2. **Transaction Microservice**

we can configure the server to manage the data either from github, file system or database.

## Setup at client side
### application.yml
```yml
spring:
  application:
    name: accounts # or transaction
  cloud:
    config:
      uri: http://localhost:8071
```

### pom.xml
```pom.xml
<properties>
    <java.version>17</java.version>
    <spring-cloud.version>2024.0.1</spring-cloud.version>
</properties>

<dependency>
    <groupId>org.springframework.cloud</groupId>
    <artifactId>spring-cloud-starter-config</artifactId>
</dependency>

<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-dependencies</artifactId>
            <version>${spring-cloud.version}</version>
            <type>pom</type>
            <scope>import</scope>
        </dependency>
    </dependencies>
</dependencyManagement>
```