package com.raza.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun RegisterPage(
    onRegisterSuccess: () -> Unit,
    onLogin: () -> Unit,
    viewModel: AuthViewModel = viewModel()
) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(
                    start = 20.dp,
                    end = 20.dp
                )
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            MedicalImage(20.dp)

            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text = "Create Account",
                fontSize = 25.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 50.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Username")
                },
                value = viewModel.username,
                onValueChange = {
                    viewModel.username = it
                }
            )
            //
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Email")
                },
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                }
            )
            //
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Password")
                },
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                }
            )
            //
            OutlinedTextField(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxWidth(),
                label = {
                    Text("Confirm Password")
                },
                value = viewModel.confirmPassword,
                onValueChange = {
                    viewModel.confirmPassword = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 120.dp),

                enabled = !viewModel.isLoading,

                onClick = {
                    viewModel.register(onRegisterSuccess)
                }
            ) {
                if(viewModel.isLoading) {
                    CircularProgressIndicator(modifier = Modifier
                        .size(18.dp))

                } else {
                    Text(
                        text =
                            "Register"
                    )
                }
            }

            HorizontalDivider(
                modifier = Modifier
                    .padding(top = 20.dp)
            )

            Text(
                modifier = Modifier
                    .padding(top = 20.dp),
                text =
                    "Already have an account?",
                fontSize = 12.sp
            )

            Button(
                modifier = Modifier
                    .padding(
                        top = 10.dp,
                        bottom = 10.dp
                    ),
                onClick = {
                    onLogin()
                }
            ) {
                Text(
                    text = "Login",
                    fontSize = 12.sp
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview() {
    RegisterPage(
        onLogin = {},
        onRegisterSuccess = {}
    )
}