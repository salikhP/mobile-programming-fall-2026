package com.example.homework02.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun AddBookDialog(
    onDismiss: () -> Unit,
    onAddBook: (
        title: String,
        author: String,
        pages: String,
        genre: String
    ) -> Boolean
) {
    var title by rememberSaveable {
        mutableStateOf("")
    }

    var author by rememberSaveable {
        mutableStateOf("")
    }

    var pages by rememberSaveable {
        mutableStateOf("")
    }

    var genre by rememberSaveable {
        mutableStateOf("")
    }

    var showValidationError by remember {
        mutableStateOf(false)
    }

    AlertDialog(
        onDismissRequest = onDismiss,
        title = {
            Text("Add a new book")
        },
        text = {
            Column(
                modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                OutlinedTextField(
                    value = title,
                    onValueChange = {
                        title = it
                    },
                    label = {
                        Text("Title")
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = author,
                    onValueChange = {
                        author = it
                    },
                    label = {
                        Text("Author")
                    },
                    singleLine = true
                )

                OutlinedTextField(
                    value = pages,
                    onValueChange = { newValue ->
                        pages = newValue.filter { character ->
                            character.isDigit()
                        }
                    },
                    label = {
                        Text("Number of pages")
                    },
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Number
                    ),
                    singleLine = true
                )


                OutlinedTextField(
                    value = genre,
                    onValueChange = {
                        genre = it
                    },
                    label = {
                        Text("Genre")
                    },
                    singleLine = true
                )

                if (showValidationError) {
                    Text(
                        text = "Complete all fields and enter a positive page count.",
                        color = MaterialTheme.colorScheme.error,
                        style = MaterialTheme.typography.bodySmall
                    )
                }
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val wasAdded = onAddBook(
                        title,
                        author,
                        pages,
                        genre
                    )

                    if (wasAdded) {
                        onDismiss()
                    } else {
                        showValidationError = true
                    }
                }
            ) {
                Text("Add")
            }
        },
        dismissButton = {
            TextButton(
                onClick = onDismiss
            ) {
                Text("Cancel")
            }
        }
    )
}