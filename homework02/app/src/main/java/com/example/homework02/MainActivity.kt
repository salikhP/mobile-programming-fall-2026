package com.example.homework02

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.homework02.ui.LibraryScreen
import com.example.homework02.ui.theme.LibraryAppTheme
import com.example.homework02.viewmodel.LibraryViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            LibraryAppTheme {
                val libraryViewModel: LibraryViewModel = viewModel()

                val uiState by libraryViewModel.uiState.collectAsStateWithLifecycle()

                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    LibraryScreen(
                        uiState = uiState,
                        onSearchQueryChange = libraryViewModel::updateSearchQuery,
                        onGenreSelected = libraryViewModel::selectGenre,
                        onBorrowClick = libraryViewModel::toggleBorrowed,
                        onAddBook = libraryViewModel::addBook,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}
