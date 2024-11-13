# ShopEase - E-Commerce Server

## Overview

ShopEase is an e-commerce backend built with **Spring Boot**. It follows a microservice architecture with services for **product management**, **cart handling**, and **order processing**. The project uses **H2** for data storage and **Eureka** for service discovery.

## Key Services

- **Product Service**: Manages product details.
- **Cart Service**: Manages shopping cart functionality.
- **Order Service**: Processes orders and checks stock availability.

## Technologies

- **Spring Boot**
- **Eureka (Service Discovery)**
- **H2 In memory database (can be changed)**
- **Feign Client**

## Setup

1. Clone the repository:
    ```bash
    git clone https://github.com/Rudra-23/ShopEase-Server-Java.git
    ```

2. Configure MySQL connection in each service.

3. Run services locally:
    ```bash
    mvn spring-boot:run
    ```

### API Gateway

All client requests should go through the **API Gateway** for routing to the appropriate services.

## API Endpoints

### Product Service
- **POST /products/product/create**: Create a new product.  
  - **Request Body**: 
    ```json
    {
      "name": "Product Name",
      "description": "Product Description",
      "price": 100.00,
      "quantity": 50
    }
    ```
- **GET /products/product/{id}**: Retrieve a product by its ID.  
- **PUT /products/product/{id}/update-stock**: Update the stock quantity of a product.  
  - **Request Parameter**: `quantity` (integer)

### Cart Service
- **POST /carts/cart/create**: Create a new cart.  
- **PUT /carts/cart/{cartId}/add-item**: Add an item to the specified cart.  
  - **Request Body**:
    ```json
    {
      "productId": 1,
      "quantity": 2
    }
    ```
- **GET /carts/cart/{cartId}**: Retrieve a cart by its ID.  
- **DELETE /carts/cart/{cartId}**: Delete the specified cart.

### Address Service
- **POST /addresses**: Create a new address.  
  - **Request Body**: 
    ```json
    {
      "street": "123 Main St",
      "city": "CityName",
      "state": "StateName",
      "zip": "12345"
    }
    ```
- **GET /addresses/address/{id}**: Retrieve an address by its ID.

### Order Service
- **POST /orders/order/create**: Create a new order.  
  - **Request Body**: 
    ```json
    {
      "cartId": 1,
      "addressId": 2
    }
    ```
- **GET /orders/order/{orderId}**: Retrieve an order by its ID.

This project is still under-development. Additional endpoints will be added soon with proper error handling.

