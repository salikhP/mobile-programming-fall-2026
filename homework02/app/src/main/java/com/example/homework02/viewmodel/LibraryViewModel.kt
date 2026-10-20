package com.example.homework02.viewmodel

import androidx.lifecycle.ViewModel
import com.example.homework02.model.Book
import com.example.homework02.ui.LibraryUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class LibraryViewModel : ViewModel() {
    private val initialBooks = listOf(
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
        ),
        Book(
            id = 5,
            title = "Sapiens",
            author = "Yuval Noah Harari",
            pages = 443,
            genre = "History",
            isBorrowed = true
        )
    )

    private val _uiState = MutableStateFlow(
        LibraryUiState(books = initialBooks)
    )

    val uiState: StateFlow<LibraryUiState> = _uiState.asStateFlow()

    fun updateSearchQuery(query: String) {
        _uiState.update { currentState -> currentState.copy(searchQuery = query) }
    }

    fun selectGenre(genre: String) {
        _uiState.update { currentState -> currentState.copy(selectedGenre = genre) }
    }

    fun toggleBorrowed(bookId: Int) {
        _uiState.update { currentState ->
            val updatedBooks = currentState.books.map { book ->
                if (book.id == bookId) {
                    book.copy(isBorrowed = !book.isBorrowed)
                } else {
                    book
                }
            }

            currentState.copy(books = updatedBooks)
        }
    }

    fun addBook(
        title: String,
        author: String,
        pagesText: String,
        genre: String
    ): Boolean {
        val pages = pagesText.toIntOrNull()

        if (
            title.isBlank() ||
            author.isBlank() ||
            genre.isBlank() ||
            pages == null ||
            pages <= 0
        ) {
            return false
        }

        _uiState.update { currentState ->
            val nextId = (currentState.books.maxOfOrNull { book -> book.id } ?: 0) + 1

            val newBook = Book(
                id = nextId,
                title = title.trim(),
                author = author.trim(),
                pages = pages,
                genre = genre.trim()
            )

            currentState.copy(
                books = currentState.books + newBook
            )
        }

        return true
    }
}
