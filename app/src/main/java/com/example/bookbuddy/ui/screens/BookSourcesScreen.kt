package com.example.bookbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import com.example.bookbuddy.data.Source
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.theme.Gray
import com.example.bookbuddy.ui.theme.Purple500
import com.example.bookbuddy.ui.theme.White

@Composable
fun BookSourcesScreen(onBackClick: () -> Unit) {
    val sources = listOf(
        Source(
            "Project Gutenberg",
            "https://www.gutenberg.org",
            "Over 75,000 free public domain eBooks in EPUB, Kindle, and PDF formats."
        ),
        Source(
            "Open Library",
            "https://openlibrary.org",
            "Access to over 3 million free eBooks with borrowing options."
        ),
        Source(
            "ManyBooks",
            "https://manybooks.net",
            "Over 50,000 free eBooks in various genres, requires a free account."
        ),
        Source(
            "Internet Archive",
            "https://archive.org",
            "Millions of free books, including rare editions, in PDF and EPUB."
        ),
        Source(
            "Feedbooks",
            "https://www.feedbooks.com",
            "Thousands of public domain eBooks in multiple languages."
        )
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Download Sources",
            style = MaterialTheme.typography.headlineLarge,
            color = Purple500,
            modifier = Modifier.padding(top = 16.dp)
        )
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            items(sources) { source ->
                SourceItem(source = source)
            }
        }
        Button(
            onClick = onBackClick,
            colors = ButtonDefaults.buttonColors(containerColor = Purple500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Back",
                color = White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}

@Composable
private fun SourceItem(source: Source) {
    val uriHandler = LocalUriHandler.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Black)
            .clickable { uriHandler.openUri(source.url) }
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = source.name,
                style = MaterialTheme.typography.titleMedium,
                color = White
            )
            Text(
                text = source.url,
                style = MaterialTheme.typography.bodySmall,
                color = Purple500,
                textDecoration = TextDecoration.Underline,
                modifier = Modifier.padding(top = 8.dp)
            )
            Text(
                text = source.description,
                style = MaterialTheme.typography.bodySmall,
                color = Gray,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}