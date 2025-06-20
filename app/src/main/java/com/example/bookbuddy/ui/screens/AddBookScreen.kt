package com.example.bookbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.theme.Gray
import com.example.bookbuddy.ui.theme.Purple500
import com.example.bookbuddy.ui.theme.White

@Composable
fun AddBookScreen(onBackClick: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var author by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp)
    ) {
        Text(
            text = "Add New Book",
            style = MaterialTheme.typography.headlineLarge,
            color = Purple500,
            modifier = Modifier.padding(top = 16.dp)
        )
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Book Title", color = Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )
        OutlinedTextField(
            value = author,
            onValueChange = { author = it },
            label = { Text("Author Name", color = Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )
        OutlinedTextField(
            value = year,
            onValueChange = { year = it },
            label = { Text("Publication Year", color = Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White
            ),
            keyboardOptions = androidx.compose.foundation.text.KeyboardOptions(
                keyboardType = androidx.compose.ui.text.input.KeyboardType.Number
            )
        )
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description", color = Gray) },
            modifier = Modifier
                .fillMaxWidth()
                .height(120.dp)
                .padding(top = 16.dp),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(
            onClick = {
                if (title.isNotEmpty() && author.isNotEmpty()) {
                    // Save book (e.g., to database)
                    onBackClick()
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Purple500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Save Book",
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