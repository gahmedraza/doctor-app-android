package com.raza.auth.auth

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun ForgotPasswordScreen(onResetPassword: (String) -> Unit,
                         viewModel: AuthViewModel = viewModel()) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(start = 10.dp, end = 10.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top =
                    100.dp),
                text = "Enter your email to reset your password",
                fontSize = 25.sp)

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                value = viewModel.email,
                onValueChange = {
                    viewModel.email = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                onClick = {
                    viewModel.forgotPassword(onResetPassword)
                }
            ) {
                Text(
                    text =
                        "Reset Password"
                )
            }

        }
    }
}