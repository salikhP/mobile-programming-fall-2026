package com.example.homework02.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BookSearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = query,
        onValueChange = onQueryChange,
        modifier = modifier.fillMaxWidth(),
        label = {
            Text("Search books")
        },
        placeholder = {
            Text("Enter a title or author")
        },
        singleLine = true,
        trailingIcon = {
            if (query.isNotEmpty()) {
                TextButton(
                    onClick = {
                        onQueryChange("")
                    }
                ) {
                    Text("Clear")
                }
            }
        }
    )
}
