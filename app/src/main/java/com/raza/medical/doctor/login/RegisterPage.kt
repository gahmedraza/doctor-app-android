package com.raza.medical.doctor.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
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
import com.raza.medical.doctor.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Composable
fun RegisterPage(
    onLogin: () -> Unit
) {
    var username by remember { mutableStateOf<String>("") }
    var email by remember { mutableStateOf<String>("") }
    var password by remember { mutableStateOf<String>("") }
    var confirmPassword by remember { mutableStateOf<String>("") }

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

            Image(
                painter = painterResource(R.drawable.baseline_medical_services_24),
                contentDescription = "Medical Icon",
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(120.dp),
                colorFilter = ColorFilter.tint(Color(0xFF000000))
            )

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
                value = username,
                onValueChange = {
                    username = it
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
                value = email,
                onValueChange = {
                    email = it
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
                value = password,
                onValueChange = {
                    password = it
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
                value = confirmPassword,
                onValueChange = {
                    confirmPassword = it
                }
            )

            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 120.dp),
                onClick = {
                    CoroutineScope(context = Dispatchers.IO).launch {
                        //
                        val request = RegisterRequest()
                        request.username = username
                        request.email = email
                        request.password = password
                        makeRegisterCall(request)
                    }
                }
            ) {
                Text(
                    text =
                        "Register"
                )
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
    RegisterPage {

    }
}