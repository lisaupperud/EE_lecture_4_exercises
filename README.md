# EE Lecture 4 - Exercises
Reactive Spring Boot API for creating and retrieving messages.

Built as part of the Java Enterprise & Eclipse course to explore reactive programming concepts with WebFlux and Project Reactor.

## Tech Stack
- **Java 21**
- **Spring Boot 3 (Reactive WebFlux)**
- **PostgreSQL** + **Flyway** for database migrations
- **Project Reactor** (`Mono`, `Flux`)
- **SLF4J + Logback** for logging
- **Lombok** for reducing boilerplate

##  Getting Started

### Prerequisites
- Java 21
- PostgreSQL installed and running
- IDE (IntelliJ recommended)

### Setup
1. Clone the repository:
   ```bash
   git clone https://github.com/<user>/<repo>.git
   cd <repo>
   ```
   
2. Create a PostGreSQL Database:
   ```
   CREATE DATABASE <DB_NAME>;
   ```
   
3. Configure your database credentials in `application.properties`:
  ```
  spring.r2dbc.url=r2dbc:postgresql://localhost:5432/<DB_NAME>
  spring.r2dbc.username=<DB_USER>
  spring.r2dbc.password=<DB_PASSWORD>
  ```

4. Run the application:
   ```
   ./gradlew bootRun
   ```

5. Test in Postman:

  POST `/api/v1/message/create`

  GET `/api/v1/message/{id}`


##  Project Overview
This API allows users to:
- Create new messages and store them in PostgreSQL
- Retrieve messages by ID
- Validate input with `@Valid` and handle errors using a global exception handler

For detailed API documentation, see the [Wiki → Message API](../../wiki/Message-API)
