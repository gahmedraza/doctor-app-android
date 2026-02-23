package com.raza.auth.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raza.auth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    onRegister: () -> Unit
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
                    start = 20.dp,
                    end = 20.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Image(
                painter = painterResource(
                    id =
                        R.drawable.baseline_medical_services_24
                ),
                contentDescription = "login icon",
                modifier = Modifier
                    .padding(30.dp)
                    .size(120.dp),
                colorFilter = ColorFilter.tint(Color(0xFF000000))
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "Welcome back",
                fontSize = 25.sp
            )

            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                text = "Sign in to continue",
                fontSize = 20.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
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

            OutlinedTextField(
                modifier = Modifier
                    .padding(
                        top =
                            20.dp,
                    )
                    .fillMaxWidth(),
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

            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        top = 20.dp
                    )
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.CenterHorizontally),
                text = "Need a new account?",
                fontSize = 12.sp
            )

            Button(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    )
                    .align(Alignment.CenterHorizontally),

                onClick = {
                    onRegister()
                }) {

                Text(
                    text = "Register",
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginPreview() {
    LoginPage {

    }
}