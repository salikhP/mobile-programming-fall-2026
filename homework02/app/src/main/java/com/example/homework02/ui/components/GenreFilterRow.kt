package com.example.homework02.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun GenreFilterRow(
    genres: List<String>,
    selectedGenre: String,
    onGenreSelected: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(
            items = genres,
            key = { genre -> genre }
        ) { genre ->
            FilterChip(
                selected = genre == selectedGenre,
                onClick = {
                    onGenreSelected(genre)
                },
                label = {
                    Text(genre)
                }
            )
        }
    }
}