package com.example.homework02.ui

import com.example.homework02.model.Book

data class LibraryUiState(
    val books: List<Book> = emptyList(),
    val searchQuery: String = "",
    val selectedGenre: String = "All"
) {
    val displayedBooks: List<Book>
        get() = books.filter { book ->
            val matchesSearch =
                book.title.contains(searchQuery, ignoreCase = true) ||
                        book.author.contains(searchQuery, ignoreCase = true)

            val matchesGenre =
                selectedGenre == "All" || book.genre == selectedGenre

            matchesSearch && matchesGenre
        }

    val genres: List<String>
        get() = listOf("All") + books
            .map { book -> book.genre }
            .distinct()
            .sorted()
}
