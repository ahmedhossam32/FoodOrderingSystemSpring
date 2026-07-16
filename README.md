# FoodOrderingSystem

A small Spring Boot practice project — a REST API for a single-restaurant food ordering system (Meals, Customers, Orders).

## Tech Stack

- Java 17
- Spring Boot
- Spring Data JPA
- H2 file-based database
- Lombok
- Validation (Jakarta Bean Validation)

## How to Run

```bash
./gradlew bootRun
```

On Windows:

```bash
gradlew.bat bootRun
```

The app runs on `http://localhost:8080`.

H2 console: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:file:./data/fooddb`
- User: `sa`
- Password: (blank)

## API Endpoints

### Meals

#### `POST /api/meals`
Creates a new meal.

**Request body:**
```http
{
  "name": "Margherita Pizza",
  "description": "Classic pizza with tomato, mozzarella, and basil",
  "category": "MAIN_COURSE",
  "price": 12.99
}
```

**Response:** `201 Created`
```http
{
  "id": 1,
  "name": "Margherita Pizza",
  "description": "Classic pizza with tomato, mozzarella, and basil",
  "category": "MAIN_COURSE",
  "price": 12.99
}
```

#### `GET /api/meals/{id}`
Gets a single meal by id. `{id}` — Long.

**Response:** `200 OK`
```http
{
  "id": 1,
  "name": "Margherita Pizza",
  "description": "Classic pizza with tomato, mozzarella, and basil",
  "category": "MAIN_COURSE",
  "price": 12.99
}
```

#### `GET /api/meals`
Gets all meals.

**Response:** `200 OK`
```http
[
  {
    "id": 1,
    "name": "Margherita Pizza",
    "description": "Classic pizza with tomato, mozzarella, and basil",
    "category": "MAIN_COURSE",
    "price": 12.99
  }
]
```

#### `PUT /api/meals/{id}`
Updates an existing meal. `{id}` — Long.

**Request body:**
```http
{
  "name": "Margherita Pizza (Large)",
  "description": "Classic pizza with tomato, mozzarella, and basil - large size",
  "category": "MAIN_COURSE",
  "price": 15.99
}
```

**Response:** `200 OK`
```http
{
  "id": 1,
  "name": "Margherita Pizza (Large)",
  "description": "Classic pizza with tomato, mozzarella, and basil - large size",
  "category": "MAIN_COURSE",
  "price": 15.99
}
```

#### `DELETE /api/meals/{id}`
Deletes a meal. `{id}` — Long.

**Response:** `204 No Content`

---

### Customers

#### `POST /api/customers`
Creates a new customer.

**Request body:**
```http
{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567",
  "address": "123 Main St, Springfield"
}
```

**Response:** `201 Created`
```http
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567",
  "address": "123 Main St, Springfield"
}
```

#### `GET /api/customers/{id}`
Gets a single customer by id. `{id}` — Long.

**Response:** `200 OK`
```http
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567",
  "address": "123 Main St, Springfield"
}
```

#### `GET /api/customers`
Gets all customers.

**Response:** `200 OK`
```http
[
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane.doe@example.com",
    "phoneNumber": "555-123-4567",
    "address": "123 Main St, Springfield"
  }
]
```

#### `PUT /api/customers/{id}`
Updates an existing customer. `{id}` — Long.

**Request body:**
```http
{
  "name": "Jane A. Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567",
  "address": "456 Oak Ave, Springfield"
}
```

**Response:** `200 OK`
```http
{
  "id": 1,
  "name": "Jane A. Doe",
  "email": "jane.doe@example.com",
  "phoneNumber": "555-123-4567",
  "address": "456 Oak Ave, Springfield"
}
```

#### `DELETE /api/customers/{id}`
Deletes a customer. `{id}` — Long.

**Response:** `204 No Content`

---

### Orders

#### `POST /api/orders`
Creates a new order for a customer with one or more meal items.

**Request body:**
```http
{
  "customerId": 1,
  "items": [
    {
      "mealId": 1,
      "quantity": 2
    },
    {
      "mealId": 3,
      "quantity": 1
    }
  ]
}
```

**Response:** `201 Created`
```http
{
  "id": 1,
  "customerId": 1,
  "customerName": "Jane Doe",
  "orderDate": "2026-07-16T18:32:10",
  "totalPrice": 41.97,
  "items": [
    {
      "mealId": 1,
      "mealName": "Margherita Pizza",
      "quantity": 2,
      "priceAtOrder": 12.99,
      "subtotal": 25.98
    },
    {
      "mealId": 3,
      "mealName": "Tiramisu",
      "quantity": 1,
      "priceAtOrder": 15.99,
      "subtotal": 15.99
    }
  ]
}
```

#### `GET /api/orders/{id}`
Gets a single order by id. `{id}` — Long.

**Response:** `200 OK`
```http
{
  "id": 1,
  "customerId": 1,
  "customerName": "Jane Doe",
  "orderDate": "2026-07-16T18:32:10",
  "totalPrice": 41.97,
  "items": [
    {
      "mealId": 1,
      "mealName": "Margherita Pizza",
      "quantity": 2,
      "priceAtOrder": 12.99,
      "subtotal": 25.98
    },
    {
      "mealId": 3,
      "mealName": "Tiramisu",
      "quantity": 1,
      "priceAtOrder": 15.99,
      "subtotal": 15.99
    }
  ]
}
```

#### `GET /api/orders`
Gets all orders.

**Response:** `200 OK`
```http
[
  {
    "id": 1,
    "customerId": 1,
    "customerName": "Jane Doe",
    "orderDate": "2026-07-16T18:32:10",
    "totalPrice": 41.97,
    "items": [
      {
        "mealId": 1,
        "mealName": "Margherita Pizza",
        "quantity": 2,
        "priceAtOrder": 12.99,
        "subtotal": 25.98
      }
    ]
  }
]
```

#### `DELETE /api/orders/{id}`
Deletes an order. `{id}` — Long.

**Response:** `204 No Content`

## Notes

- Orders have no PUT/update endpoint by design — orders aren't edited after being placed.
- `priceAtOrder` on order items is a snapshot of the meal's price at the time the order was placed, so later menu price changes don't affect past orders.
- Not-found errors currently return raw 500 responses; a global exception handler is planned but not yet implemented.
