# Java Spring Microservices Project

This is a Java/Spring Boot microservices project that includes multiple services communicating via gRPC and Kafka. It is based on course material by Chris Blakely.

---

## 🧩 Microservices Overview

- **Patient Service**
    - Manages patient data
    - Communicates with Billing Service via gRPC
    - Publishes Kafka events

- **Billing Service**
    - Handles billing logic
    - Exposes gRPC endpoints

- **Notification Service**
    - Listens to Kafka topics
    - Sends out notifications

- **Auth Service**
    - Handles authentication and authorization
    - Issues JWT tokens

---

## ⚙️ Common Tech Stack

- Java 17
- Spring Boot
- gRPC
- Kafka
- PostgreSQL
- Maven
- Protobuf

---

## 🔧 Setup Instructions

### 1. Clone the repo and start required containers
Use Docker Compose to run PostgreSQL and Kafka:
```bash
docker-compose up -d
