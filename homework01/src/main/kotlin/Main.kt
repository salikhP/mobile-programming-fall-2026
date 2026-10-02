package org.example

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val books = mutableListOf(
        Book(
            id = 1,
            title = "Clean Code",
            author = "Robert Martin",
            pages = 464,
            genre = "Programming"
        ),
        Book(
            id = 2,
            title = "The Pragmatic Programmer",
            author = "David Thomas",
            pages = 352,
            genre = "Programming"
        ),
        Book(
            id = 3,
            title = "1984",
            author = "George Orwell",
            pages = 328,
            genre = "Fiction"
        ),
        Book(
            id = 4,
            title = "Animal Farm",
            author = "George Orwell",
            pages = 112,
            genre = "Fiction"
        )
    )

    val items: List<LibraryItem> = books + Magazine(
        id = 5,
        title = "Forbes",
        issueNumber = 10
    )

    val library = LibraryService(books)

    val loadJob = launch {
        library.loadCatalog()
    }

    loadJob.join()

    var running = true

    while (running) {
        println()
        println("=== Library ===")
        println("1. Show all books")
        println("2. Borrow book")
        println("3. Show programming books")
        println("4. Show statistics")
        println("0. Exit")
        print("Choose option: ")

        when (readlnOrNull()) {
            "1" -> {
                for (item in items) {
                    if (item is Book) {
                        val status = if (item.isBorrowed) {
                            "Borrowed"
                        } else {
                            "Available"
                        }

                        println("${item.description()} [$status]")
                    } else {
                        println(item.description())
                    }
                }
            }

            "2" -> {
                print("Enter book id: ")

                val id = readlnOrNull()?.toIntOrNull()

                if (id == null) {
                    println("Invalid id.")
                    continue
                }

                when (val result = library.borrowBook(id)) {
                    is BorrowResult.Success -> {
                        println("Borrowed: ${result.book.title}")
                    }

                    is BorrowResult.NotFound -> {
                        println("Book with id ${result.id} not found.")
                    }

                    is BorrowResult.AlreadyBorrowed -> {
                        println("${result.book.title} is already borrowed.")
                    }
                }
            }

            "3" -> {
                val programmingBooks = library.findBooks { book ->
                    book.genre == "Programming"
                }

                programmingBooks.forEach { book ->
                    println(book.description())
                }
            }

            "4" -> {
                println("Titles: ${library.getTitles()}")
                println("Unique authors: ${library.getUniqueAuthors()}")
                println("Books by id: ${library.getBooksById()}")
                println("Total pages: ${library.getTotalPages()}")
            }

            "0" -> {
                running = false
                println("Goodbye!")
            }

            else -> {
                println("Unknown option.")
            }
        }
    }
}
