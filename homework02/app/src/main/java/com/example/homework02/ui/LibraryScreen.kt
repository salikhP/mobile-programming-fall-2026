package com.example.homework02.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.homework02.ui.components.AddBookDialog
import com.example.homework02.ui.components.BookCard
import com.example.homework02.ui.components.BookSearchField
import com.example.homework02.ui.components.GenreFilterRow

@Composable
fun LibraryScreen(
    uiState: LibraryUiState,
    onSearchQueryChange: (String) -> Unit,
    onGenreSelected: (String) -> Unit,
    onBorrowClick: (Int) -> Unit,
    onAddBook: (
        title: String,
        author: String,
        pages: String,
        genre: String
    ) -> Boolean,
    modifier: Modifier = Modifier
) {
    var showAddBookDialog by rememberSaveable {
        mutableStateOf(false)
    }

    Box(
        modifier = modifier.fillMaxSize()
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(
                start = 16.dp,
                top = 20.dp,
                end = 16.dp,
                bottom = 96.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                LibraryHeader(
                    totalBooks = uiState.books.size,
                    availableBooks = uiState.books.count { book ->
                        !book.isBorrowed
                    }
                )
            }

            item {
                BookSearchField(
                    query = uiState.searchQuery,
                    onQueryChange = onSearchQueryChange
                )
            }

            item {
                GenreFilterRow(
                    genres = uiState.genres,
                    selectedGenre = uiState.selectedGenre,
                    onGenreSelected = onGenreSelected
                )
            }

            if (uiState.displayedBooks.isEmpty()) {
                item {
                    EmptyLibraryMessage()
                }
            } else {
                items(
                    items = uiState.displayedBooks,
                    key = { book -> book.id }
                ) { book ->
                    BookCard(
                        book = book,
                        onBorrowClick = {
                            onBorrowClick(book.id)
                        }
                    )
                }
            }
        }

        ExtendedFloatingActionButton(
            onClick = {
                showAddBookDialog = true
            },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(16.dp)
        ) {
            Text("Add book")
        }
    }

    if (showAddBookDialog) {
        AddBookDialog(
            onDismiss = {
                showAddBookDialog = false
            },
            onAddBook = onAddBook
        )
    }
}

@Composable
fun LibraryHeader(
    totalBooks: Int,
    availableBooks: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = "My Library",
            style = MaterialTheme.typography.headlineLarge,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = "$availableBooks of $totalBooks books available",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}


@Composable
fun EmptyLibraryMessage(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(160.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = "No books match your search",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}
