# EE Lecture 4 - Exercises
Reactive Spring Boot API for creating and retrieving messages.

Built as part of the Java Enterprise & Eclipse course to explore reactive programming concepts with WebFlux and Project Reactor.

For detailed API documentation, see the Wiki:
https://github.com/lisaupperud/EE_lecture_4_exercises/wiki/Mesage-API

##  Project Overview
This API allows users to:
- Create new messages and store them in PostgreSQL
- Retrieve messages by ID
- Validate input with `@Valid` and handle errors using a global exception handler

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


## Screenshots: Postman, Logs & PostgreSQL Database Table

#### GET `200 OK`:

<img width="1448" height="802" alt="get_message" src="https://github.com/user-attachments/assets/d8d2a2da-9f14-4264-a094-e666a5c2da39" />

#### Log

2025-10-15T20:35:43.144+02:00  INFO 61548 --- \[EE\_4\_exercises] \[actor-tcp-nio-1] c.l.E.m.service.MessageServiceImpl       : Message found: Message\[id=1, message=Hello World!, createdAt=2025-10-15T18:10:35.673631]

#### GET `404 NOT FOUND`:

<img width="1451" height="906" alt="get_404" src="https://github.com/user-attachments/assets/103f8bc8-4fdc-4ec8-97a0-80aa9e33bf06" />

#### Log
2025-10-15T20:36:10.280+02:00  WARN 61548 --- \[EE\_4\_exercises] \[ctor-tcp-nio-10] c.l.E.m.handler.GlobalExceptionHandler   : Message with Id 100 not found

#### POST `201 CREATED`:

<img width="1383" height="795" alt="post_message" src="https://github.com/user-attachments/assets/d53e7486-ad25-4956-80f5-e08e43133beb" />

#### Log

2025-10-15T20:56:18.793+02:00  INFO 62236 --- \[EE\_4\_exercises] \[ctor-http-nio-3] c.l.E.m.service.MessageServiceImpl       : New Message has been created \& saved

#### POST `400 BAD REQUEST`:

<img width="1449" height="990" alt="post_invalid" src="https://github.com/user-attachments/assets/a3df38ef-e5c0-40bc-bc79-bafc15b5b65f" />

#### Log

2025-10-15T21:00:41.961+02:00  WARN 45840 --- \[EE\_4\_exercises] \[ctor-http-nio-3] c.l.E.m.handler.GlobalExceptionHandler   : Validation failed 2 -> errors \[message: Message can't be blank or contain whitespaces, message: Check the message length!]

### PostGreSQL - Message:

<img width="1267" height="800" alt="postgres_message" src="https://github.com/user-attachments/assets/7300efc3-e3c2-48db-ac47-c20cb869c4b0" />


