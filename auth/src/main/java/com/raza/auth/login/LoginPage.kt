package com.raza.auth.login

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raza.auth.theme.AuthTheme

@Composable
fun LoginPage(
    onLoginSuccess: () -> Unit,
    onRegister: () -> Unit,
    onForgotPassword: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {

    val snackbarHostState = remember { SnackbarHostState() }

    LaunchedEffect(Unit) {
        viewModel.events.collect { event ->
            when(event) {
                is AuthViewModel.UiEvent.ShowSnackbar -> {
                    snackbarHostState.showSnackbar(event.message)
                }
            }
        }
    }

    Scaffold(
        snackbarHost = {
            SnackbarHost(snackbarHostState)
        }
    ) { padding ->
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

            MedicalImage(20.dp)

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
                isError = viewModel.showUsernameError,
                value = viewModel.username,
                onValueChange = {
                    viewModel.username = it
                }
            )

            if (viewModel.showUsernameError) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),

                    text = viewModel.usernameErrorMessage
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
                value = viewModel.password,
                isError = viewModel.showPasswordError,
                onValueChange = {
                    viewModel.password = it
                }
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(Alignment.End)
                    .clickable {
                        onForgotPassword()
                    },
                text = "Forgot"
            )

            if (viewModel.showPasswordError) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            bottom = 10.dp
                        ),

                    text = viewModel.passwordErrorMessage
                )
            }

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 200.dp),

                enabled = !viewModel.isLoading,

                onClick = {
                    viewModel.login(onLoginSuccess = {
                        onLoginSuccess()
                    })
                }) {

                if(viewModel.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier
                            .size(18.dp),
                        strokeWidth = 2.dp
                    )
                } else {

                    Text(
                        text = "Login"
                    )
                }
            }

            GoogleLoginButton(
                onLogin = {
                    onLoginSuccess()
                }
            )

            FacebookLoginButton(
                onTokenReceived = { token ->
                    viewModel.loginWithFacebook(token,
                        onFacebookLoginSuccess = {
                            onLoginSuccess()
                        })
                }
            )

            HorizontalDivider(
                modifier = Modifier
                    .padding(
                        top = 20.dp
                    )
            )

            Row(modifier = Modifier
                .padding(top = 20.dp)) {

                Text(
                    modifier = Modifier
                        .align(Alignment.CenterVertically),
                    text = "Need a new account?",
                    fontSize = 12.sp
                )

                Spacer(modifier = Modifier.width(10.dp))

                Button(
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
}

@Preview(showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_NO)
@Composable
fun LoginPreview() {
    AuthTheme {
        LoginPage(
            onLoginSuccess = {},
            onRegister = {},
            onForgotPassword = {}
        )
    }
}