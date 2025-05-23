# 📬 Notification Service – Kafka Event Bridge

## 📌 Introduction
Before diving into the Notification Service, it's essential to understand Apache Kafka, the underlying technology powering our asynchronous communication between microservices.

Kafka is an open-source distributed event streaming platform used to build real-time data pipelines and streaming applications. It can handle large volumes of messages in a distributed, fault-tolerant manner.

### 🧠 Kafka Core Concepts
#### 1. Producer
   Producers are responsible for sending records (messages) to Kafka topics.
In our system, services like Account Service, Transaction Service, and others will act as producers.
They publish events to dedicated Kafka topics, such as:
- account_created_topic
- balance_transfer_topic

#### 2. Topics
   A topic is a category or feed name to which records are sent.
Each microservice will publish to its unique topic when an event occurs.
Example: account_created_topic for a new account creation.

#### 3. Kafka Brokers
   Kafka brokers are servers that store data and serve clients.
A Kafka cluster consists of one or more brokers.
Each broker handles read/write operations for the partitions assigned to it.

#### 4. Partitions
   Topics are split into partitions to allow parallel processing.
Kafka guarantees message order within a partition, not across the entire topic.
Partitions allow high throughput and scalability.

#### 5. Consumers read data from Kafka topics.
Our Notification Service is the main consumer of all event topics.
It consumes events and decides the channel (SMS, EMAIL, PUSH) to notify the user.


### Producer Microservices: Account, Transaction, etc.
### Notification Service: Acts as both consumer and producer.

- Consumes topics from other microservices.
- Based on channelList, produces messages to appropriate downstream services like EMAIL, SMS, or PUSH.

### 📨 Expected Request Format
```json
{
  "eventType": "account-created-topic",
  "channelList": ["EMAIL"],
  "eventDetails": {
    "mobile": "9876554320",
    "accountId": 388714407840,
    "email": "himanshu.singh@example.com",
    "createdAt": 1747928834387
  }
}
```

### Fields:
- eventType: Name of the Kafka topic/event source.

- channelList: Enum values (EMAIL, SMS, PUSH) determining notification routes.

- eventDetails: Dynamic object holding relevant event data for downstream services.

### 🧾 Enum: ChannelType
```java
public enum ChannelType {
    EMAIL, SMS, PUSH
}
```

### 🛠️ Responsibilities of Notification Service
1. Subscribe to specific Kafka topics.

2. Parse event messages and extract routing logic.
3. Forward messages to appropriate services:
   - EMAIL Service via email-topic
   - SMS Service via sms-topic
   - PUSH Notification Service via push-topic
4. Acts as a bridge and ensures decoupling between producers and channel-specific consumers.


### 📚 Future Enhancements
- Add Dead Letter Queue (DLQ) support for failed events.
- Enable retry policies for failed deliveries.
- Introduce logging and monitoring for event lifecycle.
- Secure and validate incoming Kafka messages.