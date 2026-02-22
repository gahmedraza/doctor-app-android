package com.raza.medical.doctor.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ForgotPasswordScreen() {
    var email by remember { mutableStateOf("") }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(start = 10.dp, end = 10.dp)
        ) {

            Text(text = "Enter your email to reset your password")

            TextField(
                value = email,
                onValueChange = {
                    email = it
                }
            )

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        val request = ForgotPasswordRequest()
                        request.email = email
                        callForgotPasswordApi(request)
                    }
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