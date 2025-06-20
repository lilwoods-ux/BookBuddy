package com.example.bookbuddy

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.bookbuddy.auth.AuthState
import com.example.bookbuddy.auth.AuthViewModel
import com.example.bookbuddy.navigation.Screen
import com.example.bookbuddy.ui.screens.AddBookScreen
import com.example.bookbuddy.ui.screens.BookDetailScreen
import com.example.bookbuddy.ui.screens.BookSourcesScreen
import com.example.bookbuddy.ui.screens.HomeScreen
import com.example.bookbuddy.ui.screens.LoginScreen
import com.example.bookbuddy.ui.theme.BookBuddyTheme
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.components.NavigationDrawerContent
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BookBuddyTheme {
                BookBuddyApp()
            }
        }
    }
}

@Composable
fun BookBuddyApp(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    val authState by authViewModel.authState.collectAsState()

    // Determine start destination based on auth state
    val startDestination = when (authState) {
        is AuthState.Authenticated -> Screen.Home.route
        else -> Screen.Login.route
    }

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            if (authState is AuthState.Authenticated) {
                drawerState.apply {
                    NavigationDrawerContent(
                        onPremiumClick = { /* Redirect to premium subscription */ },
                        onLogoutClick = {
                            authViewModel.logout()
                            scope.launch { drawerState.close() }
                            navController.navigate(Screen.Login.route) {
                                popUpTo(navController.graph.id) { inclusive = true }
                            }
                        },
                        onCloseDrawer = { scope.launch { drawerState.close() } }
                    )
                }
            }
        },

        gesturesEnabled = authState is AuthState.Authenticated
    ) {
        Scaffold { innerPadding ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Black)
                    .padding(innerPadding)
            ) {
                NavHost(navController = navController, startDestination = startDestination) {
                    composable(Screen.Login.route) {
                        LoginScreen(
                            onLoginSuccess = {
                                navController.navigate(Screen.Home.route) {
                                    popUpTo(Screen.Login.route) { inclusive = true }
                                }
                            }
                        )
                    }
                    composable(Screen.Home.route) {
                        HomeScreen(
                            onMenuClick = { scope.launch { drawerState.open() } },
                            onBookClick = { book ->
                                navController.navigate("book_detail/${book.id}")
                            },
                            onAddBookClick = { navController.navigate(Screen.AddBook.route) }
                        )
                    }
                    composable(Screen.AddBook.route) {
                        AddBookScreen(onBackClick = { navController.popBackStack() })
                    }
                    composable(Screen.BookDetail.route) { backStackEntry ->
                        val bookId = backStackEntry.arguments?.getString("bookId")?.toIntOrNull()
                        BookDetailScreen(
                            bookId = bookId,
                            onBackClick = { navController.popBackStack() },
                            onSourcesClick = { navController.navigate(Screen.BookSources.route) }
                        )
                    }
                    composable(Screen.BookSources.route) {
                        BookSourcesScreen(onBackClick = { navController.popBackStack() })
                    }
                }
            }
        }
    }
}

