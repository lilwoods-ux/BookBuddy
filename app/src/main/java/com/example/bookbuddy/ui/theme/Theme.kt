package com.example.bookbuddy.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable

private val DarkColorScheme = darkColorScheme(
    primary = Purple500,
    onPrimary = White,
    background = Black,
    onBackground = White,
    surface = Black,
    onSurface = White,
    secondary = Gray,
    onSecondary = Black
)

@Composable
fun BookBuddyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colorScheme = DarkColorScheme,
        typography = MaterialTheme.typography,
        content = content
    )
}