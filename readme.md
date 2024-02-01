# **Product Management API**


## **Introduction**

This Spring Boot application provides a RESTful API for managing products. It allows you to perform operations such as retrieving all products, getting filtered products, adding, updating, and deleting products.

## **Setup**
1. Clone the repository:

   `git clone https://github.com/Saurabh221B/Product-Management-Service`


2. Navigate to the project directory:


3. Build the project

   `./gradlew build`

## **Running the Application**

Use the following command to start application and you can access it at http://localhost:8080.

    './gradlew bootRun'

## Endpoints

### Get All Products

- **Method:** `GET`
- **Path:** `/products`
- **Description:** Retrieves all products.
- **Response:**
    - Status: `200 OK`
    - Body: List of products
- Example:

  ```bash
  curl -X GET http://localhost:8080/products
  ```
### Get Filtered Products

- **Method:** `GET`
- **Path:** `/product`
- **Query Parameters:**
    - `name` (optional): Filter by product name.
    - `minPrice` (optional): Minimum price filter.
    - `maxPrice` (optional): Maximum price filter.
- **Description:** Retrieves products based on specified filters.
- **Response:**
    - Status: `200 OK`
    - Body: List of filtered products
- Example:

  ```bash
  curl -X GET "http://localhost:8080/product?name=Product1&minPrice=20.0&maxPrice=50.0"
  ```

### Get Product by ID

- **Method:** `GET`
- **Path:** `/{productId}`
- **Description:** Retrieves a product by ID.
- **Response:**
    - Status: `200 OK`
    - Body: Product details
- Example:

  ```bash
  curl -X GET http://localhost:8080/1
  ```

### Add Product

- **Method:** `POST`
- **Path:** `/addproduct`
- **Request Body:** Product details (JSON format)
- **Description:** Adds a new product.
- **Response:**
    - Status: `200 OK`
    - Body: Newly added product details
- Example:

  ```bash
  curl -X POST -H "Content-Type: application/json" -d '{"productId": 1, "name": "New Product", "price": 30.0}' http://localhost:8080/addproduct
  ```

### Delete Product

- **Method:** `DELETE`
- **Path:** `/{productId}`
- **Description:** Deletes a product by ID.
- **Response:**
    - Status: `200 OK`
    - Body: Success message
- Example:

  ```bash
  curl -X DELETE http://localhost:8080/1
  ```

### Update Product

- **Method:** `PUT`
- **Path:** `/{productId}`
- **Request Body:** Updated product details (JSON format)
- **Description:** Updates an existing product by ID.
- **Response:**
    - Status: `200 OK`
    - Body: Success message
- Example:

  ```bash
  curl -X PUT -H "Content-Type: application/json" -d '{"name": "Updated Product", "price": 40.0}' http://localhost:8080/1
  ```

### Update Product Price

- **Method:** `PUT`
- **Path:** `/price-update/{productId}`
- **Query Parameters:**
    - `price`: New price for the product
- **Description:** Updates the price of an existing product by ID.
- **Response:**
    - Status: `200 OK`
    - Body: Success message
- Example:

  ```bash
  curl -X PUT "http://localhost:8080/price-update/1?price=50.0"
  ```
  ## Additional Configuration
- Please check application.properties file can be modified to customize the behavior of the application.



