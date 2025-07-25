# Spring Boot JWT Authentication API

This project is a **secure user authentication system** built with **Spring Boot 3**, **Spring Security**, and **JWT (JSON Web Tokens)**. It allows users to register, login, and access protected resources based on roles such as `USER` and `ADMIN`.

---

## 🔐 Features

- User registration with role-based assignment (`USER`, `ADMIN`, etc.)
- Secure login with JWT token generation
- JWT-based stateless authentication
- Password encryption using `BCryptPasswordEncoder`
- Role-based access control using `@PreAuthorize`
- Custom `UserDetailsService` to load users from the database
- Global exception handling
- Token validation in `JwtAuthFilter`
- Simple REST endpoints with authentication protection

---

## 📦 Tech Stack

- Java 17+
- Spring Boot 3
- Spring Security
- JWT (via `jjwt`)
- H2 / MySQL / PostgreSQL (your choice)
- Maven
- Lombok
- JPA & Hibernate

---


---

### `##` API Endpoints

```md
## API Endpoints

| Method | Endpoint         | Description              | Protected |
|--------|------------------|--------------------------|-----------|
| POST   | /api/auth/register | Register new user       | ❌        |
| POST   | /api/auth/login    | Authenticate and get JWT| ❌        |
| GET    | /api/user          | Get user dashboard      | ✅ USER   |
| GET    | /api/admin         | Get admin dashboard     | ✅ ADMIN  |
```

#### 🔄 Sample Register Request

```json
POST /auth/register
{
  "username": "john",
  "password": "pass123",
  "roles": ["USER"]
}
```

## Environment Configuration

In `application.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/dbname
spring.datasource.username=root
spring.datasource.password=yourpassword

jwt.secret=your-secret-key
jwt.expiration=86400000
```

## 📁 Project Structure

```
src/main/java/com/devkaran/user_authentication
├── AppUser.java // Entity for user
├── AppUserRepo.java // JPA repository
├── AuthCon.java // Controller for /auth/login and /auth/register
├── JwtService.java // JWT generation and parsing
├── JwtAuthFilter.java // Intercepts requests to validate JWT
├── RegistrationDto.java // DTO for registration
├── LoginDto.java // DTO for login
├── SecurityConfig.java // Spring Security setup
├── UserCon.java // Controller for protected endpoints
├── CustomUserDetailsService.java // Loads user from DB
├── GlobalExceptionHandler.java // Handles errors
```

---

## 🛠️ How to Run

### Clone the Repository

```
git clone https://github.com/YOUR_USERNAME/springboot-jwt-auth.git
cd springboot-jwt-auth
```

###Build and Run
Make sure you have Java 17+ and Maven installed.

```
./mvnw spring-boot:run
```

API will start at:
```
http://localhost:8080
```

## License

This project is licensed under the MIT License.
