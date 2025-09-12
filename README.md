# Basic Check Java 8

A Java 8 utility library for basic parameter validation using Spring AOP and annotations.

## Overview

This library provides automatic parameter validation through AOP (Aspect-Oriented Programming) using annotations. It
supports configurable return types when validation fails, making it suitable for various use cases.

## Features

- **Annotation-based validation**: Simple annotations for common validation scenarios
- **AOP integration**: Automatic validation without boilerplate code
- **Configurable return types**: Choose between throwing exceptions, returning null, or returning empty collections
- **Spring Boot integration**: Works seamlessly with Spring Boot applications

## Dependencies

- Java 8
- Spring Boot 2.4.4 (AOP)
- Apache Commons Lang3 & Collections
- Bean Validation API
- Lombok

## Quick Start

### 1. Add the dependency to your Maven project:

```xml

<dependency>
  <groupId>com.goody.utils</groupId>
  <artifactId>basic-check-java-8</artifactId>
  <version>1.0.0</version>
</dependency>
```

### 2. Enable Spring Boot AOP in your application:

```java

@SpringBootApplication
@EnableAspectJAutoProxy
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
```

### 3. Use annotations in your service methods:

```java

@Service
public class UserService {

    @BasicCheck
    public void updateUser(@CheckLong Long userId, @CheckString String name) {
        // Method implementation
        // Validation happens automatically before execution
    }

    @BasicCheck(returnType = BasicCheck.ReturnType.EMPTY)
    public List<User> findUsers(@CheckCollection List<Long> userIds) {
        // Returns empty list if userIds is null or empty
        return userRepository.findByIds(userIds);
    }
}
```

## Available Annotations

### Method-level Annotation

- `@BasicCheck`: Enables validation for the method
    - `returnType`: Configure return behavior on validation failure
        - `EXCEPTION` (default): Throws `IllegalArgumentException`
        - `NULL`: Returns `null`
        - `EMPTY`: Returns appropriate empty collection/Optional

### Parameter-level Annotations

- `@CheckNull`: Validates parameter is not null
- `@CheckString`: Validates string is not null or blank
- `@CheckLong`: Validates Long is not null and >= 0
- `@CheckCollection`: Validates Collection is not null or empty
- `@CheckMap`: Validates Map is not null or empty
- `@CheckObject`: Validates object using Bean Validation (JSR-303)

## Usage Examples

### Basic Parameter Validation

```java

@BasicCheck
public void processData(@CheckLong Long id, @CheckString String name) {
    // Throws IllegalArgumentException if id is null/negative or name is null/blank
}
```

### Return Empty Collections

```java

@BasicCheck(returnType = BasicCheck.ReturnType.EMPTY)
public List<Item> getItems(@CheckCollection List<Long> ids) {
    // Returns Collections.emptyList() if ids is null or empty
    return itemService.findByIds(ids);
}
```

### Return Null on Validation Failure

```java

@BasicCheck(returnType = BasicCheck.ReturnType.NULL)
public void updateItem(@CheckNull Item item) {
    // Returns null (void method behavior) if item is null
    itemRepository.save(item);
}
```

### Complex Object Validation

```java

@BasicCheck
public void saveUser(@CheckObject UserDTO user) {
    // Validates user object using Bean Validation annotations
    // UserDTO should have @NotNull, @Size, etc. annotations
}
```

## Return Type Behavior

| Return Type | Method Return Type | Behavior                          |
|-------------|--------------------|-----------------------------------|
| `EXCEPTION` | Any                | Throws `IllegalArgumentException` |
| `NULL`      | Any                | Returns `null`                    |
| `EMPTY`     | `List<T>`          | Returns `Collections.emptyList()` |
| `EMPTY`     | `Set<T>`           | Returns `Collections.emptySet()`  |
| `EMPTY`     | `Map<K,V>`         | Returns `Collections.emptyMap()`  |
| `EMPTY`     | `Optional<T>`      | Returns `Optional.empty()`        |

## Building and Testing

```bash
# Compile the project
mvn clean compile

# Run tests
mvn test

# Package the jar
mvn package

# Install to local repository
mvn install
```

## License

This project is licensed under the MIT License.

## Author

- **Goody** - *Initial work* - Version 1.0, 2023/01/30
