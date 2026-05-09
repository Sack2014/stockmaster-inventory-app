# StockMaster Inventory App

StockMaster is a Full Stack inventory management application designed to help small businesses manage products, categories, stock entries, stock exits, low-stock alerts and dashboard indicators.

## Main Features

- Product CRUD
- Category CRUD
- Product search by name or code
- Product filter by category
- Stock entry registration
- Stock exit registration
- Validation to avoid negative stock
- Low-stock product alerts
- Dashboard with inventory indicators
- REST API connected to Angular frontend

## Technologies

### Backend

- Java 17
- Spring Boot 3
- Spring Web
- Spring Data JPA
- Bean Validation
- H2 Database
- PostgreSQL driver included
- Maven

### Frontend

- Angular
- TypeScript
- Bootstrap
- Reactive Forms
- Angular Router
- HttpClient

## Project Structure

```txt
stockmaster-inventory-app/
│
├── backend/
│   └── stockmaster-api/
│
├── frontend/
│   └── stockmaster-web/
│
├── screenshots/
└── README.md
```

## Backend Endpoints

### Categories

```http
GET    /api/categories
GET    /api/categories/{id}
POST   /api/categories
PUT    /api/categories/{id}
DELETE /api/categories/{id}
```

### Products

```http
GET    /api/products
GET    /api/products?search=keyboard
GET    /api/products?categoryId=1
GET    /api/products/low-stock
GET    /api/products/{id}
POST   /api/products
PUT    /api/products/{id}
DELETE /api/products/{id}
```

### Inventory

```http
GET    /api/inventory/movements
GET    /api/inventory/movements/latest
POST   /api/inventory/entry
POST   /api/inventory/exit
```

### Dashboard

```http
GET    /api/dashboard/summary
```

## How to Run the Backend

```bash
cd backend/stockmaster-api
./mvnw spring-boot:run
```

On Windows PowerShell:

```powershell
cd backend/stockmaster-api
mvn spring-boot:run
```

The backend runs on:

```txt
http://localhost:8080
```

H2 Console:

```txt
http://localhost:8080/h2-console
```

H2 configuration:

```txt
JDBC URL: jdbc:h2:mem:stockmasterdb
Username: sa
Password: empty
```

## How to Run the Frontend

```bash
cd frontend/stockmaster-web
npm install
npm start
```

The frontend runs on:

```txt
http://localhost:4200
```

## Sample JSON Requests

### Create Category

```json
{
  "name": "Electronics",
  "description": "Electronic products and accessories"
}
```

### Create Product

```json
{
  "name": "Mechanical Keyboard",
  "code": "ELEC-001",
  "description": "USB mechanical keyboard",
  "price": 185000,
  "currentStock": 8,
  "minimumStock": 5,
  "categoryId": 1
}
```

### Register Stock Entry

```json
{
  "productId": 1,
  "quantity": 10,
  "observation": "Initial supplier purchase"
}
```

### Register Stock Exit

```json
{
  "productId": 1,
  "quantity": 2,
  "observation": "Customer sale"
}
```

## Portfolio Description

StockMaster Inventory App is a Full Stack application built with Spring Boot and Angular. It allows users to manage products, categories and inventory movements, while providing dashboard indicators and low-stock alerts. The project demonstrates REST API development, database persistence, validation, business rules and frontend integration.

## Author

Faber Palacio
