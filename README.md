# Employee Management System (Spring Boot)

## 🔹 Features
- REST APIs for employee CRUD operations
- JWT-based authentication & authorization
- DTO pattern for response optimization
- Hierarchical mapping (Country → State → District → City)

## 🔔 Leave Approval Notification

- Implemented notification system triggered on leave approval
- Sends notification to employee when manager approves request
- Designed using service-layer event handling
- Supports email-based notification (Spring Mail)

### Flow
Manager Approves Leave → Service Layer → Notification Trigger → User Notified

## 🔹 Tech Stack
- Java, Spring Boot, Hibernate (JPA)
- MySQL
- Spring Security, JWT

## 🔹 API Endpoints
- POST /employees
- GET /employees/{id}
- GET /locations/hierarchy

## 🔹 How to Run
1. Clone the repo
2. Run Spring Boot application
3. Test APIs using Postman
