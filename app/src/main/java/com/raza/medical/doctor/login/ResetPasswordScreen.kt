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
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ResetPasswordScreen() {
    var newPassword by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Scaffold{ padding ->
        Column(modifier = Modifier
            .padding(padding)
            .padding(start = 10.dp,
                end = 10.dp)) {

            Text(text =
            "Create a new Password")

            TextField(
                value = newPassword,
                onValueChange = {
                    newPassword = it
                }
            )

            TextField(
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                }
            )

            Button(onClick = {
                CoroutineScope(Dispatchers.IO).launch {
                    val request = ResetPasswordRequest()
                    request.newPassword = newPassword
                    callResetPasswordApi(request)
                }
            }) {
                Text(text =
                "Submit")
            }
        }
    }
}