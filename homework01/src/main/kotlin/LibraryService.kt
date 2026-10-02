package org.example
import kotlinx.coroutines.delay

class LibraryService(
    private val books: MutableList<Book>
) {

    suspend fun loadCatalog() {
        println("Loading catalog...")
        delay(1000)
        println("Catalog loaded")
    }

    fun getAllBooks(): List<Book> {
        return books
    }

    fun borrowBook(id: Int): BorrowResult {
        val book = books.find { it.id == id }
            ?: return BorrowResult.NotFound(id)

        if (book.isBorrowed) {
            return BorrowResult.AlreadyBorrowed(book)
        }

        book.isBorrowed = true

        return BorrowResult.Success(book)
    }

    fun findBooks(
        condition: (Book) -> Boolean
    ): List<Book> {
        return books.filter(condition)
    }

    fun getTitles(): List<String> {
        return books.map { book ->
            book.title
        }
    }

    fun getUniqueAuthors(): Set<String> {
        return books
            .map { book -> book.author }
            .toSet()
    }

    fun getBooksById(): Map<Int, Book> {
        return books.associateBy { book -> book.id }
    }

    fun getTotalPages(): Int {
        return books
            .map { book -> book.pages }
            .reduce { total, pages -> total + pages }
    }
}
