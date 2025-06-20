package com.example.bookbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.bookbuddy.R
import com.example.bookbuddy.data.Book
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.theme.Gray
import com.example.bookbuddy.ui.theme.Purple500
import com.example.bookbuddy.ui.theme.White

@Composable
fun BookDetailScreen(
    bookId: Int?,
    onBackClick: () -> Unit,
    onSourcesClick: () -> Unit
) {
    // Mock book data for demo
    val book = Book(
        id = bookId ?: 1,
        title = "Sample Book",
        author = "Author Name",
        year = "2020",
        description = "A sample book description."
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_book_placeholder),
                contentDescription = "Book Cover",
                tint = Gray,
                modifier = Modifier.size(120.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.headlineMedium,
                    color = White
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.titleMedium,
                    color = Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Text(
                    text = book.year,
                    style = MaterialTheme.typography.bodyMedium,
                    color = Gray,
                    modifier = Modifier.padding(top = 8.dp)
                )
            }
        }
        Text(
            text = book.description,
            style = MaterialTheme.typography.bodyLarge,
            color = White,
            modifier = Modifier.padding(top = 16.dp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = onSourcesClick,
            colors = ButtonDefaults.buttonColors(containerColor = Purple500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "View Download Sources",
                color = White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
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