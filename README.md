# Hello World API

A Spring Boot HTTP API that returns personalized greetings based on alphabet validation rules.

## Endpoint

`GET /hello-world?name={name}`

| Condition | HTTP Status | Response |
|-----------|-------------|----------|
| First letter is A–M or a–m | `200 OK` | `{ "message": "Hello Alice" }` |
| First letter is N–Z or n–z | `400 Bad Request` | `{ "error": "Invalid Input" }` |
| `name` is missing, empty, or blank | `400 Bad Request` | `{ "error": "Invalid Input" }` |

## Prerequisites

- Java 17+
- Maven 3.9+

## Run the application

```bash
./mvnw spring-boot:run
```

The API starts on `http://localhost:8080`.

### API documentation

Swagger UI is available at `http://localhost:8080/swagger-ui.html` when the app is running.

### Example requests

```bash
curl "http://localhost:8080/hello-world?name=alice"
# {"message":"Hello Alice"}

curl "http://localhost:8080/hello-world?name=nancy"
# {"error":"Invalid Input"}
```

## Run the tests

```bash
./mvnw test
```

Tests cover:
- Valid names in the first half of the alphabet
- Invalid names in the second half of the alphabet
- Missing, empty, and blank names
- Leading/trailing whitespace trimming
- Boundary letters (`M`/`m` valid, `N`/`n` invalid)
- Non-alphabetic first characters
- Full HTTP integration via `MockMvc`

## Project structure

```
src/main/java/com/example/helloworld/
├── HelloWorldApplication.java
├── config/OpenApiConfig.java              # Swagger / OpenAPI setup
├── controller/HelloWorldController.java   # HTTP layer
├── service/HelloWorldService.java         # validation + greeting logic
├── dto/                                   # response records
└── exception/                             # error handling
```

## Assumptions

1. **Name formatting**: The greeting capitalizes the first letter and lowercases the rest (e.g. `alice` → `Hello Alice`, `ALICE` → `Hello Alice`).
2. **Whitespace**: Leading/trailing whitespace is trimmed before validation and formatting (`" alice "` is treated as `alice`).
3. **Non-alphabetic first character**: Names starting with digits or symbols (e.g. `1bob`, `@alice`) return `400 Bad Request`.
4. **Boundary letters**: `M`/`m` are valid (first half); `N`/`n` are invalid (second half).
