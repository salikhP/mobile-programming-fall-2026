# Homework 01 - Library Console Application

A small Kotlin/JVM console application that simulates a simple library system.

The application allows users to:
- View all library items
- Borrow books
- Filter programming books
- View basic library statistics

## How to Run

From the `homework01` directory:

```bash
./gradlew --console=plain run
```

To build the project:

```bash
./gradlew clean build
```

## Main Requirements

### Variables, Data Types, Conditions, and Loops

Used in `Main.kt`:
- `val` and `var`
- `if`
- `when`
- `while`
- `for`

### List, Set, and Map

Used in `LibraryService.kt`:
- `List<Book>`
- `Set<String>` for unique authors
- `Map<Int, Book>` for books indexed by ID

### Collection Operations

Used in `LibraryService.kt`:
- `map`
- `filter`
- `reduce`

### Functions, Higher-Order Functions, and Lambdas

`findBooks()` accepts a function as a parameter:

```kotlin
fun findBooks(
    condition: (Book) -> Boolean
): List<Book>
```

It is called using a lambda:

```kotlin
library.findBooks { book ->
    book.genre == "Programming"
}
```

### Classes and Objects

The project contains classes such as:
- `LibraryItem`
- `Book`
- `Magazine`
- `LibraryService`

### Inheritance

`Book` and `Magazine` inherit from `LibraryItem`.

### Interfaces and Polymorphism

`LibraryItem` implements the `Describable` interface.

`Book` and `Magazine` override `description()` with their own implementations.

Both types are stored as `LibraryItem` objects and accessed through the same `description()` method.

### Data Class

`Book` is implemented as a Kotlin `data class`.

### Sealed Class

`BorrowResult` is a sealed class representing possible borrowing results:
- `Success`
- `NotFound`
- `AlreadyBorrowed`

### Suspend Function and Coroutine

`LibraryService.loadCatalog()` is a `suspend` function.

The application uses Kotlin coroutines with:
- `runBlocking`
- `launch`
- `delay`
- `join`
