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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    onSignup: () -> Unit
) {
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
                    top = 200.dp,
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
                    .padding(top = 200.dp),

                onClick = {

                    var validation = validateUsername(username)
                    showUsernameError = validation.showError
                    usernameErrorMessage = validation.errorMessage

                    validation = validatePassword(password)
                    showPasswordError = validation.showError
                    passwordErrorMessage = validation.errorMessage

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

            Text(
                modifier = Modifier
                    .padding(
                        top = 100.dp
                    )
                    .align(Alignment.CenterHorizontally),
                text = "For a new account",
                fontSize = 12.sp
            )

            Button(
                modifier = Modifier
                    .padding(
                        top = 10.dp
                    )
                    .align(Alignment.CenterHorizontally),

                onClick = {
                    onSignup()
                }) {

                Text(
                    text = "Signup",
                    fontSize = 12.sp
                )
            }
        }
    }
}

fun validateUsername(username: String): Validator {
    val userValidator = Validator()

    userValidator.apply {
        if (username.isEmpty()) {
            showError = true
            errorMessage = "Username cannot be empty"
        } else if (username.length < 5) {
            showError = true
            errorMessage = "Username cannot be less than 5 characters"
        } else {
            showError = false
        }
    }

    return userValidator
}

fun validatePassword(password: String): Validator {
    val validator = Validator()

    validator.apply {
        if (password.isEmpty()) {
            showError = true
            errorMessage = "Password cannot be empty"
        } else if (password.length < 5) {
            showError = true
            errorMessage = "Password cannot be less than 5 characters"
        } else {
            showError = false
        }
    }

    return validator
}

class Validator {
    var showError: Boolean = false
    var errorMessage: String = ""
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LoginPage({})
}