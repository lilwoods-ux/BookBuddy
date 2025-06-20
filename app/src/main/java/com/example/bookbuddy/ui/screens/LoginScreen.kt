package com.example.bookbuddy.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.bookbuddy.auth.AuthState
import com.example.bookbuddy.auth.AuthViewModel
import com.example.bookbuddy.ui.theme.Black
import com.example.bookbuddy.ui.theme.Gray
import com.example.bookbuddy.ui.theme.Purple500
import com.example.bookbuddy.ui.theme.White
import kotlinx.coroutines.launch

// LoginScreen displays a form for login or signup and handles password reset
@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit,
    authViewModel: AuthViewModel = viewModel()
) {
    // State for form inputs and UI toggles
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isSignup by remember { mutableStateOf(false) }
    var showResetDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val authState by authViewModel.authState.collectAsState()

    // Handle authentication state changes (fixed lines 49–67)
    LaunchedEffect(authState) {
        when (authState) {
            is AuthState.Authenticated -> {
                onLoginSuccess() // Navigate to HomeScreen on success
            }
            is AuthState.Error -> {
                scope.launch {
                    snackbarHostState.showSnackbar((authState as AuthState.Error).message)
                    authViewModel.resetErrorState() // Clear error after showing
                }
            }
            else -> {
                // Handle other states (e.g., AuthState.Loading, AuthState.Idle) if needed
            }
        }
    }
    // Main UI layout
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Black)
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Title
        Text(
            text = if (isSignup) "Sign Up" else "Login",
            style = MaterialTheme.typography.headlineLarge,
            color = Purple500,
            modifier = Modifier.padding(top = 32.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))

        // Email field (visible with white text, purple border)
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email", color = Gray) },
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White,
                cursorColor = White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Password field
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("Password", color = Gray) },
            visualTransformation = PasswordVisualTransformation(),
            modifier = Modifier.fillMaxWidth(),
            colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Purple500,
                unfocusedBorderColor = Purple500,
                focusedTextColor = White,
                unfocusedTextColor = White,
                cursorColor = White
            )
        )
        Spacer(modifier = Modifier.height(16.dp))

        // Login/Signup button
        Button(
            onClick = {
                if (email.isNotEmpty() && password.isNotEmpty()) {
                    if (isSignup) {
                        authViewModel.signup(email, password)
                    } else {
                        authViewModel.login(email, password)
                    }
                } else {
                    scope.launch {
                        snackbarHostState.showSnackbar("Please fill in all fields")
                    }
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = Purple500),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = if (isSignup) "Sign Up" else "Login",
                color = White,
                style = MaterialTheme.typography.bodyLarge
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        // Toggle between login and signup
        TextButton(onClick = { isSignup = !isSignup }) {
            Text(
                text = if (isSignup) "Already have an account? Login" else "Don't have an account? Sign Up",
                color = Purple500
            )
        }

        // Forgot Password link (visible only in login mode)
        if (!isSignup) {
            TextButton(onClick = { showResetDialog = true }) {
                Text(text = "Forgot Password?", color = Purple500)
            }
        }
        Spacer(modifier = Modifier.weight(1f))

        // Snackbar for errors and messages
        SnackbarHost(hostState = snackbarHostState)
    }

    // Forgot Password dialog
    if (showResetDialog) {
        var resetEmail by remember { mutableStateOf("") }
        AlertDialog(
            onDismissRequest = { showResetDialog = false },
            title = { Text("Reset Password") },
            text = {
                Column {
                    Text("Enter your email to receive a password reset link.")
                    Spacer(modifier = Modifier.height(8.dp))
                    OutlinedTextField(
                        value = resetEmail,
                        onValueChange = { resetEmail = it },
                        label = { Text("Email", color = Gray) },
                        modifier = Modifier.fillMaxWidth(),
                        colors = androidx.compose.material3.OutlinedTextFieldDefaults.colors(
                            focusedBorderColor = Purple500,
                            unfocusedBorderColor = Purple500,
                            focusedTextColor = White,
                            unfocusedTextColor = White,
                            cursorColor = White
                        )
                    )
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        if (resetEmail.isNotEmpty()) {
                            authViewModel.resetPassword(resetEmail)
                        } else {
                            scope.launch {
                                snackbarHostState.showSnackbar("Please enter an email")
                            }
                        }
                    }
                ) {
                    Text("Send", color = Purple500)
                }
            },
            dismissButton = {
                TextButton(onClick = { showResetDialog = false }) {
                    Text("Cancel", color = Purple500)
                }
            },
            containerColor = Black,
            titleContentColor = Purple500,
            textContentColor = White
        )
    }
}