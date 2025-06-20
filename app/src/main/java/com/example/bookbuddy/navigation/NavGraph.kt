package com.example.bookbuddy.navigation

sealed class Screen(val route: String) {
    data object Login : Screen("login")
    data object Home : Screen("home")
    data object AddBook : Screen("add_book")
    data object BookDetail : Screen("book_detail/{bookId}")
    data object BookSources : Screen("book_sources")
}