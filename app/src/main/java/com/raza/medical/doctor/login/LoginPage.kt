package com.raza.medical.doctor.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginPage() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showUsernameError by remember { mutableStateOf(false) }
    var usernameErrorMessage by remember { mutableStateOf("") }
    var showPasswordError by remember { mutableStateOf(false) }
    var passwordErrorMessage by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(
                    top = 300.dp,
                    start = 20.dp,
                    end = 20.dp
                )
        ) {
            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("username") },
                isError = showUsernameError,
                value = username,
                onValueChange = {
                    username = it
                }
            )

            if (showUsernameError) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),

                    text = usernameErrorMessage
                )
            }

            TextField(
                modifier = Modifier.fillMaxWidth(),
                label = { Text("password") },
                value = password,
                isError = showPasswordError,
                onValueChange = {
                    password = it
                }
            )

            if (showPasswordError) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),

                    text = passwordErrorMessage
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 300.dp),

                onClick = {

                    if (username.isEmpty()) {
                        showUsernameError = true
                        usernameErrorMessage = "Username cannot be empty"
                    } else if (username.length < 5) {
                        showUsernameError = true
                        usernameErrorMessage = "Username cannot be less than 5 characters"
                    } else {
                        showUsernameError = false
                    }

                    if (password.isEmpty()) {
                        showPasswordError = true
                        passwordErrorMessage = "Password cannot be empty"
                    } else if (password.length < 5) {
                        showPasswordError = true
                        passwordErrorMessage = "Password cannot be less than 5 characters"
                    } else {
                        showPasswordError = false
                    }

                    if (!showUsernameError && !showPasswordError) {
                        CoroutineScope(Dispatchers.IO).launch {
                            val request = LoginRequest()
                            request.email = username
                            request.password = password
                            makeLoginCall(request)
                        }
                    }
                }) {

                Text(
                    text = "Login"
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LoginPage()
}