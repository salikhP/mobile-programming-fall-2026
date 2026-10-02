package org.example

sealed class BorrowResult {
    data class Success(val book: Book) : BorrowResult()

    data class NotFound(val id: Int) : BorrowResult()

    data class AlreadyBorrowed(val book: Book) : BorrowResult()
}
