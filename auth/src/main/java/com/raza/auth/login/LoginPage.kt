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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raza.auth.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun LoginPage(
    onRegister: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
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

                onClick = {
                    viewModel.login()
                }) {

                Text(
                    text = "Login"
                )
            }

            GoogleLoginButton()

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
    LoginPage(onRegister = {})
}