package com.example.bookbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun HomeScreen(
    onMenuClick: () -> Unit,
    onBookClick: (Book) -> Unit,
    onAddBookClick: () -> Unit
) {
    val books = remember {
        listOf(
            Book(1, "Sample Book", "Author Name", "2020", "A sample book description.")
        )
    }
    var searchQuery by remember { mutableStateOf("") }
    var showSearchBar by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_menu),
                contentDescription = "Menu",
                tint = Purple500,
                modifier = Modifier
                    .size(48.dp)
                    .clickable { onMenuClick() }
                    .padding(8.dp)
            )
            Text(
                text = "BookBuddy",
                style = MaterialTheme.typography.headlineLarge,
                color = Purple500,
                modifier = Modifier.weight(1f)
            )
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_search),
                contentDescription = "Search",
                tint = Purple500,
                modifier = Modifier
                    .size(48.dp)
                    .clickable { showSearchBar = !showSearchBar }
                    .padding(8.dp)
            )
        }
        if (showSearchBar) {
            OutlinedTextField(
                value = searchQuery,
                onValueChange = { searchQuery = it },
                label = { Text("Search your library...", color = Gray) },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                shape = RoundedCornerShape(12.dp),
                colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Purple500,
                    unfocusedBorderColor = Purple500,
                    focusedTextColor = White,
                    unfocusedTextColor = White
                )
            )
        }
        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(top = 16.dp)
        ) {
            items(books.filter { it.title.contains(searchQuery, ignoreCase = true) }) { book ->
                BookItem(book = book, onClick = { onBookClick(book) })
            }
        }
        FloatingActionButton(
            onClick = onAddBookClick,
            containerColor = Purple500,
            modifier = Modifier
                .align(Alignment.End)
                .padding(16.dp)
        ) {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_add),
                contentDescription = "Add Book",
                tint = White
            )
        }
    }
}

@Composable
private fun BookItem(book: Book, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(Black)
            .clickable(onClick = onClick)
            .padding(16.dp)
    ) {
        Row {
            Icon(
                imageVector = ImageVector.vectorResource(R.drawable.ic_book_placeholder),
                contentDescription = "Book Cover",
                tint = Gray,
                modifier = Modifier.size(80.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 16.dp)
                    .weight(1f)
            ) {
                Text(
                    text = book.title,
                    style = MaterialTheme.typography.titleMedium,
                    color = White
                )
                Text(
                    text = book.author,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
                Text(
                    text = book.year,
                    style = MaterialTheme.typography.bodySmall,
                    color = Gray,
                    modifier = Modifier.padding(top = 4.dp)
                )
            }
        }
    }
}