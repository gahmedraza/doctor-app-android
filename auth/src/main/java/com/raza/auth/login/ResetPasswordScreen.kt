package com.raza.auth.login

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raza.auth.bean.ResetPasswordRequest
import com.raza.auth.networking.callResetPasswordApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun ResetPasswordScreen(token: String?,
                        viewModel: AuthViewModel = viewModel()) {
    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(
                    start = 10.dp,
                    end = 10.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 200.dp
                    ),
                text =
                    "Create a new Password",
                fontSize = 25.sp
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 100.dp),
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                }
            )

            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp),
                value = viewModel.password,
                onValueChange = {
                    viewModel.password = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 50.dp),
                onClick = {
                    viewModel.resetPassword(token)
                }) {
                Text(
                    text =
                        "Submit"
                )
            }
        }
    }
}