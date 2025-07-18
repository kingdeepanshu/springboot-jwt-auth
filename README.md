# Spring Boot JWT Authentication with Role-Based Access Control

This project is a **Spring Boot 3 REST API** that implements **JWT Authentication** with **Role-Based Authorization** (`USER`, `ADMIN`).  
It uses **Spring Security** and **JSON Web Tokens (JWT)** to secure endpoints in a stateless way.

## Features

- ✅ JWT-based Login System  
- ✅ Role-Based API Access (`USER`, `ADMIN`)  
- ✅ In-Memory Users for Demo Purposes  
- ✅ Custom JWT Filter for Token Validation  
- ✅ Stateless Authentication (No Sessions)

## Endpoints

| Method | URL | Access | Description |
|---|---|---|---|
| `POST` | `/auth/login` | Public | Login with username/password and receive JWT token |
| `GET` | `/` | Public | Public Hello World |
| `GET` | `/auth/user` | USER / ADMIN | Accessible by both USER and ADMIN roles |
| `GET` | `/auth/admin` | ADMIN | Accessible by ADMIN role only |

## Default Users

| Username | Password | Roles |
|---|---|---|
| Dev | 123 | ADMIN, USER |
| Gauri | 123 | USER |

## Tech Stack

- **Java 17+**  
- **Spring Boot 3**  
- **Spring Security**  
- **JWT (io.jsonwebtoken - jjwt)**  
- **In-Memory UserDetailsService**

## How It Works

1. **Login:**  
   Send `POST` request to `/auth/login` with JSON body:
   ```json
   {
     "username": "Dev",
     "password": "123"
   }
